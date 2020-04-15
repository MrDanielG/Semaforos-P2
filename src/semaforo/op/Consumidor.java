package semaforo.op;

import semaforo.gui.MainWindow;

public class Consumidor implements Runnable {

	BufferLimitado b = null;
	int tiempoEnConsumir;
	
	public Consumidor( BufferLimitado initb, int _tiempoEnConsumir ) {
		b = initb;
		this.tiempoEnConsumir = _tiempoEnConsumir;
		new Thread( this ).start();
	}
	
	public void run() {
		double item;
		while( true ) {	
			item = b.fetch();
			System.out.println( "Art�culo extra�do " + item );
			MainWindow.setRojoSC();
			Util.mySleep(tiempoEnConsumir);
		}
	}
}