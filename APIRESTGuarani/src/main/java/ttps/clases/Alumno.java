package ttps.clases;

public class Alumno extends Usuario {
	private long id;
	private String nombres;
	private String apellidos;
	private String email;
	
	public Alumno(long id, String nombres, String apellidos, String email, String usuario, String contraseña){
		super(usuario,contraseña);
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.email = email;
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
}
