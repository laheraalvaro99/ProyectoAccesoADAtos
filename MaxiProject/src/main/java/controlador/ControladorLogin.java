/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.LoginForm;

import javax.swing.*;

public class ControladorLogin {
    private LoginForm vista;
    private UsuarioDAO usuarioDAO;

    public ControladorLogin(LoginForm vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();
        initController();
    }

    private void initController() {
        vista.getBtnLogin().addActionListener(e -> login());
    }

    private void login() {
        String email = vista.getTxtEmail().getText();
        String pass = String.valueOf(vista.getTxtPassword().getPassword());

        Usuario user = usuarioDAO.validarLogin(email, pass);

        if (user != null) {
            JOptionPane.showMessageDialog(vista, 
                    "Bienvenido " + user.getNombre() + " (" + (user.getRol() == 'p' ? "Profesor" : "Alumno") + ")");
        } else {
            JOptionPane.showMessageDialog(vista, "Email o contraseña incorrectos.");
        }
    }
}
