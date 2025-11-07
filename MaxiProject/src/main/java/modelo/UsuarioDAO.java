/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Diurno
 */
import java.sql.*;

public class UsuarioDAO {

    public Usuario validarLogin(String nombre, String password) {
        Usuario user = null;
        try {
            Connection con = Conexion.getConexion();
            String sql = "SELECT * FROM users WHERE nombre = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setPassword(rs.getString("password"));
                user.setRol(rs.getString("rol").charAt(0));
            }
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
        }
        return user;
    }
}
