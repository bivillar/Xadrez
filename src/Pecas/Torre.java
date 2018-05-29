package Pecas;

public class Torre extends PECA{
	public Torre (char time, Posicao pos) {
		super(time,pos,tPecas.torre);
	}
	
	@Override
	public boolean mov_valido(Posicao dest) { 
		if(dest.x == pos.x || dest.y==pos.y)
			return true;
		return false;
	}
}
