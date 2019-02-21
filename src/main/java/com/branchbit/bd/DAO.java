package com.branchbit.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.branchbit.vo.Persona;

public class DAO {
	Statement stm;
	Conexion conex;
	Connection conn;

	public DAO() {
		conex = new Conexion();
		conn = conex.getConex();
	}

	public List<Persona> listarPersonas() {
		Persona persona;
		List<Persona> listaPersonas = new ArrayList<Persona>();
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("Select * from Persona");
			while (rs.next()) {
				persona = new Persona(rs.getInt("idPersona"), rs.getString("nombre"), rs.getString("puesto"));
				listaPersonas.add(persona);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return listaPersonas;
	}

	public static void main(String[] args) {
		DAO dao = new DAO();
		List<Persona> persona = dao.buscarPersonas("Adr");
		for (Persona per : persona) {
			System.out.println(per.getNombre());
			System.out.println(per.getPuesto());
		}
		
		List<Persona> per=dao.buscarPersona(4);
		System.out.println(per);
		
		Persona adrian=new Persona("Adrian","Java Jr");
		int r=dao.actualizarPersona(1, adrian);
		System.out.println(r);
		
		/*
		 * Persona javier = new Persona("Javier", "Estudiante"); int i =
		 * dao.agregarPersona(javier); if(i!=0) { System.out.println("Insertado"); }else
		 * { System.out.println("asdnfk"); }
		 */
	}

	public int agregarPersona(Persona persona) {
		int r = 0;
		String cadSql;
		try {
			stm = conn.createStatement();
			cadSql = "insert into Persona values(idPersona.nextval, '" + persona.getNombre() + "','"
					+ persona.getPuesto() + "')";
			r = stm.executeUpdate(cadSql);
		} catch (Exception e) {
			e.printStackTrace();
			return r;
		}
		return r;
	}

	public List<Persona> buscarPersonas(String dato) {
		String cadSql;
		List<Persona> personas = new ArrayList<Persona>();
		Persona persona = null;
		try {
			stm = conn.createStatement();
			String nombre = "";
			cadSql = "select * from Persona where (nombre like '%"+dato+"%')";
			 cadSql+=" OR (puesto like '%"+dato+"%')";
			 //cadSql+=" OR (idPersona = "+Integer.parseInt(dato)+")";
			 System.out.println(cadSql);
			ResultSet rs = stm.executeQuery(cadSql);
			while (rs.next()) {
				persona = new Persona();
				persona.setIdPersona(rs.getInt(1));
				persona.setNombre(rs.getString(2));
				persona.setPuesto(rs.getString(3));
				personas.add(persona);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return personas;
	}
	
	public List<Persona> buscarPersona(int idPersona) {
		String cadSql;
		List<Persona>personas=new ArrayList<Persona>();
		Persona persona = null;
		try {
			stm = conn.createStatement();
			String nombre = "";
			cadSql = "select * from Persona where idPersona = "+idPersona;
			ResultSet rs = stm.executeQuery(cadSql);
			while (rs.next()) {
				persona = new Persona();
				persona.setIdPersona(rs.getInt(1));
				persona.setNombre(rs.getString(2));
				persona.setPuesto(rs.getString(3));
				personas.add(persona);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return personas;
	}
	
	public int actualizarPersona(int idPersona, Persona persona) {
		int r=0;
		String cadSql;
		try {
			stm=conn.createStatement();
			cadSql="Update Persona set nombre='"+persona.getNombre()+"', puesto='"+persona.getPuesto()+"' where idPersona="+idPersona;
			r=stm.executeUpdate(cadSql);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return r;
	}

	public int eliminarPersona(int idPersona) {
		int r = 0;
		String cadSql;
		try {
			stm = conn.createStatement();
			cadSql = "Delete from Persona where idPersona=" + idPersona;
			r = stm.executeUpdate(cadSql);
		} catch (Exception e) {
			e.printStackTrace();
			return r;
		}
		return r;
	}
}
