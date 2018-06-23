package Principal;

import Interface.*;
import Tabuleiro.*;

public class Main {
	
	static Window janelaJogo;
	
	public static void main(String[] args) {
		
		//t.imprime();
		janelaJogo = new Window();
		
		janelaJogo.setVisible(true);
		janelaJogo.addMouseListener(new Controlador());
	}

}