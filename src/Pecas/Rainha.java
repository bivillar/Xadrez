package Pecas;

public class Rainha extends PECA{
	public Rainha(char time, Posicao pos) {
		super(time,pos,tPecas.rainha);
	}
	@Override
	public boolean mov_valido(Posicao dest) { 
		if((pos.x==dest.x) || (pos.y==dest.y)||
				(Math.abs(pos.x - dest.x) == Math.abs(pos.y - dest.y)))
			return true;
		return false;
	}
}
