package examenFinalFAMM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fifa {
	// Atributos
		private static Connection con;
		private static Fifa INSTANCE = null;

		// Constructor
		private Fifa() {

		}

		// crear instancia
		private synchronized static void crearInstancia() {
			if (INSTANCE == null) {
				INSTANCE = new Fifa();
				crearConexion();
			}
		}

		// obtener instancia
		public static Fifa getInstancia() {
			if (INSTANCE == null) {
				crearInstancia();
			}
			return INSTANCE;
		}

		// crear conexion
		private static void crearConexion() {
			String host = "127.0.0.1";
			String user = "zeus";
			String password = "Zeus11111";
			String dataBase = "fifa";
			try {
				// importando la libreria de conexion de mysqul

				Class.forName("com.mysql.jdbc.Driver");
				String urlConexion = "jdbc:mysql://" + host + "/" + dataBase + "?user=" + user + "&password=" + password;
				con = DriverManager.getConnection(urlConexion);
				System.out.println("Lo lograste :-)");
			} catch (Exception e) {
				System.out.println("Error al conectar a la base de datos");
			}
		}

		//-- a) Contar el numero de argentinos que juegan en FC Barcelona
		public ArrayList<String> conteo() throws SQLException {
			ArrayList<String> conteo = new ArrayList<String>();
			PreparedStatement ps = con.prepareStatement("select count(personal_details.id) from personal_details " + 
					"inner join other_details on personal_details.player_id = other_details.player_id and other_details.club = 'FC Barcelona' and personal_details.nationality = 'Argentina'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				conteo.add(rs.getString("count(personal_details.id)")) ;
			}
			rs.close();
			return conteo;
		}
		// -- b) Mostrar los salarios y nombres de los jugadores que ganan menos que el promedio.

		public ArrayList<String> getNombreYSalario() throws SQLException {
			ArrayList<String> nombreYSalario = new ArrayList<String>();
			PreparedStatement ps = con.prepareStatement("select salary.value as Salario, personal_details.player_name as Nombre from salary " + 
					"inner join personal_details on (salary.player_id=personal_details.player_id ) " + 
					"WHERE value < (SELECT AVG(salary.value) FROM salary)");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nombreYSalario.add(rs.getString("Salario") + " " + rs.getString("Nombre"));
			}
			rs.close();
			return nombreYSalario;
		}
		// -- c) Obtener los nombres de los jugadores que tienen un control de la pelota debajo del promedio
		public ArrayList<String> getNombreControl() throws SQLException {
			ArrayList<String> nombre = new ArrayList<String>();
			PreparedStatement ps = con.prepareStatement("select personal_details.player_name as Nombre from personal_details " + 
					"inner join player_stats on personal_details.player_id=player_stats.player_id " + 
					"WHERE ball_control < (SELECT AVG(player_stats.ball_control) FROM player_stats) ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nombre.add(rs.getString("Nombre")) ;
			}
			rs.close();
			return nombre;
		}
}
