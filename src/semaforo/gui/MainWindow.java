package semaforo.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import semaforo.op.*;
public class MainWindow {

	protected Shell shell;
	int cantConsumir;
	int cantProducir;
	int tiempoEnConsumir;
	int tiempoEnProducir;
	
	static Image rojo = new Image(Display.getCurrent(), "semaforoRojo.png");
	static ImageData dataRojo = rojo.getImageData();
	
	Image verde = new Image(Display.getCurrent(), "semaforoVerde.png");
	ImageData dataVerde = rojo.getImageData();

	static Label semaforoProductor;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public static void setText() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				semaforoProductor.setImage(rojo);
			}
		});
	}

	/**
	 * Create contents of the window.
	 */
	public void createContents() {
		shell = new Shell();
		shell.setSize(632, 565);
		shell.setText("Semaforo P2");
		
		Label lblProcesoConsumidor = new Label(shell, SWT.NONE);
		lblProcesoConsumidor.setBounds(60, 210, 70, 20);
		lblProcesoConsumidor.setText("Consumir:");
		
		Label lblProcesoProductor = new Label(shell, SWT.BORDER | SWT.CENTER);
		lblProcesoProductor.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblProcesoProductor.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblProcesoProductor.setBounds(436, 50, 129, 130);
		lblProcesoProductor.setText("Proceso Productor");
		
		Label lblNewLabel = new Label(shell, SWT.BORDER | SWT.HORIZONTAL | SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel.setBounds(47, 50, 129, 130);
		lblNewLabel.setText("Proceso Consumidor");
		
		Label lineaConsumidor2 = new Label(shell, SWT.BORDER);
		lineaConsumidor2.setBounds(21, 110, 9, 363);
		
		Label lineaProductor2 = new Label(shell, SWT.BORDER);
		lineaProductor2.setBounds(582, 110, 9, 363);
		
		Label lineaConsumidor1 = new Label(shell, SWT.BORDER);
		lineaConsumidor1.setBounds(21, 110, 24, 10);
		
		Label lineaProductor1 = new Label(shell, SWT.BORDER);
		lineaProductor1.setBounds(567, 110, 24, 10);
		
		Label semaforoConsumidor = new Label(shell, SWT.BORDER);
		semaforoConsumidor.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		semaforoConsumidor.setBounds(190, 69, 70, 111);
		
		Label lblProducir = new Label(shell, SWT.NONE);
		lblProducir.setBounds(376, 210, 70, 20);
		lblProducir.setText("Producir:");
		
		semaforoProductor = new Label(shell, SWT.BORDER);
		semaforoProductor.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		semaforoProductor.setBounds(350, 50, 78, 130);
		
		Label semaforoMutex = new Label(shell, SWT.BORDER);
		semaforoMutex.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		semaforoMutex.setBounds(267, 397, 70, 111);
		
		Label lineaConsumidor3 = new Label(shell, SWT.BORDER);
		lineaConsumidor3.setBounds(21, 463, 266, 10);
		
		Label lineaProductor3 = new Label(shell, SWT.BORDER);
		lineaProductor3.setBounds(325, 463, 266, 10);
		
		Label lblTiempoDeConsumo = new Label(shell, SWT.WRAP);
		lblTiempoDeConsumo.setText("Tiempo de Consumo (ms):");
		lblTiempoDeConsumo.setBounds(55, 275, 107, 39);
		
		Spinner spinnerConsumir = new Spinner(shell, SWT.BORDER);
		spinnerConsumir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cantConsumir = spinnerConsumir.getSelection();
			}
		});
		spinnerConsumir.setMinimum(1);
		spinnerConsumir.setBounds(168, 207, 59, 26);
		
		Spinner spinnerProducir = new Spinner(shell, SWT.BORDER);
		spinnerProducir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cantProducir = spinnerProducir.getSelection();
			}
		});
		spinnerProducir.setMinimum(1);
		spinnerProducir.setBounds(506, 210, 59, 26);
		
		Button btnIniciar = new Button(shell, SWT.NONE);
		btnIniciar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BufferLimitado buffer = new BufferLimitado();
				Productor productor = new Productor( buffer );
				Consumidor consumidor = new Consumidor( buffer );
			}
		});
		btnIniciar.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnIniciar.setBounds(267, 206, 90, 30);
		btnIniciar.setText("Iniciar");
		
		Spinner spinnerTiempoCons = new Spinner(shell, SWT.BORDER);
		spinnerTiempoCons.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tiempoEnConsumir = spinnerTiempoCons.getSelection();
			}
		});
		spinnerTiempoCons.setMaximum(10000);
		spinnerTiempoCons.setMinimum(10);
		spinnerTiempoCons.setBounds(168, 288, 59, 26);
		
		
		Spinner spinnerTiempoProd = new Spinner(shell, SWT.BORDER);
		spinnerTiempoProd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tiempoEnProducir = spinnerTiempoProd.getSelection();
			}
		});
		spinnerTiempoProd.setMaximum(10000);
		spinnerTiempoProd.setMinimum(10);
		spinnerTiempoProd.setBounds(506, 288, 59, 26);
		
		ProgressBar progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setBounds(60, 354, 505, 21);
		
	}
	

}
