/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.modelo.ConsultasProfesor.ConsultasConsultar;

import grupo4.proyectoaccesodatos.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diurno
 */
public class ConsultarNota {

    private Connection conexion;

    public ConsultarNota() {
        conexion = Conexion.getConexion();
    }

    public int obtenerIdUsuarioPorEmail(String email) {
        String query = "SELECT users.id FROM users WHERE users.email = ?";
        int usuarioId = -1;

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            // Si encontramos el resultado, obtenemos el id
            if (rs.next()) {
                usuarioId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioId;
    }

    public Object[][] obtenerNotasPorProfesor(int profesorId) {
        String query = "SELECT notas.id AS nota_id, users.nombre AS alumno_nombre, users.apellido AS alumno_apellido, "
                + "asignaturas.nombre AS asignatura, notas.puntuacion "
                + "FROM notas "
                + "JOIN asignaturas ON notas.id_asignatura = asignaturas.id "
                + "JOIN users ON notas.id_user_alumno = users.id "
                + "WHERE notas.id_user_profesor = ?";

        // Usamos una lista para almacenar los resultados dinámicamente
        List<Object[]> resultados = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, profesorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[5];  // Ahora tenemos 5 columnas
                fila[0] = rs.getInt("nota_id");  // ID de la nota
                fila[1] = rs.getString("alumno_nombre");
                fila[2] = rs.getString("alumno_apellido");
                fila[3] = rs.getString("asignatura");
                fila[4] = rs.getString("puntuacion");
                resultados.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convertir la lista a un arreglo de objetos
        return resultados.toArray(new Object[0][0]);
    }
}
