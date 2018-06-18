package Principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Tabuleiro.*;
import Pecas.*;
import Interface.*;
import javax.swing.*;

public class Controlador implements MouseListener{
	private Casa _tabuleiro[][];
	private movimento _jogadas[][];
	private Casa _origem = null;
	private Casa _destino = null;
	private Posicao _pos = new Posicao (0,0);
	private Posicao _dest  = new Posicao (0,0);
	private static boolean vezBranco = true;
	
	public static Casa get_casa (Posicao pos, Casa[][] tab) { //recebe uma posicao da tela e retorna a casa que estao dentro dela, se houver
		//int x,y;
		
		if(pos.x>560 || pos.y>582) {
			System.out.println("Fora do tab");
			return null;
		}
		else {
			pos.x = (int) pos.x/70;
			pos.y = (int) (pos.y-22)/70;
			return tab[pos.x][pos.y];
		}
	}
	
	/*retorna a peca q ta no caminho de outra ou null se nao tiver nenhuma*/
	public Casa caminho (Posicao pos, Posicao dest, Casa[][] tab) { 
		
		if(pos.x==dest.x) { //mesmo x
			if(pos.y<dest.y) { //destino ta mais alto
				for(int i=1; i<= dest.y - pos.y ;i++) 
					if (!tab[pos.x][pos.y+i].vazia()) 
						return tab[pos.x][pos.y+i];
				
			}else {
				for(int i=1; i<= pos.y - dest.y ;i++) //destino ta mais baixo
					if (!tab[pos.x][pos.y-i].vazia()) 
						return tab[pos.x][pos.y-i];
			}
		
		}else if(pos.y==dest.y) { //mesmo y
			
			if(pos.x<dest.x) {
				for(int i=1; i<= dest.x-pos.x ;i++) //destino mais a direita
					if (!tab[pos.x+i][pos.y].vazia()) 
						return tab[pos.x+i][pos.y];
				
			} else {
				for(int i=1; i<= pos.x-dest.x;i++) //destino mais a esquerda
					if (!tab[pos.x-i][pos.y].vazia()) 
						return tab[pos.x-i][pos.y];
			}
			
		}else if(Math.abs(dest.x-pos.x) == Math.abs(dest.y-pos.y)) { // diagonal
			
			if(dest.x>pos.x) {		//direita
				if(dest.y>pos.y) {		//em cima
					for(int i=1; i<= dest.x-pos.x;i++) 
						if (!tab[pos.x+i][pos.y+i].vazia()) 
							return tab[pos.x+i][pos.y+i];
				}else { 					//embaixo
					for(int i=1; i<= dest.x-pos.x;i++) 
						if (!tab[pos.x+i][pos.y-i].vazia()) 
							return tab[pos.x+i][pos.y-i];
				}
			} else{ 					//esquerda
				if(dest.y>pos.y) {		//em cima
					for(int i=1; i<= dest.y-pos.y; i++) 
						if (!tab[pos.x-i][pos.y+i].vazia()) 
							return tab[pos.x-i][pos.y+i];
				}else {					//embaixo
					for(int i=1; i<= dest.y-pos.y; i++) 
						if (!tab[pos.x-i][pos.y-i].vazia()) 
							return tab[pos.x-i][pos.y-i];
				}
			}
		}else { //cavalo
			if(!tab[dest.x][dest.y].vazia())
				return tab[dest.x][dest.y];
			else
				return null;
		}
		
		return null;		
	}
	
	public void verificaCaminhosLivres (Casa origem) {
		int i,j;
		
		
		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				if(_jogadas[_pos.x+8*_pos.y][i+8*j] == movimento.valido) {
					_tabuleiro[i][j].movT='v';
				}
				else if(_jogadas[_pos.x+8*_pos.y][i+8*j] == movimento.ataque ) {
					_tabuleiro[i][j].movT='a';
				}
				else{
					_tabuleiro[i][j].movT='0';
				}
			}
		}
		_tabuleiro[origem.peca.pos.x][origem.peca.pos.y].movT='p'; //indica que √© a posi√ß√£o do pr√≥prio pe√£o
		Main.janelaJogo.tab.repaint();
	
	}
	
	public void repaintTabuleiro() {
		for (int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				_tabuleiro[x][y].movT='0';
			}
		}
		
		Main.janelaJogo.tab.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent c) {
		_tabuleiro = Tabuleiro.get_Tabuleiro();
		_jogadas = Tabuleiro.get_Jogadas();
		String time="";
		//char original = 'b';
		
		if(_origem == null) { // nao selecionaram quem vai atacar ate agora
			_pos.set_Pos(c.getX(), c.getY());
			_origem = get_casa(_pos,_tabuleiro);
			
			if(_origem == null) {
				System.out.println("Tente outra vez");
			
			}else if(_origem.vazia()) { //nao tem peca
				System.out.println("Casa vazia, Tente outra vez");
				_origem = null;
			}else if((vezBranco && _origem.peca.time=='p') || (!vezBranco && _origem.peca.time=='b')) {
				System.out.println("N√£o √© sua vez");
				_origem = null;
			}
			else {
				verificaCaminhosLivres(_origem);
			}
			
		}
		else { //vamos ao ataque
			_dest.set_Pos(c.getX(), c.getY());
			_destino = get_casa(_dest,_tabuleiro);
			
			if(_origem == _destino || _destino == null) {
				System.out.println("Tente outra vez");
				_origem = null;
				repaintTabuleiro();
				
			}else if(!_destino.vazia() && _destino.peca.time == _origem.peca.time) {
				if(_destino.peca.tipo == tPecas.torre && _origem.peca.tipo == tPecas.rei) {
					if(_jogadas[_pos.x+8*_pos.y][_dest.x+8*_dest.y] == movimento.valido) {
						//ROQUE
						
						Posicao nRei = new Posicao();
						Posicao nTorre = new Posicao();
						int fat = 1;
						
						if(_pos.x> _dest.x) {
							fat = -1;
						}
						
						nRei.set_Pos(_pos.x+2*fat,_dest.y);
						nTorre.set_Pos(nRei.x-1*fat,_dest.y);
						
						Tabuleiro.move_peca(_pos,nRei,_tabuleiro); // bota o rei duas casas mais perto da torre
						Tabuleiro.move_peca(_dest,nTorre,_tabuleiro);  //bota a torre na casa do lado do rei
						
						
						vezBranco = !vezBranco;
						
					}

					_origem = null;
					repaintTabuleiro();

				}
				else {
					_pos.set_Pos(_dest.x,_dest.y);
					_origem = _destino;
					verificaCaminhosLivres(_origem);
				}
			} else {
				switch(_jogadas[_pos.x+8*_pos.y][_dest.x+8*_dest.y]) {
					case invalido:
						System.out.println("Movimento Inv√°lido");
						//Tabuleiro.imprime();
						break;
					case valido:
						System.out.println("Caminho Livre");
						Tabuleiro.move_peca(_pos,_dest,_tabuleiro);
						
						//Tabuleiro.imprime();
						verifica_xeque(_destino);
						promoPeao(_destino); //PROMOCAO PEAO
						vezBranco = !vezBranco;	
						break;
					case ataque:
						System.out.println("ATAQUE!");
						if(_destino.peca.tipo == tPecas.rei) {
							if(_destino.peca.time == 'b')
								time = "BRANCO";
							else
								time = "PRETO";
								
								JOptionPane.showMessageDialog(Main.janelaJogo,
									    "XEQUE-MATE!!\n VITORIA DO TIME "+time,
									    "Aviso",
									    JOptionPane.PLAIN_MESSAGE);
						}
						Tabuleiro.move_peca(_pos,_dest,_tabuleiro);
						
						
						//Tabuleiro.imprime();
						verifica_xeque(_destino);
						promoPeao(_destino); //PROMOCAO PEAO
						vezBranco = !vezBranco;
						break;
					case bloqueado:
						//MOSTRAR UM ALERT DIZENDO QUE A PECA TA BLOQUEADA <--------------------------- LIV
						System.out.println("Pe√ßa bloqueada");
						//Tabuleiro.imprime();
						break;
				}
				repaintTabuleiro();
				_origem = null;
			}
			_destino = null;
		}
	}
	
	private void verifica_xeque (Casa c){
		String time;
		if(c.peca.time == 'b')
			time = "BRANCO";
		else
			time = "PRETO";
		
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				if((_jogadas[c.peca.pos.x + 8*c.peca.pos.y][x+8*y]==movimento.ataque || _jogadas[c.peca.pos.x + 8*c.peca.pos.y][x+8*y]==movimento.ataque_valido)
						&& !_tabuleiro[x][y].vazia() && _tabuleiro[x][y].peca.tipo == tPecas.rei) {
					for(int X=0;X<8;X++) {
						for(int Y=0;Y<8;Y++) {
							if(_jogadas[x+8*y][X+8*Y] == movimento.valido || _jogadas[x+8*y][X+8*Y] == movimento.ataque) {
								
								JOptionPane.showMessageDialog(Main.janelaJogo,
									    "XEQUE PELO TIME "+time,
									    "Aviso",
									    JOptionPane.WARNING_MESSAGE);
								
								System.out.println("XEQUE!!");
								return;
							}
						}
					}
					JOptionPane.showMessageDialog(Main.janelaJogo,
						    "XEQUE-MATE!!\n VITORIA DO TIME: "+time,
						    "Aviso",
						    JOptionPane.WARNING_MESSAGE);
					System.out.println("XEQUE-MATE!!");
				}
			}
		}				
	}
	
	private Boolean caminhoBloqueado (Posicao a, Posicao b) {
		int fat = 1;
		System.out.println("oioi");
		if(a.x>b.x) {
			fat=-1;
		}
		
		for(int i = a.x; i!=b.x; i+=fat) {
			if(!_tabuleiro[i][a.y].vazia()) {
				System.out.println("x: "+i+"y: "+a.y);
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
	private void promoPeao (Casa c) {
		String poss[]= {"Rainha","Torre", "Bispo", "Cavalo"};
		String tipo;
		
		if(c.peca.tipo != tPecas.peao || (c.peca.time == 'b' && c.peca.pos.y!=0) || (c.peca.time == 'p' && c.peca.pos.y!=7))
			return;
		
		System.out.println("PROMOVE PEAO");
		
		tipo= (String)JOptionPane.showInputDialog(
		                    Main.janelaJogo,
		                    "PROMO«√O DO PE√O!!\n"
		                    + "Escolha o tipo de peca:",
		                    "ATEN«√O",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null, poss,
		                    "Rainha");
		
		Tabuleiro.promovePeao(c.peca.pos,tipo);
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}