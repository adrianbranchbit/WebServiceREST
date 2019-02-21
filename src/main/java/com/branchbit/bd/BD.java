package com.branchbit.bd;

import java.util.ArrayList;
import java.util.List;

import com.branchbit.vo.Persona;

public class BD {
	private static List<Persona> personas;
	
	public BD() {
		personas=new ArrayList<Persona>();
		personas.add(new Persona(1,"Adrian","Java"));
		personas.add(new Persona(2,"Cesar","Oracle"));
		personas.add(new Persona(3,"Edwin","Gerente"));
	}
	
	public List<Persona> getPersonas() {
		return personas;
	}
	
	public Persona getPersona(int idPersona) {
		return personas.get(idPersona);
	}
	
	public void newPersona(Persona persona) {
		personas.add(persona);
	}
	
	public void updatePersona(int idPersona, Persona persona) {
		personas.set(idPersona, persona);
	}
	
	public void deletePersona(int idPersona) {
		personas.remove(idPersona);
	}
}
