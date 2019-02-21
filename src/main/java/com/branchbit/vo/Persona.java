package com.branchbit.vo;

import java.io.Serializable;

public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPersona;
	private String nombre;
	private String puesto;
	
	public Persona() {
		// TODO Auto-generated constructor stub
	}

	public Persona(int idPersona, String nombre, String puesto) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.puesto = puesto;
	}

	public Persona(String nombre, String puesto) {
		this.nombre = nombre;
		this.puesto = puesto;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", puesto=" + puesto + "]";
	}

}
