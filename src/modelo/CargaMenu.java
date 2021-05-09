package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class CargaMenu {

	public Conexion miConexion;
	public ResultSet rs;
	public ResultSet rs2;

	public CargaMenu() {
		miConexion = new Conexion();
	}

	public String ejecutaConsultas() {
		Productos miProducto = null;
		Connection accesoBD = miConexion.dameConexion();

		try {

			Statement secciones = accesoBD.createStatement();
			Statement paises = accesoBD.createStatement();
			
			rs = secciones.executeQuery("SELECT DISTINCT(SECCION) FROM PRODUCTOS");
			rs2 = paises.executeQuery("SELECT DISTINCT(PAISORIGEN) FROM PRODUCTOS");
			
				miProducto = new Productos();
				miProducto.setSeccion(rs.getString(1));
				miProducto.setpOrigen(rs2.getString(1));

			rs.close();
			rs2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return miProducto.getSeccion();
	} 
	
/*	public ResultSet ejecutaConsultas() {
		Connection accesoBBDD =  miConexion.dameConexion();
		try {
			Statement secciones = accesoBBDD.createStatement();
			return rs = secciones.executeQuery("SELECT DISTINCT(SECCION) FROM PRODUCTOS");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	} */

}
