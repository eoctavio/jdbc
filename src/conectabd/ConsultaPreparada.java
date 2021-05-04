package conectabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsultaPreparada {

	public static void main(String[] args) {
		
		 String connectionUrl =
	                "jdbc:sqlserver://localhost:1434;"
	                        + "database=pruebas;"
	                        + "user=sa;"
	                        + "password=t4ct10n4dm1n;";
		
		try {
			
			Connection miConexion = DriverManager.getConnection(connectionUrl);
			
			PreparedStatement miSentencia = miConexion.prepareStatement("SELECT NOMBREARTICULO, SECCION, PAISORIGEN FROM "
					+ " PRODUCTOS WHERE SECCION=? AND PAISORIGEN=?");
			
			miSentencia.setString(1, "DEPORTES");
			miSentencia.setString(2, "USA");
			
			ResultSet rs = miSentencia.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			
			rs.close();
			
			System.out.println("EJECUCION SEGUNDA CONSULTA");
			System.out.println("");
			
			miSentencia.setString(1, "DEPORTES");
			miSentencia.setString(2, "MEX");
			
			rs = miSentencia.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			
			rs.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}

	}

}
