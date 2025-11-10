/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diurno
 */
public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private char rol; // 'a' = alumno, 'p' = profesor

    public Usuario() {}

    public Usuario(int id, String nombre, String apellido, String email, String password, char rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public char getRol() { return rol; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRol(char rol) { this.rol = rol; }
    
    public void validarLogin(String email, String password) {
        
        try {
            Connection con = Conexion.getConexion();
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                setId(rs.getInt("id"));
                setNombre(rs.getString("nombre"));
                setApellido(rs.getString("apellido"));
                setEmail(rs.getString("email"));
                setPassword(rs.getString("password"));
                setRol(rs.getString("rol").charAt(0));
            }
            if (nombre == null) {
                id = 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
        }
    }
    
    public String consulAsignaturas() throws ClassNotFoundException {
        String sql="";
        if (rol == 'a') {
            sql = "SELECT DISTINCT a.id, a.nombre\n"
                + "FROM asignaturas a JOIN notas n\n"
                + "ON a.id = n.id_asignatura\n"
                + "WHERE n.id_user_alumno = ?;";
        } else if (rol == 'p') {
            sql = "SELECT a.id, a.nombre\n"
                + "FROM asignaturas a\n"
                + "WHERE a.id_user_profesor = ?;";
        }
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet resul = sentencia.executeQuery();
            String result = "";
            while (resul.next()) {
                result += resul.getString(1)+ ", " + resul.getString(2) + "\n";
            }
            resul.close(); // Cerrar ResultSet
            sentencia.close(); // Cerrar Statement
            con.close();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}