package Principal;

import Interface.*;

public class Main {
	
	static Window janelaJogo;
	
	public static void main(String[] args) {
		janelaJogo = new Window();
		janelaJogo.setVisible(true);
		janelaJogo.addMouseListener(new Controlador());
	}

}