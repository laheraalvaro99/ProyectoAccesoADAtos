/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.vista;

import javax.swing.*;

public class LoginForm extends JFrame {

    private final JTextField txtEmail;
    private final JPasswordField txtPassword;
    private final JButton btnLogin;

    public LoginForm() {
        setTitle("Login - Academia");
        setSize(320, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 30, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(130, 30, 150, 25);
        add(txtEmail);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(30, 70, 100, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(130, 70, 150, 25);
        add(txtPassword);

        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(100, 120, 100, 30);
        add(btnLogin);
    }

    public JTextField getTxtEmail() { return txtEmail; }
    public JPasswordField getTxtPassword() { return txtPassword; }
    public JButton getBtnLogin() { return btnLogin; }
}