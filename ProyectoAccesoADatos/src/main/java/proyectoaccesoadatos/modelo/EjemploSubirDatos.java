/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoaccesoadatos.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EjemploSubirDatos {
public static void main(String[] args) {
        try {
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;"
           + "databaseName=Academia;"
           + "integratedSecurity=true;"
           + "encrypt=false;"
           + "trustServerCertificate=true;";
Connection conexion = DriverManager.getConnection(url);

        Statement sentencia = conexion.createStatement();
        String sql = "select id,id_user_alumno,id_user_profesor,id_asignatura,puntuacion\n" +
"from notas";
        ResultSet resul = sentencia.executeQuery(sql);

        while (resul.next()) {
      System.out.println(resul.getInt(1) + ", " + resul.getInt(2) + ", " + resul.getInt(3) + ", " + resul.getInt(4)+", "+resul.getDouble(5));
            
        }
        resul.close();
        sentencia.close(); 
        conexion.close(); 
        } catch (SQLException e) {
        e.printStackTrace();
}       catch (ClassNotFoundException ex) {     
            Logger.getLogger(ProyectoTema2.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}