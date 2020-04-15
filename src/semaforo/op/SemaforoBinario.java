package semaforo.op;

public class SemaforoBinario {

	boolean value;
	
	SemaforoBinario( boolean initValue ){
		value = initValue;
	}
	
	public synchronized void P(){
		while( value == false )
			Util.myWait(this); //en cola de procesos bloqueados
		value = false;
	}
	
	public synchronized void V(){
		value = true;
		notify();
	}

}