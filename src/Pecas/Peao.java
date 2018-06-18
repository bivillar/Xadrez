package Pecas;

import Tabuleiro.Casa;

public class Peao extends PECA{
	
	public Peao (char time, Posicao pos) {
		super(time,pos,tPecas.peao);
	}
	
	@Override
//	public boolean mov_valido(Posicao dest) { 
//		if(dest.x == pos.x) {
//			if((time == 'b' && dest.y<pos.y)||(time == 'p' && dest.y>pos.y)) {
//				if(Math.abs(dest.y-pos.y)==1 || (this.primeira_jogada() && Math.abs(dest.y-pos.y) == 2))
//					return true;
//			}
//		}
//		return false;
//	}
	
	public movimento[] mov_valido(Casa casas[][])  { 
		movimento movs[] = new movimento[64];
		int fator=1;
		int y_vet, x_vet;
		
		for(int i=0;i<64;i++)
			movs[i]=movimento.invalido;
		
		if(pos.y!=7 && pos.y!=0) { //ainda tem pra onde andar
			
			if(time == 'b')  // só anda pra cima
				fator = -1;
			
			y_vet = pos.y+1*fator;
			if(casas[pos.x][y_vet].vazia())
				movs[pos.x+8*y_vet] = movimento.valido;
			else
				movs[pos.x+8*y_vet] = movimento.bloqueado;
			
			
			if(qtd_mov == 0) {
				y_vet = pos.y+2*fator;
				
				if(casas[pos.x][y_vet].vazia() && casas[pos.x][pos.y+fator].vazia())
					movs[pos.x+8*y_vet] = movimento.valido;
				else
					movs[pos.x+8*y_vet] = movimento.invalido;
			}
	
			y_vet = pos.y+1*fator;
			
			if(pos.x != 7) { // ainda tem espaco pra direita
				x_vet = pos.x+1;
				
				if(!casas[x_vet][y_vet].vazia()) {
					if(casas[x_vet][y_vet].peca.time!= time) {
						movs[x_vet+8*(y_vet)] = movimento.ataque;
					}else {
						movs[x_vet+8*(y_vet)] = movimento.bloqueado;
					}
				}
				else if(casas[x_vet][y_vet].vazia()) {
					movs[x_vet+8*(y_vet)] = movimento.ataque_valido;
				}
			}
			
			if(pos.x != 0) { // ainda tem espaço pra esquerda
				x_vet = pos.x-1;
				
				if(!casas[x_vet][y_vet].vazia()) {
					if (casas[x_vet][y_vet].peca.time!= time) {
						movs[x_vet+8*(y_vet)] = movimento.ataque;
					}else {
						movs[x_vet+8*(y_vet)] = movimento.bloqueado;
					}
				}
				else if(casas[x_vet][y_vet].vazia()) {
					movs[x_vet+8*(y_vet)] = movimento.ataque_valido;
				}
			}
		}
		
		return movs;
	}
	
}
