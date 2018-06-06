package Pecas;

import Tabuleiro.Casa;

public class Rainha extends PECA{
	public Rainha(char time, Posicao pos) {
		super(time,pos,tPecas.rainha);
	}
	
	@Override
//	public boolean mov_valido(Posicao dest) { 
//		if((pos.x==dest.x) || (pos.y==dest.y)||
//				(Math.abs(pos.x - dest.x) == Math.abs(pos.y - dest.y)))
//			return true;
//		return false;
//	}
	
	public movimento[] mov_valido(Casa casas[][])  {  //é bispo + torre
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
