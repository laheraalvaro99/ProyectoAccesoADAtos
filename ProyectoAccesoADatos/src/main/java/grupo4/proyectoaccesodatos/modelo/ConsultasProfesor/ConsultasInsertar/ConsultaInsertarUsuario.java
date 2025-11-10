/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.modelo.ConsultasProfesor.ConsultasInsertar;

import grupo4.proyectoaccesodatos.modelo.Consultas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Diurno
 */
public class ConsultaInsertarUsuario {
   public void insertarUsuario(String nombre, String apellido, String rol) {
        try {
            // Cargar el controlador JDBC de SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // URL de conexión con la base de datos
            String url = "jdbc:sqlserver://localhost:1433;"
                         + "databaseName=Academia;"
                         + "integratedSecurity=true;"
                         + "encrypt=false;"
                         + "trustServerCertificate=true;";

            try (Connection conexion = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conexion.prepareStatement(
                     "INSERT INTO users (nombre, apellido, rol) VALUES (?, ?, ?)"
                 )) {

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

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
