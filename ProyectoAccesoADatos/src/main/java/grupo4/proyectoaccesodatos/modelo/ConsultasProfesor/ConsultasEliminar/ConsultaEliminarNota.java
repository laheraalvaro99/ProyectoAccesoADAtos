/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.modelo.ConsultasProfesor.ConsultasEliminar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diurno
 */
public class ConsultaEliminarNota {
   private Connection conexion;
    public ConsultaEliminarNota() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=Academia;"
                    + "integratedSecurity=true;"
                    + "encrypt=false;"
                    + "trustServerCertificate=true;";

             conexion = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
           Logger.getLogger(ConsultaEliminarNota.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
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
    String query = "SELECT notas.id AS nota_id, users.nombre AS alumno_nombre, users.apellido AS alumno_apellido, " +
                   "asignaturas.nombre AS asignatura, notas.puntuacion " +
                   "FROM notas " +
                   "JOIN asignaturas ON notas.id_asignatura = asignaturas.id " +
                   "JOIN users ON notas.id_user_alumno = users.id " +
                   "WHERE notas.id_user_profesor = ?";

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
      
      public boolean eliminarNota(int idNota) {
    // Primero, eliminar las filas que hacen referencia a la nota en la tabla historico_notas
    String queryHistorico = "DELETE FROM historico_notas WHERE id_notas = ?";

    try (PreparedStatement stmtHistorico = conexion.prepareStatement(queryHistorico)) {
        // Establecer el id de la nota a eliminar
        stmtHistorico.setInt(1, idNota);
        
        // Ejecutar la eliminación en historico_notas
        int filasEliminadasHistorico = stmtHistorico.executeUpdate();
        
        // Si no se eliminaron filas en historico_notas, significa que no hay referencias
        if (filasEliminadasHistorico > 0) {
            // Ahora proceder a eliminar la nota de la tabla notas
            String queryNotas = "DELETE FROM notas WHERE id = ?";
            
            try (PreparedStatement stmtNotas = conexion.prepareStatement(queryNotas)) {
                stmtNotas.setInt(1, idNota);
                int filasEliminadasNotas = stmtNotas.executeUpdate();

                // Si se eliminó correctamente, devolver true
                return filasEliminadasNotas > 0;
            }
        } else {
            // Si no se pudieron eliminar las filas en historico_notas, significa que hay un problema
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // En caso de error, devolver false
    }
}

}
