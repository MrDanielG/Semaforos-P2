package semaforo.op;

public class SemaforoContador {

	int value;
	
	public SemaforoContador( int initValue){
		value = initValue;
	}
	
	public synchronized void P() {
		value--;
	
		if( value < 0 )
			Util.myWait( this );
	}
	
	public synchronized void V() {
		value++;
		if( value <= 0 )
		notify();
	}
}