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
import org.eclipse.swt.graphics.Color;
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
	
	static Image verde = new Image(Display.getCurrent(), "semaforoVerde.png");
	ImageData dataVerde = rojo.getImageData();
	
	static Color red = new Color (Display.getCurrent(), 255, 0, 0);
	static Color green = new Color (Display.getCurrent(), 0, 255, 0);

	static Label semaforoProductor;
	static Label semaforoMutex;
	static Label semaforoConsumidor;
	
	static Label lineaConsumidor1;
	static Label lineaConsumidor2;
	static Label lineaConsumidor3;
	
	static Label lineaProductor1;
	static Label lineaProductor2;
	static Label lineaProductor3;
	
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
	public static void setRojoMutex() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				Image rojo = new Image(Display.getCurrent(), "semaforoRojo.png");
				semaforoMutex.setImage(rojo);
			}
		});
	}
	
	public static void setVerdeMutex() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				Image verde = new Image(Display.getCurrent(), "semaforoVerde.png");
				semaforoMutex.setImage(verde);
			}
		});
	}
	
	public static void setRojoSP() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				semaforoProductor.setImage(rojo);
				semaforoConsumidor.setImage(verde);
			}
		});
	}
	
	public static void setRojoSC() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				semaforoConsumidor.setImage(rojo);
				semaforoProductor.setImage(verde);
			}
		});
	}
	
	public static void setCableConsRojo() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				lineaConsumidor1.setBackground(red);
				lineaConsumidor2.setBackground(red);
				lineaConsumidor3.setBackground(red);
			}
		});
	}
	
	public static void setCableConsVerde() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				lineaConsumidor1.setBackground(green);
				lineaConsumidor2.setBackground(green);
				lineaConsumidor3.setBackground(green);
			}
		});
	}
	
	public static void setCableProdRojo() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				lineaProductor1.setBackground(red);
				lineaProductor2.setBackground(red);
				lineaProductor3.setBackground(red);
			}
		});
	}
	
	public static void setCableProdVerde() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				lineaProductor1.setBackground(green);
				lineaProductor2.setBackground(green);
				lineaProductor3.setBackground(green);
			}
		});
	}

	/**
	 * Create contents of the window.
	 */
	public void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		shell.setSize(632, 565);
		shell.setText("Semaforo P2");
		
		semaforoMutex = new Label(shell, SWT.NONE);
		semaforoMutex.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		semaforoMutex.setBounds(267, 397, 70, 111);
		
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
		
		lineaConsumidor1 = new Label(shell, SWT.NONE);
		lineaConsumidor1.setBounds(21, 110, 24, 10);
		
		lineaProductor1 = new Label(shell, SWT.NONE);
		lineaProductor1.setBounds(567, 110, 24, 10);
		
		lineaConsumidor2 = new Label(shell, SWT.NONE);
		lineaConsumidor2.setBounds(21, 110, 9, 363);
		
		lineaProductor2 = new Label(shell, SWT.NONE);
		lineaProductor2.setBounds(582, 110, 9, 363);
		
		lineaConsumidor3 = new Label(shell, SWT.NONE);
		lineaConsumidor3.setBounds(21, 463, 266, 10);
		
		lineaProductor3 = new Label(shell, SWT.NONE);
		lineaProductor3.setBounds(325, 463, 266, 10);
		
		semaforoConsumidor = new Label(shell, SWT.NONE);
		semaforoConsumidor.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		semaforoConsumidor.setBounds(182, 67, 80, 100);
		
		Label lblProducir = new Label(shell, SWT.NONE);
		lblProducir.setBounds(376, 210, 70, 20);
		lblProducir.setText("Producir:");
		
		semaforoProductor = new Label(shell, SWT.NONE);
		semaforoProductor.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		semaforoProductor.setBounds(350, 67, 80, 100);
		
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
				Productor productor = new Productor( buffer, tiempoEnProducir );
				Consumidor consumidor = new Consumidor( buffer, tiempoEnConsumir );
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
		spinnerTiempoCons.setBounds(168, 288, 70, 26);
		spinnerTiempoCons.setSelection(2000);
		
		
		Spinner spinnerTiempoProd = new Spinner(shell, SWT.BORDER);
		spinnerTiempoProd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tiempoEnProducir = spinnerTiempoProd.getSelection();
			}
		});
		spinnerTiempoProd.setMaximum(10000);
		spinnerTiempoProd.setMinimum(10);
		spinnerTiempoProd.setBounds(489, 288, 76, 26);
		spinnerTiempoProd.setSelection(2000);
		
		ProgressBar progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setBounds(60, 354, 505, 21);
		
		Label lblTiempoDeProuccin = new Label(shell, SWT.WRAP);
		lblTiempoDeProuccin.setBounds(376, 275, 107, 49);
		lblTiempoDeProuccin.setText("Tiempo de Proucci\u00F3n (ms):");
		
	}
}
