package Principal;

import Interface.*;
import Tabuleiro.*;

public class Main {
	
	static Window janelaJogo;
	
	public static void main(String[] args) {
		Tabuleiro t = new Tabuleiro();
		t.imprime();
		janelaJogo = new Window(t.get_Tabuleiro());
		
		janelaJogo.setVisible(true);
		janelaJogo.addMouseListener(new Controlador());
	}

}
