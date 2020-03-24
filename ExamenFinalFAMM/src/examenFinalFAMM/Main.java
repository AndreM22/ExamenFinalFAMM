package examenFinalFAMM;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {		
				Fifa instancia = Fifa.getInstancia();
				try {
					//inciso a)
					ArrayList<String> conteo = instancia.conteo();
					System.out.println("a) Contar el numero de argentinos que juegan en FC Barcelona");
					for (String i : conteo) {
						System.out.println(i);
					}
					//inciso b)
					System.out.println("b) Mostrar los salarios y nombres de los jugadores que ganan menos que el promedio.");
					ArrayList<String> nombreYSalario = instancia.getNombreYSalario();
					for (String i : nombreYSalario) {
						System.out.println(i);
					}
					//inciso c)
					System.out.println("c) Obtener los nombres de los jugadores que tienen un control de la pelota debajo del promedio");
					ArrayList<String> nombre = instancia.getNombreControl();
					for (String i : nombre) {
						System.out.println(i);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}

}
