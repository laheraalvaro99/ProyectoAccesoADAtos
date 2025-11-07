/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
/**
 *
 * @author Diurno
 */
public class Conexion {
    private static Connection conexion;

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;"
                        + "databaseName=academia;"
                        + "integratedSecurity=true;"
                        + "encrypt=false;"
                        + "trustServerCertificate=true;";
                conexion = DriverManager.getConnection(url);
                System.out.println("Conexión establecida correctamente.");
            } catch (Exception e) {
                System.out.println("Error al conectar: " + e.getMessage());
            }
        }
        return conexion;
    }
}