package menualterar2amtbis;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
	static String []camposAlumno= {"IDN","APENOM","DIRECC","PROV","EMAIL","FECHA","HORA","MARCATIEMPO"};
	public static void main(String[] args) throws SQLException {
			
			//creamos int para el menu
			int elec = 0;
			Scanner sc = new Scanner(System.in);
			//creamos la conexion
			Connection conn;
			//"jdbc:mariadb://localhost:3306/db2amtbis","root","root"
			//nos conectamos a nuestra bbdd
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db2amtbis","root","root");
			DatabaseMetaData dm=conn.getMetaData();
			Statement st = conn.createStatement();
			System.out.println("dd");
			do {
				System.out.println("Introduce opcion : \n1.-Inserta alumno \n2.-Elimina Alumno \n3 Actualiza alumno \n4.-Muestra Alumno ");
				elec=sc.nextInt();
				switch(elec) {
				case 1 :
					insertarAlumno(conn,st);
					break;
				case 2 :
					eliminaAlumno(conn,st);
					break;
				case 3 :
					actualizaAlumno(conn,st);
					break;
				case 4 :
					muestraAlumno(conn,st);
					break;
				case 5 :
					System.out.println("Adios");
					break;
				}
				
			}while(elec!=5);
			
			st.close();
			conn.close();
			

	}

	private static void muestraAlumno(Connection con,Statement st) throws SQLException {
		String dni;
		dni = JOptionPane.showInputDialog("Introduce el dni del Alumno a mostrar  : ");
		String ssql = "SELECT * FROM ALUMNOS WHERE idn = "+dni;
		ResultSet rs = st.executeQuery(ssql);
		while(rs.next()) {
			System.out.printf("%1s  %32s  %32s    %16s   %1s\n", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
		}
	}

	private static void actualizaAlumno(Connection con,Statement st) throws SQLException {
		String dni;
		String terminar = "salir";
		String campo;
		String nValor;
		LocalDate DATE = LocalDate.now();
		LocalTime TIME = LocalTime.now();
		Long datetime = System.currentTimeMillis();
		Timestamp TIMESTAMP= new Timestamp(datetime);
		
		do {
			dni = JOptionPane.showInputDialog("Introduce el dni del Alumno a modificar  : ");
			campo = JOptionPane.showInputDialog("Introduce el campo del Alumno a modificar o salir para terminar  : \ndni\nnombre\ndireccion\nprovincia\nemail\nsalir");
			nValor=JOptionPane.showInputDialog("Introduce nuevo valor del campo : ");
			
			switch(campo) {
			case "dni":
				st.executeQuery("UPDATE alumnos SET IDN='"+nValor+"'WHERE IDN ='"+dni+"'");
				st.executeQuery("UPDATE alumnos SET FECHA='"+DATE+"'");
				st.executeQuery("UPDATE alumnos SET HORA='"+TIME+"'");
				st.executeQuery("UPDATE alumnos SET MARCATIEMPO='"+TIMESTAMP+"'");
				break;
			case "nombre":
				st.executeQuery("UPDATE alumnos SET APENOM='"+nValor+"'WHERE IDN ='"+dni+"'");
				st.executeQuery("UPDATE alumnos SET FECHA='"+DATE+"'");
				st.executeQuery("UPDATE alumnos SET HORA='"+TIME+"'");
				st.executeQuery("UPDATE alumnos SET MARCATIEMPO='"+TIMESTAMP+"'");
				break;
			case "direccion":
				
				st.executeQuery("UPDATE alumnos SET DIRECC='"+nValor+"'WHERE IDN ='"+dni+"'");
				st.executeQuery("UPDATE alumnos SET FECHA='"+DATE+"'");
				st.executeQuery("UPDATE alumnos SET HORA='"+TIME+"'");
				st.executeQuery("UPDATE alumnos SET MARCATIEMPO='"+TIMESTAMP+"'");
				break;
			case "provincia":
				
				st.executeQuery("UPDATE alumnos SET PROV='"+nValor+"'WHERE IDN ='"+dni+"'");
				st.executeQuery("UPDATE alumnos SET FECHA='"+DATE+"'");
				st.executeQuery("UPDATE alumnos SET HORA='"+TIME+"'");
				st.executeQuery("UPDATE alumnos SET MARCATIEMPO='"+TIMESTAMP+"'");
				break;
			case "email":
				
				st.executeQuery("UPDATE alumnos SET EMAIL='"+nValor+"'WHERE IDN ='"+dni+"'");
				st.executeQuery("UPDATE alumnos SET FECHA='"+DATE+"'");
				st.executeQuery("UPDATE alumnos SET HORA='"+TIME+"'");
				st.executeQuery("UPDATE alumnos SET MARCATIEMPO='"+TIMESTAMP+"'");
				break;
			case "salir":
				System.out.println("adios");
				break;
			}
			
		}while(dni==terminar);
		
		
		
		
	}

	private static void eliminaAlumno(Connection con,Statement st) throws SQLException {
		
		String exit="salir";
		String opcion;
		String dni,apenom,dir,pro,ema;
		do {
			
			opcion=JOptionPane.showInputDialog("Introduce campo a traves del cual eliminar alumno o salir para terminar : \ndni\nnombre\ndireccion\nprovincia\nemail\nsalir");
			
			switch (opcion){
			case "dni":
				dni= (JOptionPane.showInputDialog("Introduce el dni"));
				st.executeQuery("DELETE FROM alumnos WHERE IDN='"+dni+"'");
				break;
			case "nombre":
				apenom= JOptionPane.showInputDialog("Introduce el apellido y nombre en este orden");
				st.executeQuery("DELETE FROM alumnos WHERE APENOM='"+apenom+"'");
				break;
			case "direccion":
				dir= JOptionPane.showInputDialog("Introduce la direccion formato : calle,numero");
				st.executeQuery("DELETE FROM alumnos WHERE DIRECC='"+dir+"'");
				break;
			case "provincia":
				pro= JOptionPane.showInputDialog("Introduce la provincia");
				st.executeQuery("DELETE FROM alumnos WHERE PROV='"+pro+"'");
				break;
			case "email":
				ema= JOptionPane.showInputDialog("Introduce el email");
				st.executeQuery("DELETE FROM alumnos WHERE EMAIL='"+ema+"'");
				break;
			case "salir":
				System.out.println("adios");
				break;
			}
			
		}while(opcion==exit);
		
	}

	private static void insertarAlumno(Connection con,Statement st) throws SQLException {
		st = con.createStatement();
		String ntabla,dni,apenom,dir,pro,ema;
		String sentencia;
		LocalDate DATE = LocalDate.now();
		LocalTime TIME = LocalTime.now();
		Long datetime = System.currentTimeMillis();
		Timestamp TIMESTAMP= new Timestamp(datetime);
		ntabla= JOptionPane.showInputDialog("Introduce la tabla sobre la cual realizar los cambios");
		switch(ntabla) {
		case "alumnos":
			System.out.println("Vaya introduciendo campos para instertar nuevo Alumnos");
			
			dni= JOptionPane.showInputDialog("Introduce el dni");
			
			apenom= JOptionPane.showInputDialog("Introduce el apellido y nombre en este orden");
			
			dir= JOptionPane.showInputDialog("Introduce la direccion , calle y numero");
			
			pro= JOptionPane.showInputDialog("Introduce la provincia");
			
			ema= JOptionPane.showInputDialog("Introduce el email");
			sentencia="INSERT INTO "+ntabla+" ("+camposAlumno[0]+","
			+camposAlumno[1]+","+camposAlumno[2]+","+camposAlumno[3]+","+camposAlumno[4]+","+camposAlumno[5]+","+camposAlumno[6]+","+camposAlumno[7]+") VALUES "
					+ "('"+dni+"','"+apenom+ "','"+dir+"','"+pro+"','"+ema+"','"+DATE+"','"+TIME+"','"+TIMESTAMP+"')";
			st.executeQuery(sentencia);
			break;
		}
			
			
			
		
		// TODO Auto-generated method stub
		
	}

}
