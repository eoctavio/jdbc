package conectabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModificaBBDD {

	public static void main(String[] args) {
		 String connectionUrl =
	                "jdbc:sqlserver://localhost:1434;"
	                        + "database=pruebas;"
	                        + "user=sa;"
	                        + "password=t4ct10n4dm1n;";

		
		try {
			
			Connection miConexion = DriverManager.getConnection(connectionUrl);
			
			Statement mistatement = miConexion.createStatement();
			
			/*String instruccionSQL = "INSERT INTO PRODUCTOS(CODIGOARTICULO, SECCION, NOMBREARTICULO, PRECIO, FECHA, IMPORTADO, PAISORIGEN) "
			+ "VALUES('AR77', 'CONFECCION', 'PATALON', 25.33, '27/04/2021', 'FALSE','MEX')"; */
			
			//String sql = "UPDATE PRODUCTOS SET PRECIO = PRECIO * 2 WHERE CODIGOARTICULO = 'AR77'";
			
			String deleteSql = "DELETE FROM PRODUCTOS WHERE CODIGOARTICULO = 'AR77'";
			
			mistatement.executeUpdate(deleteSql);
			
			//mistatement.executeUpdate(instruccionSQL);
			
			System.out.println("Datos insertados correctamente");
		}catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}

	}

}

