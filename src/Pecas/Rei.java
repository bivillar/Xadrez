package Pecas;

public class Rei extends PECA{
	public Rei (char time, Posicao pos) {
		super(time,pos,tPecas.rei);
	}
	public boolean mov_valido(Posicao dest) { 
		if(Math.abs(pos.x-dest.x)==1 && Math.abs(pos.y-dest.y)==1) {
			return true;
		}
		return false;
	}
}
