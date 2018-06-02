package Pecas;

import Tabuleiro.Casa;

public class Bispo extends PECA{
	public Bispo (char time, Posicao pos) {
		super(time,pos,tPecas.bispo);
	}
	@Override
//	public boolean mov_valido(Posicao dest) { 
//		if((Math.abs(pos.x - dest.x) == Math.abs(pos.y - dest.y)))
//			return true;
//		return false;
//	}
	
	public movimento[] mov_valido(Casa casas[][])  { 
		movimento movs[] = new movimento[64];
		
		for(int i=0;i<64;i++)
			movs[i]=movimento.invalido;
		
		//diagonal cima esquerda
		for(int y=pos.y-1, x = pos.x-1; y>=0 && x>=0 ; y-- , x--) {
			
			if(!casas[x][y].vazia()) {
				if(casas[x][y].peca.time != time)
					movs[x + 8*y] = movimento.ataque;
				else
					movs[x + 8*y] = movimento.bloqueado;
				break;
			}
			
			movs[x + 8*y] = movimento.valido;
		}
		
		//diagonal cima direita				
		for(int y=pos.y-1, x = pos.x+1; y>=0 && x<8 ; y-- , x++) {
			
			if(!casas[x][y].vazia()) {
				if(casas[x][y].peca.time != time)
					movs[x + 8*y] = movimento.ataque;
				else
					movs[x + 8*y] = movimento.bloqueado;
				break;
			}
			
			movs[x + 8*y] = movimento.valido;
		}

		//diagonal baixo esquerda
		for(int y=pos.y+1, x = pos.x-1; y<8 && x>=0 ; y++ , x--) {
			
			if(!casas[x][y].vazia()) {
				if(casas[x][y].peca.time != time)
					movs[x + 8*y] = movimento.ataque;
				else
					movs[x + 8*y] = movimento.bloqueado;
				break;
			}
			
			movs[x + 8*y] = movimento.valido;
		}

		//diagonal baixo direita
		for(int y=pos.y+1, x = pos.x+1; y<8 && x<8 ; y++ , x++) {
			
			if(!casas[x][y].vazia()) {
				if(casas[x][y].peca.time != time)
					movs[x + 8*y] = movimento.ataque;
				else
					movs[x + 8*y] = movimento.bloqueado;
				break;
			}
			
			movs[x + 8*y] = movimento.valido;
		}
		
		return movs;
	}
}
