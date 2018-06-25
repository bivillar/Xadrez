package Principal;

import java.util.Observable;
import java.util.Observer;

public class ObserverTab implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Main.janelaJogo.repaint();
	}
	
	
	
}