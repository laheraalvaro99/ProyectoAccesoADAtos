/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Diurno
 */
public class User {
				private int id;
				private String nombre;
				private String apellido;
				private String contraseña;
				private boolean profesor;

				public User () {};
				
				public User(String nombre, String apellido, String contraseña, boolean profesor) {
								this.nombre = nombre;
								this.apellido = apellido;
								this.contraseña = contraseña;
								this.profesor = profesor;
				}

				public int getId() {
								return id;
				}

				public void setId(int id) {
								this.id = id;
				}

				public String getNombre() {
								return nombre;
				}

				public void setNombre(String nombre) {
								this.nombre = nombre;
				}

				public String getApellido() {
								return apellido;
				}

				public void setApellido(String apellido) {
								this.apellido = apellido;
				}

				public boolean isIsProf() {
								return profesor;
				}

				public void setIsProf(boolean isProf) {
								this.profesor = isProf;
				}
				
				
				
}
