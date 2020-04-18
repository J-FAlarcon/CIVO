
package Aplicacion.Promotor;

import java.util.*;


import Aplicacion.Proyecto.Estados;
import Aplicacion.Proyecto.Infraestructura;
import Aplicacion.Proyecto.Proyecto;
import Aplicacion.Proyecto.Social;
import Aplicacion.Proyecto.tipoSocial;

/**
 * @author eps
 *
 */


public abstract class Promotor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List <Proyecto > proyectosCreados = new ArrayList <Proyecto>();
	List <Colectivo > colectivosCreados = new ArrayList <Colectivo>();
	
	

	
	/**
	 * @return the proyectosCreados
	 */
	public List<Proyecto> getProyectosCreados() {
		return proyectosCreados;
	}
	
	/**
	 * @param proyectosCreados the proyectosCreados to set
	 */
	public void setProyectosCreados(List<Proyecto> proyectosCreados) {
		this.proyectosCreados = proyectosCreados;
	}
	
	/**
	 * @return the colectivosCreados
	 */
	public List<Colectivo> getColectivosCreados() {
		return colectivosCreados;
	}
	
	/**
	 * @param colectivosCreados the colectivosCreados to set
	 */
	public void setColectivosCreados(List<Colectivo> colectivosCreados) {
		this.colectivosCreados = colectivosCreados;
	}
	
	/**
	 * Metodo para crear proyectos sociales
	 * 
	 * @param titulo titulo
	 * @param descripcion descripcion
	 * @param presupuestosol presupuestosol
	 * @param minVotos minVotos
	 * @param estado estado
	 * @param grupoSocial grupoSocial
	 * @param tipo tipo
	 * 
	 * @return true
	 */
	
	public boolean crearProyectoSocial(String titulo,String descripcion, double presupuestosol, int minVotos, Estados estado,String grupoSocial, tipoSocial tipo) { 
		int i = proyectosCreados.size();
		Date fecha = new Date();
		Social social = new Social (i+1, titulo, descripcion, presupuestosol, 0.00, 0, fecha, 
				estado, grupoSocial, tipo);
		
		proyectosCreados.add(social);
		
		return true;
		
	}
	
	/**
	 * Metodo para crear proyectos de infraestructura
	 * 
	 * @param titulo titulo
	 * @param descripcion descripcion
	 * @param presupuestosol presupuestosol
	 * @param minVotos minVotos
	 * @param estado estado
	 * @param distrito distrito
	 * @param imagen imagen
	 * 
	 * @return true
	 */
	
	public boolean crearProyectoInfraestructura(String titulo,String descripcion, double presupuestosol, int minVotos, Estados estado,String distrito, String imagen) {
		int i = proyectosCreados.size();
		Date fecha = new Date();
		Infraestructura infraestructura = new Infraestructura (i+1, titulo, descripcion, presupuestosol, 0.00, 0, fecha, 
				estado, distrito, imagen);
		
		proyectosCreados.add(infraestructura);
		
		return true;
	}
	
	/**
	 * Metodo para crear colectivos
	 * 
	 * @param nombre nombre
	 * 
	 * 
	 * @return true
	 */
	
	public boolean crearColectivo(String nombre) {
		Colectivo colectivo = new Colectivo(nombre);
		
		colectivosCreados.add(colectivo);
		
		return true;
	}
}
	