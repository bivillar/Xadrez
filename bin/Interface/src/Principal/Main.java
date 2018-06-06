package Principal;

import Interface.*;
import Tabuleiro.*;

public class Main {
	
	public static void main(String[] args) {
		Tabuleiro t = new Tabuleiro();
		t.imprime();
		Window J = new Window(t.get_Tabuleiro());
		J.setVisible(true);
		J.addMouseListener(new Controlador());
	}

}
