package conectabd;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AplicacionConsulta {

	public static void main(String[] args) {
		
		JFrame miMarco = new MarcoAplicacion();
		
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		miMarco.setVisible(true);
	}

}

class MarcoAplicacion extends JFrame{
	
	private JComboBox secciones;
	private JComboBox paises;
	private JTextArea resultado;
	private PreparedStatement enviaConsultaSeccion;
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaTodos;
	private Connection  miConexion;
	private final String consultaSeccion = "SELECT NOMBREARTICULO, SECCION, PAISORIGEN "
			+ "FROM dbo.productos WHERE SECCION=?";
	private final String consultaPais = "SELECT NOMBREARTICULO, SECCION, PAISORIGEN "
			+ "FROM dbo.productos WHERE PAISORIGEN=?";
	private final String consultaTodos = "SELECT NOMBREARTICULO, SECCION, PAISORIGEN "
			+ "FROM dbo.productos WHERE SECCION=? AND PAISORIGEN=?";
	
	public MarcoAplicacion() {
		
		setTitle("Consulta BBD");
		
		setBounds(500, 300, 400, 400);
		
		setLayout(new BorderLayout());
		
		JPanel menus = new JPanel();
		
		menus.setLayout(new FlowLayout());
		
		secciones = new JComboBox();
		secciones.setEditable(false);
		secciones.addItem("Todos");
		
		paises = new JComboBox();
		paises.setEditable(false);
		paises.addItem("Todos");
		
		resultado = new JTextArea(4, 50);
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(secciones);
		menus.add(paises);
		
		add(menus, BorderLayout.NORTH);
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta = new JButton("Consulta");
		
		botonConsulta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ejecutaConsulta();
			}
		});
		
		
		add(botonConsulta, BorderLayout.SOUTH);
		
		 String connectionUrl =
	                "jdbc:sqlserver://localhost:1434;"
	                        + "database=pruebas;"
	                        + "user=sa;"
	                        + "password=t4ct10n4dm1n;";
		 
		 try {
			 
			 miConexion = DriverManager.getConnection(connectionUrl);
			 
			 Statement sentencia = miConexion.createStatement();
			 
			 String consulta = "SELECT DISTINCT(SECCION) FROM dbo.productos";
			 
			 ResultSet rs = sentencia.executeQuery(consulta);
			 
			 while(rs.next()) {
				 secciones.addItem(rs.getString(1));
			 }
			 
			 rs.close();
			 
            consulta = "SELECT DISTINCT(PAISORIGEN) FROM dbo.productos";
			 
			 rs = sentencia.executeQuery(consulta);
			 
			 while(rs.next()) {
				 paises.addItem(rs.getString(1));
			 }
			 
			 rs.close();
			 
			 
		 }catch(Exception e) {
			 
			 e.printStackTrace();
			 
		 }
		
	}
	
	private void ejecutaConsulta() {
		
		ResultSet rs = null;
		
		try {
			
			resultado.setText("");
			
			String seccion = (String)secciones.getSelectedItem();
			
			String pais = (String)paises.getSelectedItem();
			
			if(!seccion.equals("Todos") && pais.equals("Todos")) {
				enviaConsultaSeccion = miConexion.prepareStatement(consultaSeccion);
				enviaConsultaSeccion.setString(1, seccion);
				rs = enviaConsultaSeccion.executeQuery();
			} else if(seccion.equals("Todos") && !pais.equals("Todos")) {
				enviaConsultaPais = miConexion.prepareStatement(consultaPais);
				enviaConsultaPais.setString(1, pais);
				rs = enviaConsultaPais.executeQuery();
			} else if(!seccion.equals("Todos") && !pais.equals("Todos")) {
				enviaConsultaTodos = miConexion.prepareStatement(consultaTodos);
				enviaConsultaTodos.setString(1, seccion);
				enviaConsultaTodos.setString(2, pais);
				rs = enviaConsultaTodos.executeQuery();
			}
			
			while(rs.next()) {
				
				resultado.append(rs.getString(1));
				
				resultado.append(", ");
				
				resultado.append(rs.getString(2));
				
				resultado.append(", ");
				
                resultado.append(rs.getString(3));
				
				resultado.append("\n");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
