package ttps.clases;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Usuario {
	private long id;
	private String nombres;
	private String apellidos;
	private String email;
	private List<Integer> anios;
	
	public Profesor(long id, String nombres, String apellidos, String email, String usuario, String contraseņa){
		super(usuario,contraseņa);
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.email = email;
		this.anios = new ArrayList<Integer>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getAnios() {
		return anios;
	}

	public void setAnios(List<Integer> anios) {
		this.anios = anios;
	}
}
