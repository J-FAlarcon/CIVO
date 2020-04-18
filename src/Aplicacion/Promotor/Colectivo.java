/**
 * 
 */
package Aplicacion.Promotor;

import java.util.*;

import Aplicacion.Aplicacion;
import Aplicacion.Proyecto.Estados;
import Aplicacion.Proyecto.Infraestructura;
import Aplicacion.Proyecto.Proyecto;
import Aplicacion.Proyecto.Social;
import Aplicacion.Proyecto.tipoSocial;

/**
 * @author eps
 *
 */
public class Colectivo extends Promotor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String nombre;
	

	List <Proyecto > proyectosCreados = new ArrayList <Proyecto>();
	List <Usuario> usuarios = new ArrayList <Usuario>();
	List <Proyecto> proyectosVotados = new ArrayList <Proyecto>();
	List <Colectivo> subcolectivos = new ArrayList <Colectivo>();
	
	/**
	 * Constructor de la clase Colectivo
	 * @param nombre - Nombre del colectivo
	 */
	public Colectivo (String nombre) {
		this.nombre = nombre;
		
	}
	
	/**
	 * @return the usuarios
	 */
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

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
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * @return the proyectosvotados
	 */
	public List<Proyecto> getProyectosVotados() {
		return proyectosVotados;
	}

	/**
	 * @param proyectosvotados the proyectosvotados to set
	 */
	public void setProyectosVotados(List<Proyecto> proyectosvotados) {
		this.proyectosVotados = proyectosvotados;
	}

	/**
	 * @return the subcolectivos
	 */
	public List<Colectivo> getSubcolectivos() {
		return subcolectivos;
	}

	/**
	 * @param subcolectivos the subcolectivos to set
	 */
	public void setSubcolectivos(List<Colectivo> subcolectivos) {
		this.subcolectivos = subcolectivos;
	}
	
	/**
	 * Metodo para crear proyectos sociales
	 * 
	 * @param app App
	 * @param titulo Titulo
	 * @param descripcion descripcion 
	 * @param presupuestosol presupuestosol
	 * @param minVotos minVotos
	 * @param estado estado
	 * @param grupoSocial grupoSocial
	 * @param tipo tipo
	 * 
	 * @return true
	 */
	
	public boolean crearProyectoSocial(Aplicacion app,String titulo,String descripcion, double presupuestosol, int minVotos, Estados estado,String grupoSocial, tipoSocial tipo) { 
		int i = proyectosCreados.size();
		Date fecha = new Date();
		Social social = new Social (i+1, titulo, descripcion, presupuestosol, 0.00, getUsuarios().size(), fecha, 
				estado, grupoSocial, tipo);
		
		proyectosCreados.add(social);
		List <Proyecto> p = app.getProyectos();
		p.add(social);
		app.setProyectos(p);
		
		return true;
		
	}
	
	/**
	 * Metodo para crear proyectos de infraestructura
	 * 
	 * @param app App
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
	
	public boolean crearProyectoInfraestructura(Aplicacion app,String titulo,String descripcion, double presupuestosol, int minVotos, Estados estado,String distrito, String imagen) {
		int i = proyectosCreados.size();
		Date fecha = new Date();
		Infraestructura infraestructura = new Infraestructura (i+1, titulo, descripcion, presupuestosol, 0.00, getUsuarios().size(), fecha, 
				estado, distrito, imagen);
		
		proyectosCreados.add(infraestructura);
		List <Proyecto> p = app.getProyectos();
		p.add(infraestructura);
		app.setProyectos(p);
		
		return true;
	}
	
	
	/**
	 * Metodo para darse de baja en un colectivo
	 * 
	 * @param usuario
	 * 
	 * Elimina al usuario de la lista de suscriptores y elimina al colectivo de la lista de colectivos suscritos del usuario
	 * 
	 */
	
	public void darBaja(Usuario usuario) {
	
		List <Colectivo > colectivosSuscrito = new ArrayList <Colectivo>();
	
	
		usuarios.remove(usuario);
		
		colectivosSuscrito=usuario.getColectivosSuscrito();
		colectivosSuscrito.remove(this);
		usuario.setColectivosSuscrito(colectivosSuscrito);
	}

	/**
	 * Metodo para suscribirse en un colectivo
	 * 
	 * @param usuario
	 * 
	 * Anade al usuario en la lista de suscriptores y anade el colectivo en la lista de colectivos suscritos del usuario
	 * 
	 */
	
	public void suscribirse(Usuario usuario) {
	
		List <Colectivo > colectivosSuscrito = new ArrayList <Colectivo>();
		usuarios.add(usuario);
		colectivosSuscrito=usuario.getColectivosSuscrito();
		colectivosSuscrito.add(this);
		usuario.setColectivosSuscrito(colectivosSuscrito);
			
	}
	
	/**
	 * Metodo para crear colectivos
	 * 
	 * @param nombre nombre
	 * @param app App
	 * 
	 * @return true
	 */

	public boolean crearColectivo(String nombre,Aplicacion app) {
	
			Colectivo colectivo = new Colectivo(nombre);
			
			subcolectivos.add(colectivo);
			List <Colectivo> c  = app.getColectivos();
			c.add(colectivo);
			app.setColectivos(c);
			
			return true;
		
	}

}
