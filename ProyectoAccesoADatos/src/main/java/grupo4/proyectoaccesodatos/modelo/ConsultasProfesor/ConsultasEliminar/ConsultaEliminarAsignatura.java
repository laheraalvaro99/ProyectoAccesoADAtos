/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.modelo.ConsultasProfesor.ConsultasEliminar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Diurno
 */
public class ConsultaEliminarAsignatura {
    public boolean eliminarAsignaturaPorNombre(String nombreAsignatura, JFrame ventana) {
        boolean eliminado = false;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=Academia;"
                    + "integratedSecurity=true;"
                    + "encrypt=false;"
                    + "trustServerCertificate=true;";

            Connection conexion = DriverManager.getConnection(url);

            // Usar PreparedStatement para evitar inyecciones SQL
            String sql = "DELETE FROM asignaturas WHERE nombre = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombreAsignatura);

            // Ejecutar la eliminación
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                eliminado = true;  // La asignatura fue eliminada
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(ventana, "Asignatura eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                // Cerrar la ventana
                ventana.dispose();
            } else {
                // Si no se eliminó, mostrar mensaje de error
                JOptionPane.showMessageDialog(ventana, "No se encontró la asignatura para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            stmt.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return eliminado;
    }
}
