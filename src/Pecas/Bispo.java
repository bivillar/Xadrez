package Pecas;

public class Bispo extends PECA{
	public Bispo (char time, Posicao pos) {
		super(time,pos,tPecas.bispo);
	}
	@Override
	public boolean mov_valido(Posicao dest) { 
		if((Math.abs(pos.x - dest.x) == Math.abs(pos.y - dest.y)))
			return true;
		return false;
	}
}
