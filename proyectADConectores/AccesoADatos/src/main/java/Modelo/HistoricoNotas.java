/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Diurno
 */
public class HistoricoNotas {
				
				private int id_historico;
				
				private int id_nota;
				private int id_asignatura;
				private int id_alumno;
				private int id_profesor;
				
				private double puntuacion_anterior;
				private double puntuacion_nueva;
				
				private String tipo_Cambio;
				
				private String motivo;

				public HistoricoNotas(int id_nota, int id_asignatura, int id_alumno, int id_profesor, double puntuacion_anterior, double puntuacion_nueva, String tipo_Cambio, String motivo) {
								this.id_nota = id_nota;
								this.id_asignatura = id_asignatura;
								this.id_alumno = id_alumno;
								this.id_profesor = id_profesor;
								this.puntuacion_anterior = puntuacion_anterior;
								this.puntuacion_nueva = puntuacion_nueva;
								this.tipo_Cambio = tipo_Cambio;
								this.motivo = motivo;
				}

				public HistoricoNotas(int id_nota, int id_asignatura, int id_alumno, int id_profesor, String tipo_Cambio) {
								this.id_nota = id_nota;
								this.id_asignatura = id_asignatura;
								this.id_alumno = id_alumno;
								this.id_profesor = id_profesor;
								this.tipo_Cambio = tipo_Cambio;
				}

				public int getId_historico() {
								return id_historico;
				}

				public void setId_historico(int id_historico) {
								this.id_historico = id_historico;
				}

				public int getId_nota() {
								return id_nota;
				}

				public void setId_nota(int id_nota) {
								this.id_nota = id_nota;
				}

				public int getId_asignatura() {
								return id_asignatura;
				}

				public void setId_asignatura(int id_asignatura) {
								this.id_asignatura = id_asignatura;
				}

				public int getId_alumno() {
								return id_alumno;
				}

				public void setId_alumno(int id_alumno) {
								this.id_alumno = id_alumno;
				}

				public int getId_profesor() {
								return id_profesor;
				}

				public void setId_profesor(int id_profesor) {
								this.id_profesor = id_profesor;
				}

				public double getPuntuacion_anterior() {
								return puntuacion_anterior;
				}

				public void setPuntuacion_anterior(double puntuacion_anterior) {
								this.puntuacion_anterior = puntuacion_anterior;
				}

				public double getPuntuacion_nueva() {
								return puntuacion_nueva;
				}

				public void setPuntuacion_nueva(double puntuacion_nueva) {
								this.puntuacion_nueva = puntuacion_nueva;
				}

				public String getTipo_Cambio() {
								return tipo_Cambio;
				}

				public void setTipo_Cambio(String tipo_Cambio) {
								this.tipo_Cambio = tipo_Cambio;
				}

				public String getMotivo() {
								return motivo;
				}

				public void setMotivo(String motivo) {
								this.motivo = motivo;
				}

				
				
				
}
