package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import controlador.Conexion;

public class CargarSecciones {

	Conexion miConexion;
	private ResultSet rs;

	public CargarSecciones() {
		miConexion = new Conexion();
	}

	public String ejecutaConsultas() {
		Productos miProducto = null;
		Connection accesoBD = miConexion.dameConexion();

		try {

			Statement secciones = accesoBD.createStatement();
			rs = secciones.executeQuery("SELECT DISTINCT(SECCION) FROM PRODUCTOS");

			while (rs.next()) {
				miProducto = new Productos();
				miProducto.setSeccion(rs.getString(1));
				return miProducto.getSeccion();
			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return miProducto.getSeccion();
	}

}
