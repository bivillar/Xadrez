package Principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Tabuleiro.*;
import Pecas.*;
import Interface.*;

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
		
		_tabuleiro[origem.peca.pos.x][origem.peca.pos.y].movT='p'; //indica que é a posição do próprio peão
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
				System.out.println("Não é sua vez");
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
				
			}else if(!_destino.vazia() && _destino.peca.time == _origem.peca.time) {
				_pos.set_Pos(_dest.x,_dest.y);
				_origem = _destino;
				verificaCaminhosLivres(_origem);
				
			} else {
				switch(_jogadas[_pos.x+8*_pos.y][_dest.x+8*_dest.y]) {
					case invalido:
						System.out.println("Movimento Inválido");
						//Tabuleiro.imprime();
						break;
					case valido:
						System.out.println("Caminho Livre");
						Tabuleiro.move_peca(_pos,_dest,_tabuleiro);
						
						//Tabuleiro.imprime();
						
						promoPeao(_destino);//PROMOCAO PEAO
						vezBranco = !vezBranco;
						
						break;
					case ataque:
						System.out.println("ATAQUE!");
						Tabuleiro.move_peca(_pos,_dest,_tabuleiro);
						Main.janelaJogo.tab.repaint();
						
						//Tabuleiro.imprime();
						
						promoPeao(_destino); //PROMOCAO PEAO
						vezBranco = !vezBranco;
						break;
					case bloqueado:
						//MOSTRAR UM ALERT DIZENDO QUE A PECA TA BLOQUEADA <--------------------------- LIV
						System.out.println("Peça bloqueada");
						//Tabuleiro.imprime();
						break;
				}
				repaintTabuleiro();
				_origem = null;
			}
			_destino = null;
		}
	}

	private void promoPeao (Casa c) {
		if(c.peca.tipo != tPecas.peao || (c.peca.time == 'b' && c.peca.pos.y!=0) || (c.peca.time == 'p' && c.peca.pos.y!=7))
			return;
		
		System.out.println("PROMOVE PEAO");
		//TODO: POP ALERT PERGUNTANDO PRO QUE QUER PROMOVER;
		//TODO: CONVERTER O TIPO DA PECA DA CASA PARA O TIPO QUE DESEJA PROMOVER;
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