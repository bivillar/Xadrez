package Pecas;

import Tabuleiro.Casa;

public class Torre extends PECA{
	public Torre (char time, Posicao pos) {
		super(time,pos,tPecas.torre);
	}
	
	@Override
//	public boolean mov_valido(Posicao dest) { 
//		if(dest.x == pos.x || dest.y==pos.y)
//			return true;
//		return false;
//	}
	
	public movimento[] mov_valido(Casa casas[][])  { 
		movimento movs[] = new movimento[64];
		
		for(int i=0;i<64;i++)
			movs[i]=movimento.invalido;
		
		//mesmo x, indo pra cima
		for(int y=pos.y-1; y>=0 ;y--) {
			
			if(!casas[pos.x][y].vazia()) {
				if(casas[pos.x][y].peca.time != time)
					movs[pos.x + 8*(y)] = movimento.ataque;
				else
					movs[pos.x + 8*(y)] = movimento.bloqueado;
				break;
			}
			
			movs[pos.x + 8*(y)] = movimento.valido;
		}
		
		//mesmo x, indo pra baixo				
		for(int y=pos.y+1; y<8 ;y++) {
			
			if(!casas[pos.x][y].vazia()) {
				if(casas[pos.x][y].peca.time != time)
					movs[pos.x + 8*(y)] = movimento.ataque;
				else
					movs[pos.x + 8*(y)] = movimento.bloqueado;
				break;
			}
			
			movs[pos.x + 8*(y)] = movimento.valido;
		}

		//mesmo y, indo pra esquerda
		for(int x=pos.x-1; x>=0 ;x--) {
			
			if(!casas[x][pos.y].vazia()) {
				if(casas[x][pos.y].peca.time != time)
					movs[x + 8*(pos.y)] = movimento.ataque;
				else
					movs[x + 8*(pos.y)] = movimento.bloqueado;
				break;
			}
			
			movs[pos.x + 8*(pos.y)] = movimento.valido;
		}

		//mesmo y, indo pra direita
		for(int x=pos.x+1; x<8 ;x++) {
			
			if(!casas[x][pos.y].vazia()) {
				if(casas[x][pos.y].peca.time != time)
					movs[x + 8*(pos.y)] = movimento.ataque;
				else
					movs[x + 8*(pos.y)] = movimento.bloqueado;
				break;
			}
			
			movs[pos.x + 8*(pos.y)] = movimento.valido;
		}
		return movs;
	}
}
