/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.modelo.ConsultasAlumno;

import grupo4.proyectoaccesodatos.modelo.Consultas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diurno
 */
public class ConsultaAlumnos {
    
    public List<Object[]> consultaAlumno(String email) {
        List<Object[]> datos = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=Academia;"
                    + "integratedSecurity=true;"
                    + "encrypt=false;"
                    + "trustServerCertificate=true;";

            Connection conexion = DriverManager.getConnection(url);

            Statement sentencia = conexion.createStatement();

            String sql ="SELECT DISTINCT asignaturas.nombre,notas.puntuacion\n" +
                        "FROM notas\n" +
                        "INNER JOIN users ON notas.id_user_alumno = users.id\n" +
                        "INNER JOIN asignaturas ON notas.id_asignatura = asignaturas.id\n" +
                        "WHERE users.email like '"+email+"'" ;
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;
    }
    
    
}
