package Principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Tabuleiro.*;
import Pecas.*;
import Interface.*;

public class Controlador implements MouseListener{
	private Casa _tabuleiro[][];
	private Casa _origem = null;
	private Casa _destino = null;
	private Casa _atacada = null;
	private Posicao _pos = new Posicao (0,0);
	private Posicao _dest  = new Posicao (0,0);
	
	public static Casa get_casa (Posicao pos, Casa[][] tab) { //recebe uma posicao da tela e retorna a casa que está dentro dela, se houver
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
	
	/*retorna a peça q ta no caminho de outra ou null se nao tiver nenhuma*/
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
	
	public boolean movValido (PECA P, Posicao dest) {
		Peao p; Torre t; Cavalo c; Bispo b; Rainha a; Rei e;
		
		switch(P.tipo) {
			case peao:
				p = (Peao)P;
				return p.mov_valido(dest);
			case torre:
				t = (Torre) P;
				return t.mov_valido(dest);
			case cavalo:
				c = (Cavalo) P;
				return c.mov_valido(dest);
			case bispo:
				b = (Bispo) P;
				return b.mov_valido(dest);
			case rainha:
				a = (Rainha) P;
				return a.mov_valido(dest);
			case rei:
				e = (Rei) P;
				return e.mov_valido(dest);
		}
		System.out.println("EPA");
		return false;	
	}
	
	@Override
	public void mouseClicked(MouseEvent c) {
		_tabuleiro = Tabuleiro.get_Tabuleiro();
		
		if(_origem == null) { // nao selecionaram quem vai atacar até agora
			_pos.set_Pos(c.getX(), c.getY());
			_origem = get_casa(_pos,_tabuleiro);
			
			if(_origem == null) {
				System.out.println("Tente outra vez");
			
			}else if(_origem.vazia()) { //nao tem peca
				//MOSTRAR UM ALERTA DE CASA VAZIA <--------------------------- LIV
				System.out.println("Casa vazia, Tente outra vez");
				_origem = null;
			}else {
				
				System.out.println("Origem - tipo:" + _origem.peca.tipo + " time:" + _origem.peca.time);
			}
			
		}
		else { //vamos ao ataque
			_dest.set_Pos(c.getX(), c.getY());
			_destino = get_casa(_dest,_tabuleiro);
			
			if(_origem == _destino || _destino ==null) {
				// ALERT DE MOVIMENTO INVALIDO <--------------------------- LIV
				System.out.println("Tente outra vez");
				
			}else {
				if(movValido(_origem.peca,_dest)) {
					System.out.println("Movimento Válido");
					
					if((_atacada = caminho(_pos,_dest,_tabuleiro))==null) {
						System.out.println("Caminho Livre");
						Tabuleiro.move_peca(_pos,_dest,_tabuleiro);
						
						//CHAMAR FUNCAO DA INTERFACE PRA REDESENHAR O TABULEIRO <--------------------------- LIV
						Tabuleiro.imprime();
						
					}else if(_atacada.peca.time != _origem.peca.time) {
						System.out.println("ATAQUE!");
						Tabuleiro.move_peca(_pos,_atacada.peca.pos,_tabuleiro);
						
						//CHAMAR FUNCAO DA INTERFACE PRA REDESENHAR O TABULEIRO <--------------------------- LIV
						Tabuleiro.imprime();
						
					}else {
						
						//MOSTRAR UM ALERT DIZENDO QUE A PECA TA BLOQUEADA <--------------------------- LIV
						System.out.println("Peça bloqueada");
						Tabuleiro.imprime();
					}
				}
				else {
					//MOSTRAR UM ALERT DIZENDO QUE O MOVIMENTO NAO É VALIDO, PEDINDO PRA TENTAR DE NV <--------------------------- LIV
					System.out.println("Movimento Inválido");
					Tabuleiro.imprime();
				}
			}
			
			_origem = null;
			_destino = null;
		}
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
