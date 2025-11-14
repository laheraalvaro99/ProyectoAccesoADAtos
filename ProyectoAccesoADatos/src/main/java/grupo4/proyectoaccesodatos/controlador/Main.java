/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoaccesodatos.controlador;

import grupo4.proyectoaccesodatos.vista.LoginForm;

public class Main {

    public static void main(String[] args) {
        LoginForm vista = new LoginForm();
        new ControladorLogin(vista);
        vista.setVisible(true);
    }
}
