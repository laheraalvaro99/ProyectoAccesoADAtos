/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author Diurno
 */
import javax.swing.*;

public class LoginForm extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Login - Academia");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setBounds(30, 30, 100, 25);
        add(lblUser);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(130, 30, 120, 25);
        add(txtUsuario);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(30, 70, 100, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(130, 70, 120, 25);
        add(txtPassword);

        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(100, 120, 100, 30);
        add(btnLogin);
    }

    // Getters
    public JTextField getTxtUsuario() { return txtUsuario; }
    public JPasswordField getTxtPassword() { return txtPassword; }
    public JButton getBtnLogin() { return btnLogin; }
}