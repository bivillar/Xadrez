package Pecas;

import Tabuleiro.Casa;

public class Cavalo extends PECA{
	public Cavalo (char time, Posicao pos) {
		super(time,pos,tPecas.cavalo);
	}
	
	@Override
//	public boolean mov_valido(Posicao dest) { 
//		if((Math.abs(pos.x - dest.x)==2 && Math.abs(pos.y - dest.y)==1)
//				|| (Math.abs(pos.x - dest.x)==1 && Math.abs(pos.y - dest.y)==2))
//			return true;
//		return false;
//	}
	
	public movimento[] mov_valido(Casa casas[][])  { 
		movimento movs[] = new movimento[64];
		int a[]= {2,2,-2,-2,1,1,-1,-1};
		int b[]= {1,-1,1,-1,2,-2,2,-2};
		int x,y;
		
		for(int i=0;i<64;i++)
			movs[i]=movimento.invalido;
		
		for(int i =0;i<8;i++) {
			x= pos.x+a[i];
			y= pos.y+b[i];
			
			if(x<8 && y<8 && x>=0 && y>=0) {
				if(casas[x][y].vazia())
					movs[x+8*y] = movimento.valido;
				else if(casas[x][y].peca.time != time)
					movs[x+8*y] = movimento.ataque;
				else
					movs[x+8*y] = movimento.bloqueado;
			}
		}
		
		return movs;
	}
}
