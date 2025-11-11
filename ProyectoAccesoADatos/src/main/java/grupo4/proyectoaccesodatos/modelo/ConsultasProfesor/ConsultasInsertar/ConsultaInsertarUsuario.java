/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.modelo.ConsultasProfesor.ConsultasInsertar;

import grupo4.proyectoaccesodatos.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Diurno
 */
public class ConsultaInsertarUsuario {

    public void insertarUsuario(String nombre, String apellido, String rol) {
        try (Connection conexion = Conexion.getConexion();
                PreparedStatement pstmt = conexion.prepareStatement(
                "INSERT INTO users (nombre, apellido, rol) VALUES (?, ?, ?)")
        ) {

            // Establecer los valores para la consulta
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, rol);

            // Ejecutar la consulta
            int filasAfectadas = pstmt.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Usuario insertado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo insertar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la inserción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
