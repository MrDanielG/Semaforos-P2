package semaforo.op;

public class Consumidor implements Runnable {

	BufferLimitado b = null;
	int tiempoEnConsumir = 0;
	
	public Consumidor( BufferLimitado initb ) {
		b = initb;
		//this.tiempoEnConsumir = _tiempoEnConsumir;
		new Thread( this ).start();
	}
	
	public void run() {
		double item;
		while( true ) {	
			item = b.fetch();
			System.out.println( "Artículo extraído " + item );
			Util.mySleep(2000);
		}
	}
}