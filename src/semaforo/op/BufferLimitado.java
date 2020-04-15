package semaforo.op;

import semaforo.gui.MainWindow;

public class BufferLimitado {

	final int size = 10;
	double buffer[] = new double[size];
	int inBuf = 0, outBuf = 0;
	
	SemaforoBinario mutex = new SemaforoBinario(true);
	SemaforoContador isEmpty = new SemaforoContador(0);
	SemaforoContador isFull = new SemaforoContador( size );
	
	public void deposit( double value ) {
		isFull.P(); // espera si el buffer está lleno
		mutex.P(); // asegura la exclusión mutua
		buffer[inBuf] = value;
		inBuf = (inBuf + 1) % size;
		mutex.V();
		isEmpty.V(); // notifica a algún consumidor en espera
		MainWindow.setText();
	}
	
	public double fetch(){
		double value;
		isEmpty.P(); // esperar si el buffer está vacío
		mutex.P(); // asegura la exclusión mutua
		value = buffer[outBuf]; // lee desde el buffer
		outBuf = (outBuf+1) % size;
		mutex.V();
		isFull.V(); // notifica a cualquier productor en espera
		return value;
	}
}