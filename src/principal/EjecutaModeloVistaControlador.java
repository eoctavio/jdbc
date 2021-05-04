package principal;

import javax.swing.JFrame;

import vista.VistaAplicacion;

public class EjecutaModeloVistaControlador {

	public static void main(String[] args) {
		
		VistaAplicacion miVista = new VistaAplicacion();
		
		miVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		miVista.setVisible(true);

	}

}
