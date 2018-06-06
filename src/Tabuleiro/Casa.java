package Tabuleiro;

import Pecas.*;

public class Casa {
	public char cor;
	public PECA peca;
	public char movT;
	
	public Casa (char Cor, PECA Peca) {
		cor = Cor;
		peca = Peca;
		movT = '0';
	}
	
	public boolean vazia() {
		if(peca == null)
			return true;
		return false;
	}
	
}
