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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author Diurno
 */
public class ConsultaInsertarNotas {

    public void insertarNotas(String nombreA, String apellidoA, String nAsignatura, String email, double nota) {
        // Obtener los códigos de los usuarios y asignaturas
        int codAlumno = codigoUsr(nombreA, apellidoA);
        int codProfesor = codigoUsrPorEmail(email);
        int asignatura = codigoAsignatura(nAsignatura);
        // Verificar si la asignatura existe
        if (asignatura == 0) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar la asignatura en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;  // Salir del método si la asignatura no existe
        }
        // Obtener la fecha actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        Timestamp fecha = Timestamp.valueOf(fechaHoraActual);
        try (Connection conexion = Conexion.getConexion(); PreparedStatement pstmt = conexion.prepareStatement(
                "INSERT INTO notas (id_user_alumno, id_user_profesor, id_asignatura, puntuacion, fecha_registro) VALUES (?, ?, ?, ?, ?)"
        )) {

            // Establecer los valores para la consulta
            pstmt.setInt(1, codAlumno);
            pstmt.setInt(2, codProfesor);
            pstmt.setInt(3, asignatura);
            pstmt.setDouble(4, nota);
            pstmt.setTimestamp(5, fecha);

            // Ejecutar la consulta
            int filasAfectadas = pstmt.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Nota insertada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo insertar la nota", "Error", JOptionPane.ERROR_MESSAGE);
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

    // Método para obtener el código de un usuario (alumno o profesor) por nombre y apellido
    public static int codigoUsr(String nombre, String apellido) {
        int datos = 0;

        // Usamos PreparedStatement para evitar problemas con los caracteres especiales
        try (Connection conexion = Conexion.getConexion(); PreparedStatement pstmt = conexion.prepareStatement("SELECT id FROM users WHERE nombre = ? AND apellido = ?")) {

            // Establecemos los parámetros
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);

            ResultSet resul = pstmt.executeQuery();

            if (resul.next()) {
                datos = resul.getInt("id");
            } else {
                System.out.println("Usuario no encontrado.");
            }

            resul.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }

    // Método para obtener el código de la asignatura por nombre
    public static int codigoAsignatura(String nombre) {
        int datos = 0;

        // Usamos PreparedStatement para evitar problemas con los caracteres especiales
        try (Connection conexion = Conexion.getConexion(); PreparedStatement pstmt = conexion.prepareStatement("SELECT id FROM asignaturas WHERE nombre = ?")) {

            // Establecemos el parámetro
            pstmt.setString(1, nombre);

            ResultSet resul = pstmt.executeQuery();

            if (resul.next()) {
                datos = resul.getInt("id");
            } else {
                System.out.println("Asignatura no encontrada.");
            }

            resul.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
}
