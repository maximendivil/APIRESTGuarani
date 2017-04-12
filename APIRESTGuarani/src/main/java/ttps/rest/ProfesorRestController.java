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
@RequestMapping(value = "/Profesor")
public class ProfesorRestController {
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Profesor>> listarProfesores() {
	    List<Profesor> profesores = obtenerProfesores();
	    if(profesores.isEmpty()){
	    	 return new ResponseEntity<List<Profesor>>(HttpStatus.NO_CONTENT); 
    	}
	    return new ResponseEntity<List<Profesor>>(profesores,HttpStatus.OK);
	}
	
	private List<Profesor> obtenerProfesores(){
		Profesor p1 = new Profesor(4,"Laura","Fava","laurafava@gmail.com","laurafava","lfava123");
		List<Integer> anios = new ArrayList<Integer>();
		anios.add(2);
		anios.add(3);
		anios.add(4);
		p1.setAnios(anios);
		
		Profesor p2 = new Profesor(5,"Gustavo","Rossi","gustavorossi@gmail.com","grossi","grossi123");
		List<Integer> anios2 = new ArrayList<Integer>();
		anios2.add(2);
		anios2.add(3);
		p2.setAnios(anios2);
		
		Profesor p3 = new Profesor(6,"Juan Pablo","Perez","jpperez@gmail.com","jpperez","jpperez123");
		List<Integer> anios3 = new ArrayList<Integer>();
		anios3.add(2);
		anios3.add(3);
		anios3.add(4);
		p3.setAnios(anios3);
		
		List<Profesor> profesores = new ArrayList<Profesor>();
		profesores.add(p1);
		profesores.add(p2);
		profesores.add(p3);
		
		return profesores;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)    
	public ResponseEntity<Profesor> listarProfesor(@PathVariable("id") long id) {
		Profesor profesor = obtenerProfesorPorId(id);
		if (profesor == null) { 
			return new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
	}
	
	private Profesor obtenerProfesorPorId(long id){
		List<Profesor> profesores = obtenerProfesores();
		Iterator<Profesor> it = profesores.iterator();
		Profesor aux;
		while (it.hasNext()){
			aux = it.next();
			if (aux.getId() == id){
				return aux;
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/chequearlogin", method = RequestMethod.POST)
	public ResponseEntity<Void> chequearLogin(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder){
		if (esValido(usuario)){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	private boolean esValido(Usuario usuario){
		List<Profesor> profesores = obtenerProfesores();
		Iterator<Profesor> it = profesores.iterator();
		Profesor aux;
		while (it.hasNext()){
			aux = it.next();
			if ((aux.getUsuario().equals(usuario.getUsuario()))&&(aux.getContraseña().equals(usuario.getContraseña()))){
				return true;
			}
		}
		return false;
	}	
}