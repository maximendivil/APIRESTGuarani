package ttps.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ttps.clases.*;

@RestController
@RequestMapping(value = "/Alumno")
public class AlumnoRestController {
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Alumno>> listarAlumnos() {
	    List<Alumno> alumnos = obtenerAlumnos();
	    if(alumnos.isEmpty()){
	    	 return new ResponseEntity<List<Alumno>>(HttpStatus.NO_CONTENT); 
    	}
	    return new ResponseEntity<List<Alumno>>(alumnos,HttpStatus.OK);
	}
	
	private List<Alumno> obtenerAlumnos(){
		Alumno a1 = new Alumno(1,"Maximiliano Ezequiel","Mendivil","maximendivil22@gmail.com","maximendivil","mmendivil123");
		Alumno a2 = new Alumno(2,"Augusto Ezequiel","Ringuelet","eringuelet@gmail.com","ezeringue","eringuelet123");
		Alumno a3 = new Alumno(3,"Luciano","La Frazia","lucholafrazia@gmail.com","lucholafrazia","llafrazia123");
		
		List<Alumno> alumnos = new ArrayList<Alumno>();
		alumnos.add(a1);
		alumnos.add(a3);
		alumnos.add(a2);
		
		return alumnos;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)    
	public ResponseEntity<Alumno> listarAlumno(@PathVariable("id") long id) {
		Alumno alumno = obtenerAlumnoPorId(id);
		if (alumno == null) { 
			return new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}
	
	private Alumno obtenerAlumnoPorId(long id){
		List<Alumno> alumnos = obtenerAlumnos();
		Iterator<Alumno> it = alumnos.iterator();
		Alumno aux;
		while (it.hasNext()){
			aux = it.next();
			if (aux.getId() == id){
				return aux;
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/chequearlogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> chequearLogin(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder){
		System.out.println("Usuario:" + usuario);
		if (esValido(usuario)){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	private boolean esValido(Usuario usuario){
		List<Alumno> alumnos = obtenerAlumnos();
		Iterator<Alumno> it = alumnos.iterator();
		Alumno aux;
		while (it.hasNext()){
			aux = it.next();
			if ((aux.getUsuario().equals(usuario.getUsuario()))&&(aux.getContraseña().equals(usuario.getContraseña()))){
				return true;
			}
		}
		return false;
	}	
}

