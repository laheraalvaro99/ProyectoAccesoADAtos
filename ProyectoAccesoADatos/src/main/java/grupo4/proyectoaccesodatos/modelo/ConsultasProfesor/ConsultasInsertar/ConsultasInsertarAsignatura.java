/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.modelo.ConsultasProfesor.ConsultasInsertar;

import grupo4.proyectoaccesodatos.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Diurno
 */
public class ConsultasInsertarAsignatura {

    public void insertarAsignatura(String usuario, String nAsignatura, String nCurso) {
        try (Connection conexion = Conexion.getConexion(); PreparedStatement pstmt = conexion.prepareStatement(
                "INSERT INTO asignaturas (nombre, curso, id_user_profesor) VALUES (?, ?, ?)")) {

            // Asignar parámetros
            pstmt.setString(1, nAsignatura);
            pstmt.setString(2, nCurso + "º");  // Aquí pasamos el curso sin el "º"
            pstmt.setInt(3, codigoUsrPorEmail(usuario));  // Llamamos a la función para obtener el ID del profesor

            // Ejecutar la inserción
            int filasAfectadas = pstmt.executeUpdate();

            // Verificar si se insertaron filas
            if (filasAfectadas > 0) {
                // Si la inserción fue exitosa, mostramos un mensaje de éxito
                JOptionPane.showMessageDialog(null, "Asignatura insertada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Si no se insertaron filas, mostramos un mensaje de error
                JOptionPane.showMessageDialog(null, "No se pudo insertar la asignatura", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la inserción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static int codigoUsrPorEmail(String email) {
        int idUsuario = 0;

        // Usamos PreparedStatement para mayor seguridad
        try (Connection conexion = Conexion.getConexion(); PreparedStatement pstmt = conexion.prepareStatement(
                "SELECT id FROM users WHERE email = ?")) {

            pstmt.setString(1, email);
            ResultSet resul = pstmt.executeQuery();

            if (resul.next()) {
                idUsuario = resul.getInt("id");
            } else {
                System.out.println("Usuario no encontrado con el email: " + email);
            }

            resul.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idUsuario;
    }

    public static int codigoProfesor(String nombre, String apellido) {
        int datos = 0;

        try (Connection conexion = Conexion.getConexion(); Statement sentencia = conexion.createStatement()) {

            // Buscar sin modificar los nombres
            String sql = "SELECT id FROM users WHERE nombre='" + nombre + "' AND apellido='" + apellido + "'";
            ResultSet resul = sentencia.executeQuery(sql);

            if (resul.next()) {
                datos = resul.getInt("id");
            } else {
                System.out.println("Profesor no encontrado.");
            }

            resul.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }

    public static String reemplazarVocales(String texto) {

        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if ("AEIOUaeiou".indexOf(c) != -1) {
                resultado.append('%');
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

}
