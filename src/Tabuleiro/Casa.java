package Tabuleiro;

import Pecas.*;

public class Casa {
	public char cor;
	public PECA peca;
	public boolean mov;
	
	public Casa (char Cor, PECA Peca) {
		cor = Cor;
		peca = Peca;
		mov=false;
	}
	
	public boolean vazia() {
		if(peca == null)
			return true;
		return false;
	}
	
}
