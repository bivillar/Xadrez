package Pecas;

import Tabuleiro.*;

public class Rei extends PECA{
	public Rei (char time, Posicao pos) {
		super(time,pos,tPecas.rei);
	}
	@Override
//	public movimento[] mov_valido(Casa casas[][])  { 
//		movimento movs[] = new movimento[64];
//		
//		for(int i=0;i<64;i++)
//			movs[i]=movimento.invalido;
//		return movs;
//	}
	
	public movimento[] mov_valido(Casa casas[][])  { 
		movimento movs[] = new movimento[64];
		movimento jogadas[][] = Tabuleiro.get_Jogadas();
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
					if(casas[x][y].peca.time!=time)
						movs[x+8*y] = movimento.ataque;
					else
						movs[x+8*y] = movimento.bloqueado;
				}
				else {
					movs[x+8*y] = movimento.valido;
					
					outerloop:
					for(int Y=0;Y<8;Y++) {
						for(int X = 0;X<8;X++){
							if((jogadas[X+8*Y][x+8*y] == movimento.valido && casas[X][Y].peca.tipo != tPecas.peao) || jogadas[X+8*Y][x+8*y] == movimento.ataque_valido) {
								System.out.println("X:"+X+" Y:"+Y + "- time:" + time + "time_ataque:" + casas[X][Y].peca.time);
								
								if(casas[X][Y].peca.time!=time) {
									System.out.println("Entrou aqui");
									movs[x+8*y] = movimento.bloqueado;
									break outerloop;
								}
							}
						}
					}
				}
			}
		}
		
		return movs;
	}
}
