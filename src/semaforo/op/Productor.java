package semaforo.op;

import java.util.Random;
public class Productor implements Runnable {

	BufferLimitado b = null;
	
	public Productor( BufferLimitado initb ) {
		b = initb;
		new Thread( this ).start();
	}
	
	public void run() {
		double item;
		Random r = new Random();
		while( true ) {	
			item = r.nextDouble();
			System.out.println( "Artículo producido " + item );
			b.deposit( item );
			Util.mySleep(4000);
		}
	}

}