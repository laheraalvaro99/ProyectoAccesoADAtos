/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package grupo4.proyectoaccesodatos.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diurno
 */
public class Consultas {

    public List<Object[]> consulta() {
        List<Object[]> datos = new ArrayList<>();

        try {
            Connection conexion = Conexion.getConexion();

            Statement sentencia = conexion.createStatement();
            String sql = "SELECT id, id_user_alumno, id_user_profesor, id_asignatura, puntuacion FROM notas";
            ResultSet resul = sentencia.executeQuery(sql);

            while (resul.next()) {
                Object[] fila = new Object[5];
                fila[0] = resul.getInt("id");
                fila[1] = resul.getInt("id_user_alumno");
                fila[2] = resul.getInt("id_user_profesor");
                fila[3] = resul.getInt("id_asignatura");
                fila[4] = resul.getDouble("puntuacion");
                datos.add(fila);
            }

            resul.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
}
