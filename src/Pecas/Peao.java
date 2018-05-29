package Pecas;

public class Peao extends PECA{
	
	public Peao (char time, Posicao pos) {
		super(time,pos,tPecas.peao);
	}
	
	@Override
	public boolean mov_valido(Posicao dest) { 
		if(dest.x == pos.x) {
			if((time == 'b' && dest.y<pos.y)||(time == 'p' && dest.y>pos.y)) {
				if(Math.abs(dest.y-pos.y)==1 || (this.primeira_jogada() && Math.abs(dest.y-pos.y) == 2))
					return true;
			}
		}
		return false;
	}
	
	private boolean primeira_jogada () {
		if((time=='b' && pos.y == 6) || (time=='p' && pos.y == 1))
			return true;
		return false;
	}
}
