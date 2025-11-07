/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.HistoricoNotas;
import Modelo.Nota;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Diurno
 */
public class ControladorProfesor {
				
				private String url = "jdbc:mysql://localhost/academia";
				private String user = "root";
				private String password = "";
				
				private Connection con;
				private PreparedStatement consulta;

				public Connection getCon() {
								return con;
				}
				
				public void  ponerNota (double puntuacion, int id_profesor, int id_asignatura, int id_alumno) {
								String sqlPonerNota = "INSERT INTO notas (id_user_profesor, id_asignatura, id_user_alumno, puntuacion) "
																+ "VALUES (?,?,?,?)";
								
								try {
												con = DriverManager.getConnection(url, user, password);
												consulta = con.prepareStatement(sqlPonerNota);
												
												consulta.setInt(1, id_profesor);
												consulta.setInt(2, id_asignatura);
												consulta.setInt(3, id_alumno);
												consulta.setDouble(4, puntuacion);
												
												consulta.executeUpdate();
												
												consulta.close();
												con.close();

								} catch (SQLException ex) {
												Logger.getLogger(ControladorProfesor.class.getName()).log(Level.SEVERE, null, ex);
								}
				};
				
				public void modificarNota (int idProfesor, Nota nota, double nuevaPuntuacion, String tipoCambio, String motivo) {
								
								String sqlModificarNota = "UPDATE notas"
																+ "SET puntuacion = ?"
																+ "WHERE id = ?";
								
								String sqlAniadirCambioAlRegistro = "INSERT INTO historico_notas "
																+ "(id_nota, id_asignatura, id_user_alumno, id_user_profesor, "
																+ "puntuacion_anterior, puntuacion_posterior, tipo_cambio, motivo) "
																+ "VALUES (?,?,?,?,?,?,?)";
								
								double puntuacion_antigua = nota.getPuntuacion();
								
								try {
												con = DriverManager.getConnection(url, user, password);
												consulta = con.prepareStatement(sqlModificarNota);
												
												consulta.setInt(1, nota.getId());
												nota.setPuntuacion(nuevaPuntuacion);
												consulta.setDouble(2, nota.getPuntuacion());
												
												consulta.executeUpdate();
												
												HistoricoNotas histoCambio = new HistoricoNotas
																(nota.getId(), 
																nota.getId_asignatura(), 
																nota.getId_user(), 
																idProfesor, 
																puntuacion_antigua, 
																nota.getPuntuacion(), 
																tipoCambio, 
																motivo);
												
												consulta = con.prepareStatement(sqlAniadirCambioAlRegistro);
												
												consulta.setInt(1, histoCambio.getId_nota());
												consulta.setInt(2, histoCambio.getId_asignatura());
												consulta.setInt(3, histoCambio.getId_alumno());
												consulta.setInt(4, histoCambio.getId_profesor());
												consulta.setDouble(5, histoCambio.getPuntuacion_anterior());
												consulta.setDouble(5, histoCambio.getPuntuacion_nueva());
												consulta.setString(6, histoCambio.getTipo_Cambio());
												consulta.setString(7, histoCambio.getMotivo());
												
												consulta.executeUpdate();

								} catch (SQLException ex) {
												Logger.getLogger(ControladorProfesor.class.getName()).log(Level.SEVERE, null, ex);
								}
								
				}
				
				public static void main(String[] args) {
								Controlador.ControladorProfesor controls = new ControladorProfesor();
								controls.ponerNota(9, 1, 2, 1);
				}
}
