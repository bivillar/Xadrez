package Principal;

import Interface.*;

public class Main {
	
	static Window janelaJogo;
	
	public static void main(String[] args) {
		Facade facade = new Facade();
		janelaJogo = new Window(facade);
		janelaJogo.setVisible(true);
		janelaJogo.addMouseListener(new Controlador(facade));
	}

}