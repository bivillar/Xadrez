package Pecas;

import Tabuleiro.*;

public class Rei extends PECA{
	public Rei (char time, Posicao pos) {
		super(time,pos,tPecas.rei);
	}
	
	@Override	
	public movimento[] mov_valido(Casa casas[][])  { 
		movimento movs[] = new movimento[64];
		movimento jogadas[][] = Tabuleiro.getJogadas();
		int a[] = {1,-1,0,0,1,1,-1,-1};
		int b[] = {0,0,1,-1,1,-1,1,-1};
		int x,y;
		
		for(int i=0;i<64;i++)
			movs[i]=movimento.invalido;
		
		for(int i=0;i<8;i++) {
			x = pos.x + a[i];
			y = pos.y + b[i];
			
			if(x<8 && x>=0 && y<8 && y>=0) {
				if(!casas[x][y].vazia()) {
					if(casas[x][y].peca.time!=time) {
						movs[x+8*y] = movimento.ataque;
						outerloop:
						for(int Y =0; Y<8;Y++) {
							for(int X = 0;X<8;X++) {
								if(jogadas[X+8*Y][x+8*y] == movimento.bloqueado) {
									movs[x+8*y] = movimento.invalido;
									break outerloop;
								}
							}
						}
					}
					else {
						movs[x+8*y] = movimento.bloqueado;
					}
				}
				else {
					movs[x+8*y] = movimento.valido;
					
					outerloop:
					for(int Y=0;Y<8;Y++) {
						for(int X = 0;X<8;X++){
							if((jogadas[X+8*Y][x+8*y] == movimento.valido && casas[X][Y].peca.tipo != tPecas.peao) || jogadas[X+8*Y][x+8*y] == movimento.ataque_valido) {
								
								if(casas[X][Y].peca.time!=time) {
									movs[x+8*y] = movimento.bloqueado;
									break outerloop;
								}
							}
						}
					}
				}
			}
		}
		
		//ROQUE
		if(qtd_mov == 0) {
			loop:
			for(int X=pos.x-1;X>=0;X--) {
				for(x=0;x<8;x++)
					for(y=0;y<8;y++)
						if((jogadas[x+8*y][X+8*pos.y]==movimento.valido || jogadas[x+8*y][X+8*pos.y]==movimento.ataque_valido) && casas[x][y].peca.time!=time)
							break loop;
			
				if(!casas[X][pos.y].vazia()) {
					if(casas[X][pos.y].peca.tipo == tPecas.torre && casas[X][pos.y].peca.qtd_mov==0)
						movs[X+8*pos.y] = movimento.valido;
					break;
				}
			}
		
			loop:
			for(int X=pos.x+1;X<8;X++) {
				for(x=0;x<8;x++)
					for(y=0;y<8;y++)
						if((jogadas[x+8*y][X+8*pos.y]==movimento.valido || jogadas[x+8*y][X+8*pos.y]==movimento.ataque_valido) && casas[x][y].peca.time!=time)
							break loop;
				if(!casas[X][pos.y].vazia()) {
					if(casas[X][pos.y].peca.tipo == tPecas.torre && casas[X][pos.y].peca.qtd_mov==0)
						movs[X+8*pos.y] = movimento.valido;
					break;
				}
			}
		}
		
		return movs;
	}
}
