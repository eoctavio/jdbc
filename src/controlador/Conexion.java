package controlador;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	private Connection  miConexion = null;
	private String connectionUrl =
             "jdbc:sqlserver://localhost:1434;"
                     + "database=pruebas;"
                     + "user=sa;"
                     + "password=t4ct10n4dm1n;";
	
	public Conexion() {
		// TODO Auto-generated constructor stub
	}
	
	public Connection dameConexion() {
		try {
			
			 miConexion = DriverManager.getConnection(connectionUrl);
			
			
		} catch(Exception e) {
			
		}
		
		return miConexion;
	}

}
