/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.modelo.ConsultasProfesor.ConsultasUpdate;

import grupo4.proyectoaccesodatos.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultasActualizarNotas {

    public boolean actualizarNota(int idNota, int idNuevaAsignatura, double nuevaPuntuacion) {
        boolean actualizado = false;

        try {
            Connection con = Conexion.getConexion();
            String sql = "UPDATE notas SET id_asignatura = ?, puntuacion = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idNuevaAsignatura);
            ps.setDouble(2, nuevaPuntuacion);
            ps.setInt(3, idNota);

            int filas = ps.executeUpdate();
            actualizado = filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar nota: " + e.getMessage());
        }

        return actualizado;
    }
}
