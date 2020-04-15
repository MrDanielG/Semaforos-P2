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
		MainWindow.setRojoMutex();
		MainWindow.setCableConsRojo();
		buffer[inBuf] = value;
		inBuf = (inBuf + 1) % size;
		mutex.V();
		MainWindow.setCableConsVerde();
		MainWindow.setVerdeMutex();	
		isEmpty.V(); // notifica a algún consumidor en espera
	}
	
	public double fetch(){
		double value;
		isEmpty.P(); // esperar si el buffer está vacío
		mutex.P(); // asegura la exclusión mutua
		MainWindow.setRojoMutex();
		MainWindow.setCableProdRojo();
		value = buffer[outBuf]; // lee desde el buffer
		outBuf = (outBuf+1) % size;
		mutex.V();
		MainWindow.setCableProdVerde();
		MainWindow.setVerdeMutex();	
		isFull.V(); // notifica a cualquier productor en espera
	
		return value;
	}
}