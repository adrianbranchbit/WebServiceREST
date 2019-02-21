package com.branchbit.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private String user;
	private String pass;
	private String url;
	private Connection conex;

	public Conexion() {
		this.user=DatosBD.USUARIO;
		this.pass = DatosBD.PASSWORD;
		this.url = DatosBD.DRIVER;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conex = DriverManager.getConnection(url, user, pass);
			System.out.println("Conexion establecida");
		} catch (Exception e) {
			System.out.println("--------------------------------");
			System.out.println(e.toString());
			System.out.println("Conexion no realizada");
		}
	}
	
	public Connection getConex() {
		return conex;
	}
	
	public void cerrarConexion() {
		try {
			conex.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
