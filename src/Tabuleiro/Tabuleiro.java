package Tabuleiro;

import Pecas.*;

public class Tabuleiro {
	private static Casa _casa[][] = new Casa[8][8];
	private static movimento _jogadas[][] = new movimento[64][64];
	
	public Tabuleiro () {
		
		for (int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				if(y%2 == 0) {
					_casa[x][y] = new Casa('b', null); 
					_casa[++x][y] = new Casa('p', null); 
				}
				else {
					_casa[x][y] = new Casa('p', null); 
					_casa[++x][y] = new Casa('b', null); 
				}
			}
		}
		
		_casa[0][0].peca = (Torre) new Torre('p',new Posicao());		
		_casa[1][0].peca = (Cavalo) new Cavalo('p',new Posicao(1,0));
		_casa[2][0].peca = (Bispo) new Bispo('p',new Posicao(2,0));
		_casa[3][0].peca = (Rainha) new Rainha('p',new Posicao(3,0));
		_casa[4][0].peca = (Rei) new Rei('p',new Posicao(4,0));
		_casa[5][0].peca = (Bispo) new Bispo('p',new Posicao(5,0));
		_casa[6][0].peca = (Cavalo) new Cavalo('p',new Posicao(6,0));
		_casa[7][0].peca = (Torre) new Torre('p',new Posicao(7,0));
		
		_casa[0][1].peca = (Peao) new Peao('p',new Posicao(0,1));
		_casa[1][1].peca = (Peao) new Peao('p',new Posicao(1,1));
		_casa[2][1].peca = (Peao) new Peao('p',new Posicao(2,1));
		_casa[3][1].peca = (Peao) new Peao('p',new Posicao(3,1));
		_casa[4][1].peca = (Peao) new Peao('p',new Posicao(4,1));
		_casa[5][1].peca = (Peao) new Peao('p',new Posicao(5,1));
		_casa[6][1].peca = (Peao) new Peao('p',new Posicao(6,1));
		_casa[7][1].peca = (Peao) new Peao('p',new Posicao(7,1));
		
		_casa[0][7].peca = (Torre) new Torre('b',new Posicao(0,7));
		_casa[1][7].peca = (Cavalo) new Cavalo('b',new Posicao(1,7));
		_casa[2][7].peca = (Bispo) new Bispo('b',new Posicao(2,7));
		_casa[3][7].peca = (Rainha) new Rainha('b',new Posicao(3,7));
		_casa[4][7].peca = (Rei) new Rei('b',new Posicao(4,7));
		_casa[5][7].peca = (Bispo) new Bispo('b',new Posicao(5,7));
		_casa[6][7].peca = (Cavalo) new Cavalo('b',new Posicao(6,7));
		_casa[7][7].peca = (Torre) new Torre('b',new Posicao(7,7));
		
		_casa[0][6].peca = (Peao) new Peao('b',new Posicao(0,6));
		_casa[1][6].peca = (Peao) new Peao('b',new Posicao(1,6));
		_casa[2][6].peca = (Peao) new Peao('b',new Posicao(2,6));
		_casa[3][6].peca = (Peao) new Peao('b',new Posicao(3,6));
		_casa[4][6].peca = (Peao) new Peao('b',new Posicao(4,6));
		_casa[5][6].peca = (Peao) new Peao('b',new Posicao(5,6));
		_casa[6][6].peca = (Peao) new Peao('b',new Posicao(6,6));
		_casa[7][6].peca = (Peao) new Peao('b',new Posicao(7,6));	
		
		update_Jogadas();
	}
	
	public static Casa[][] get_Tabuleiro (){
		return _casa;
	}
	
	public static movimento[][] get_Jogadas (){
		return _jogadas;
	}
	
	public static void move_peca(Posicao pos, Posicao dest, Casa[][] tab) {
		tab[dest.x][dest.y].peca = tab[pos.x][pos.y].peca;
		tab[pos.x][pos.y].peca = null;
		tab[dest.x][dest.y].peca.pos.x = dest.x;
		tab[dest.x][dest.y].peca.pos.y = dest.y;
		tab[dest.x][dest.y].peca.qtd_mov++;
		update_Jogadas();
	}

	
	public static void update_Jogadas () {	
		Posicao [] pReis = {new Posicao(), new Posicao()};
		int i=0;
		for(int k = 0;k<64;k++)
			for(int t=0;t<64;t++)
				_jogadas[t][k]=movimento.invalido;
		
		for(int y = 0; y<8;y++) {
			for(int x = 0; x<8;x++ ) {
				if(!_casa[x][y].vazia()) {
					if(_casa[x][y].peca.tipo != tPecas.rei)
						_jogadas[x+8*y] = _casa[x][y].peca.mov_valido(_casa);
					else { //guardo as posicoes dos reis pra nao ter que verificar todo o tabuleiro de novo
						pReis[i].x = x;
						pReis[i].y = y;
						i++;
					}
				}
			}
		}
		
		for(i=0;i<2;i++) //so preencho os reis depois que sei todos os outros
			_jogadas[pReis[i].x+8*pReis[i].y] = _casa[pReis[i].x][pReis[i].y].peca.mov_valido(_casa);
		
	}
	
	public static void xeque(Posicao pos, Posicao rei) {
		for(int k=0;k<64;k++) {
			for(int t=0;t<64;t++){
				if(t != pos.x+8*pos.y && k!= rei.x+8*rei.y) // so deixa manter as jogadas que tiram o xeque
					_jogadas[k][t] = movimento.invalido;
				
			}	
		}
	}
	
	public static void promovePeao(Posicao pos, String tipo) {
		switch(tipo) {
			case "Rainha":
				_casa[pos.x][pos.y].peca = (Rainha) new Rainha(_casa[pos.x][pos.y].peca.time,pos);
				break;
			case "Torre":
				_casa[pos.x][pos.y].peca = (Torre) new Torre(_casa[pos.x][pos.y].peca.time,pos);
				break;
			case "Bispo":
				_casa[pos.x][pos.y].peca = (Bispo) new Bispo(_casa[pos.x][pos.y].peca.time,pos);
				break;
			case "Cavalo":
				_casa[pos.x][pos.y].peca = (Cavalo) new Cavalo(_casa[pos.x][pos.y].peca.time,pos);
				break;
		}
	}
	
	public static void imprime () {
		char aux=' ';
		for(int y=0; y<8;y++){
			for(int x=0; x<8;x++){
				if(_casa[x][y].vazia()) {
					if (_casa[x][y].cor == 'b')
						System.out.print("|---|");
					else
						System.out.print("|+++|");
				}
				else {
					switch(_casa[x][y].peca.tipo){
					case peao:
						aux = 'p';
						break;
					case torre:
						aux = 't';
						break;
					case cavalo:
						aux = 'c';
						break;
					case bispo:
						aux = 'b';
						break;
					case rainha:
						aux = 'a';
						break;
					case rei:
						aux = 'e';
						break;
					}
					
					if(_casa[x][y].peca.time == 'b')
						aux = Character.toUpperCase(aux);
					
					if (_casa[x][y].cor == 'b')
						System.out.print("|-"+aux+"-|");
					else
						System.out.print("|+"+aux+"+|");
				}
				
			}
			System.out.println(" ");
		}
		
	}
}
