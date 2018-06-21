package Principal;

import java.util.Observable;
import java.util.Observer;

public class ObserverTab implements Observer {

	public void notify (ObservadoTab o){
		Main.janelaJogo.tab.repaint();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
