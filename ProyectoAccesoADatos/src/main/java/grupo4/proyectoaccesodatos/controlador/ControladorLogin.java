/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.controlador;

import grupo4.proyectoaccesodatos.modelo.Usuario;
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

        if (user != null) {
            JOptionPane.showMessageDialog(vista,
                    "Bienvenido " + user.getNombre() + " (" + (user.getRol() == 'p' ? "Profesor" : "Alumno") + ")");
        } else {
            JOptionPane.showMessageDialog(vista, "Email o contraseña incorrectos.");
        }
    }
}
