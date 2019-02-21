package com.branchbit.ws;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.branchbit.bd.BD;
import com.branchbit.bd.Conexion;
import com.branchbit.bd.DAO;
import com.branchbit.bd.DatosBD;
import com.branchbit.vo.Persona;

@Path("/")
public class REST {

	BD bd = new BD();
	DAO dao;
	public REST() {
		dao = new DAO();
	}

	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() {
		List<Persona> lista= dao.listarPersonas();
		return Response.status(200).entity(lista).build();
	}
	
	@GET
	@Path("listar/{dato}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPersona(@PathParam("dato") String dato) {
		List<Persona> personas=dao.buscarPersonas(dato);
		return Response.status(200).entity(personas).build();
	}
	
	@GET
	@Path("listarPersona/{idPersona}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPersona(@PathParam("idPersona")int idPersona) {
		List<Persona> persona=dao.buscarPersona(idPersona);
		return Response.status(200).entity(persona).build();
	}

	@POST
	@Path("/agregar")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Persona> agregar(Persona persona) {
		dao.agregarPersona(persona);
		return dao.listarPersonas();
		// return persona.getNombre()+" agregado correctamente";
	}

	@PUT
	@Path("/actualizar/{idPersona}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Persona> actualizar(@PathParam("idPersona") int idPersona, Persona persona) {
		dao.actualizarPersona(idPersona, persona);
		return dao.listarPersonas();
		// return persona.getNombre()+" actualizado correctamente";
	}

	@DELETE
	@Path("/eliminar/{idPersona}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Persona> eliminar(@PathParam("idPersona") int idPersona) {
		dao.eliminarPersona(idPersona);
		return dao.listarPersonas();
	}


}
