package semaforo.op;

import semaforo.gui.MainWindow;

public class Consumidor implements Runnable {

	BufferLimitado b = null;
	int tiempoEnConsumir;
	int cantConsumir;
	int consumidos = 0;
	
	public Consumidor( BufferLimitado initb, int _tiempoEnConsumir, int _cantConsumir ) {
		this.b = initb;
		this.tiempoEnConsumir = _tiempoEnConsumir;
		this.cantConsumir = _cantConsumir;
		new Thread( this ).start();
	}
	
	public void run() {
		double item;
		while( consumidos < cantConsumir ) {	
			item = b.fetch();
			System.out.println( "Artículo extraído " + item );
			consumidos++;
			MainWindow.setRojoSC();
			Util.mySleep(tiempoEnConsumir);
		}
	}
}