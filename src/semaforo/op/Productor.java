package semaforo.op;

import java.util.Random;

import semaforo.gui.MainWindow;

public class Productor implements Runnable {

	BufferLimitado b = null;
	int tiempoEnProducir;
	int cantProducir;
	int producidos = 0;
	
	public Productor( BufferLimitado initb, int tiempo, int _cantProd ) {
		this.b = initb;
		this.tiempoEnProducir = tiempo;
		this.cantProducir = _cantProd;
		new Thread( this ).start();
	}
	
	public void run() {
		double item;
		Random r = new Random();
		while( producidos < cantProducir ) {	
			item = r.nextDouble();
			System.out.println( "Artículo producido " + item );
			b.deposit( item );
			
			producidos++;
			
			MainWindow.setRojoSP();
			Util.mySleep(tiempoEnProducir);
		}
	}

}