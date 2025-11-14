/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.vista.InterfazesProfesor.InterfazesUpdate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InterfazUpdate extends JFrame {

    public JTextField txtIdNota;
    public JComboBox<Integer> cmbAsignaturas;
    public JTextField txtPuntuacion;
    public JButton btnActualizar;

    public InterfazUpdate() {
        setTitle("Actualizar Nota");
        setSize(400, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblIdNota = new JLabel("ID Nota:");
        lblIdNota.setBounds(20, 20, 100, 25);
        add(lblIdNota);
        txtIdNota = new JTextField();
        txtIdNota.setBounds(140, 20, 200, 25);
        add(txtIdNota);

        JLabel lblAsignatura = new JLabel("Asignatura:");
        lblAsignatura.setBounds(20, 60, 100, 25);
        add(lblAsignatura);
        cmbAsignaturas = new JComboBox<>();
        cmbAsignaturas.setBounds(140, 60, 200, 25);
        add(cmbAsignaturas);

        JLabel lblPuntuacion = new JLabel("Nueva Nota:");
        lblPuntuacion.setBounds(20, 100, 100, 25);
        add(lblPuntuacion);
        txtPuntuacion = new JTextField();
        txtPuntuacion.setBounds(140, 100, 200, 25);
        add(txtPuntuacion);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(140, 150, 120, 30);
        add(btnActualizar);
    }
}