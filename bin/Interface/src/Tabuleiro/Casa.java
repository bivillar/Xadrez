package Tabuleiro;

import Pecas.*;

public class Casa {
	public char cor;
	public PECA peca;
	
	public Casa (char Cor, PECA Peca) {
		cor = Cor;
		peca = Peca;
	}
	
	public boolean vazia() {
		if(peca == null)
			return true;
		return false;
	}
	
}
