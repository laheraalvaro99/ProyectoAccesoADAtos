/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Diurno
 */
public class Asignatura {
				private String nombre;
				
				private int id;
				private int id_nota;
				private int id_alumno;
				
				private String curso;

				public Asignatura(String nombre, int id_nota, int id_alumno, String curso) {
								this.id_nota = id_nota;
								this.id_alumno = id_alumno;
								this.curso = curso;
				}

				public String getNombre() {
								return nombre;
				}

				public void setNombre(String nombre) {
								this.nombre = nombre;
				}

				public int getId() {
								return id;
				}

				public void setId(int id) {
								this.id = id;
				}

				public int getId_nota() {
								return id_nota;
				}

				public void setId_nota(int id_nota) {
								this.id_nota = id_nota;
				}

				public int getId_alumno() {
								return id_alumno;
				}

				public void setId_alumno(int id_alumno) {
								this.id_alumno = id_alumno;
				}

				public String getCurso() {
								return curso;
				}

				public void setCurso(String curso) {
								this.curso = curso;
				}
				
				
				
}
