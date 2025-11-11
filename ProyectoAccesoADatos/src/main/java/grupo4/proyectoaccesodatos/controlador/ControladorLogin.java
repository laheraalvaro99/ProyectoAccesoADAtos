/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.controlador;

import grupo4.proyectoaccesodatos.modelo.Usuario;
import grupo4.proyectoaccesodatos.vista.InterfazesAlumno.InterfazAlumno;
import grupo4.proyectoaccesodatos.vista.InterfazesProfesor.InterfazProfesor;
import grupo4.proyectoaccesodatos.vista.LoginForm;

import javax.swing.*;

public class ControladorLogin {

    private final LoginForm vista;
    private final Usuario user;

    public ControladorLogin(LoginForm vista) {
        this.vista = vista;
        user = new Usuario();
        initController();
    }

    private void initController() {
        vista.getBtnLogin().addActionListener(e -> login());
    }

    private void login() {
        String email = vista.getTxtEmail().getText();
        String pass = String.valueOf(vista.getTxtPassword().getPassword());

        user.validarLogin(email, pass);

        if (user.getId() != 0) {
            JOptionPane.showMessageDialog(vista,
                    "Bienvenido " + user.getNombre() + " (" + (user.getRol() == 'p' ? "Profesor" : "Alumno") + ")");
            vista.setVisible(false);
            if (user.getRol() == 'p') {
                // Si el rol es 'p', abrir la interfaz del profesor
                InterfazProfesor interfazPrincipal = new InterfazProfesor(user.getEmail());
                interfazPrincipal.setVisible(true);
            } else if (user.getRol() == 'a') {
                // Si el rol es 'a', abrir la interfaz del alumno
                InterfazAlumno interfazAlumno = new InterfazAlumno(user.getEmail());
                interfazAlumno.setVisible(true);
            }

        } else {
            // Si la validación falla, mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
