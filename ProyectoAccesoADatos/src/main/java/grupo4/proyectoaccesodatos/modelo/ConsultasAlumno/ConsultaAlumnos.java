/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.modelo.ConsultasAlumno;

import grupo4.proyectoaccesodatos.modelo.Conexion;
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
public class ConsultaAlumnos {

    public List<Object[]> consultaAlumno(String email) {
        List<Object[]> datos = new ArrayList<>();

        try {
            Connection conexion = Conexion.getConexion();

            Statement sentencia = conexion.createStatement();

            String sql = "SELECT DISTINCT asignaturas.nombre,notas.puntuacion\n"
                    + "FROM notas\n"
                    + "INNER JOIN users ON notas.id_user_alumno = users.id\n"
                    + "INNER JOIN asignaturas ON notas.id_asignatura = asignaturas.id\n"
                    + "WHERE users.email like '" + email + "'";
            ResultSet resul = sentencia.executeQuery(sql);

            while (resul.next()) {
                Object[] fila = new Object[4];
                fila[0] = resul.getString("nombre");
                fila[1] = resul.getDouble("puntuacion");
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
