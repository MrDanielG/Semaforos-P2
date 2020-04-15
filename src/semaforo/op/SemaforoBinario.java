package semaforo.op;

import semaforo.gui.MainWindow;

public class SemaforoBinario {

	boolean value;
	
	SemaforoBinario( boolean initValue ){
		value = initValue;
	}
	
	public synchronized void P(){
		MainWindow.setCableProdRojo();
		MainWindow.setCableConsVerde();
		
		while( value == false )
			Util.myWait(this); //en cola de procesos bloqueados
		value = false;
	}
	
	public synchronized void V(){
		MainWindow.setCableConsRojo();
		MainWindow.setCableProdVerde();
		
		value = true;
		notify();
	}

}