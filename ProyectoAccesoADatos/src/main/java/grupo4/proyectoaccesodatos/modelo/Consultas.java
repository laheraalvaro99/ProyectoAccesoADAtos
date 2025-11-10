/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package grupo4.proyectoaccesodatos.modelo;

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
public class Consultas {

    public List<Object[]> consulta() {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;
    }
    }

