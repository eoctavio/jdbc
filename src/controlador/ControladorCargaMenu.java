package controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import modelo.CargaMenu;
import vista.VistaAplicacion;

public class ControladorCargaMenu extends WindowAdapter{
	
	CargaMenu obj = new CargaMenu();
	private VistaAplicacion elMarco;
	
	public ControladorCargaMenu(VistaAplicacion elMarco) {
		this.elMarco = elMarco;
	}
	
	public void windowOpened(WindowEvent evt) {
		obj.ejecutaConsultas();
		
		try {
			
			while(obj.rs.next()) {
				elMarco.secciones.addItem(obj.rs.getString(1));
			}
			
			while(obj.rs2.next()) {
				elMarco.paises.addItem(obj.rs2.getString(1));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
