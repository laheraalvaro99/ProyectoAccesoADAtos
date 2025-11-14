package grupo4.proyectoaccesodatos.controlador.ControladorActualizarNotas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Diurno
 */

import grupo4.proyectoaccesodatos.modelo.Conexion;
import grupo4.proyectoaccesodatos.modelo.ConsultasProfesor.ConsultasUpdate.ConsultasActualizarNotas;
import grupo4.proyectoaccesodatos.vista.InterfazesProfesor.InterfazesUpdate.InterfazUpdate;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControladorActualizarNotas {

    private InterfazUpdate vista;
    private ConsultasActualizarNotas modelo;
    private int idProfesor;

    public ControladorActualizarNotas(InterfazUpdate vista, int idProfesor) {
        this.vista = vista;
        this.modelo = new ConsultasActualizarNotas();
        this.idProfesor = idProfesor;

        cargarAsignaturas();
        initController();
    }

    private void initController() {
        vista.btnActualizar.addActionListener(e -> actualizarNota());
    }

    private void actualizarNota() {
        try {
            int idNota = Integer.parseInt(vista.txtIdNota.getText());
            int idAsignatura = (int) vista.cmbAsignaturas.getSelectedItem();
            double nuevaPuntuacion = Double.parseDouble(vista.txtPuntuacion.getText());

            boolean ok = modelo.actualizarNota(idNota, idAsignatura, nuevaPuntuacion);

            if (ok) {
                JOptionPane.showMessageDialog(vista, "Nota actualizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar la nota.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Valores inválidos: " + ex.getMessage());
        }
    }

    private void cargarAsignaturas() {
        try {
            Connection con = Conexion.getConexion();
            String sql = "SELECT id FROM asignaturas WHERE id_user_profesor = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idProfesor);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                vista.cmbAsignaturas.addItem(rs.getInt("id"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar asignaturas: " + e.getMessage());
        }
    }
}