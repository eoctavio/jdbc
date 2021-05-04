package conectabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConectaPruebas {

	public static void main(String[] args) {
		
		 String connectionUrl =
	                "jdbc:sqlserver://localhost:1434;"
	                        + "database=pruebas;"
	                        + "user=sa;"
	                        + "password=t4ct10n4dm1n;";

		
		try {
			
			Connection miConexion = DriverManager.getConnection(connectionUrl);
			
			Statement mistatement = miConexion.createStatement();
			
			ResultSet miResultset = mistatement.executeQuery("SELECT * FROM dbo.productos");
			
			while(miResultset.next()) {
				
				System.out.println(miResultset.getString("CODIGOARTICULO") + " " + miResultset.getString("NOMBREARTICULO") + " " + miResultset.getString("FECHA"));
			}
			
		}catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}

	}

}
