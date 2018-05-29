package Pecas;

public class Cavalo extends PECA{
	public Cavalo (char time, Posicao pos) {
		super(time,pos,tPecas.cavalo);
	}
	
	@Override
	public boolean mov_valido(Posicao dest) { 
		if((Math.abs(pos.x - dest.x)==2 && Math.abs(pos.y - dest.y)==1)
				|| (Math.abs(pos.x - dest.x)==1 && Math.abs(pos.y - dest.y)==2))
			return true;
		return false;
	}
}
