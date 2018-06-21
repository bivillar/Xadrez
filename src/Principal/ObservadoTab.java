package Principal;

import java.util.Observable;

public class ObservadoTab extends Observable {
	private ObserverTab obsTab;
	
	public void add(ObserverTab o){
		obsTab=o;
	}
	
	public void remove(ObserverTab o){
		
	}
		
}
