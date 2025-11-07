/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Diurno
 */
public class Nota {
				
				private double puntuacion;
				
				private int id;
				private int id_user;
				private int id_asignatura;
				
				private String nombre_examen;
				
				
				public Nota(double puntuacion, int id_user, int id_asignatura, String nombre_examen) {
								this.puntuacion = puntuacion;
								this.id_user = id_user;
								this.id_asignatura = id_asignatura;
								this.nombre_examen = nombre_examen;
				}
				
				
				public double getPuntuacion() {
								return puntuacion;
				}

				public void setPuntuacion(double puntuacion) {
								this.puntuacion = puntuacion;
				}

				public int getId() {
								return id;
				}

				public void setId(int id) {
								this.id = id;
				}

				public int getId_user() {
								return id_user;
				}

				public void setId_user(int id_user) {
								this.id_user = id_user;
				}

				public int getId_asignatura() {
								return id_asignatura;
				}

				public void setId_asignatura(int id_asignatura) {
								this.id_asignatura = id_asignatura;
				}

				public String getNombre_examen() {
								return nombre_examen;
				}

				public void setNombre_examen(String nombre_examen) {
								this.nombre_examen = nombre_examen;
				}
				
				
				
}
