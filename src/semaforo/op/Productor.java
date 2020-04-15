package semaforo.op;

import java.util.Random;

public class Productor implements Runnable {

	BufferLimitado b = null;
	int tiempoEnProducir;
	
	public Productor( BufferLimitado initb, int tiempo ) {
		b = initb;
		this.tiempoEnProducir = tiempo;
		new Thread( this ).start();
	}
	
	public void run() {
		double item;
		Random r = new Random();
		while( true ) {	
			item = r.nextDouble();
			System.out.println( "Artículo producido " + item );
			b.deposit( item );
			Util.mySleep(2000);
		}
	}

}