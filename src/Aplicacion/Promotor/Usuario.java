/**
 * 
 */
package Aplicacion.Promotor;


import java.util.*;
import Aplicacion.Proyecto.Proyecto;
import Aplicacion.Proyecto.Social;
import Aplicacion.Proyecto.Infraestructura;
import Aplicacion.Proyecto.tipoSocial;
import Aplicacion.Aplicacion;
import Aplicacion.Proyecto.Estados;


/**
 * @author eps
 *
 */
public class Usuario extends Promotor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String NIF, usuario, contrasena;
	
	public EstadoUsuario estado;
	public boolean iniciado;
	List <Proyecto > proyectosCreados = new ArrayList <Proyecto>();
	List <Colectivo > colectivosCreados = new ArrayList <Colectivo>();
	List <Colectivo > colectivosSuscrito = new ArrayList <Colectivo>();
	List <Proyecto> proyectosVotados = new ArrayList <Proyecto>();
	
	/**
	 * Constructor de la clase Usuario
	 * 
	 * @param NIF - NIF del usuario
	 * @param usuario - Nombre del usuario
	 * @param contrasena - COntrasena del usuario
	 */

	public Usuario (String NIF, String usuario, String contrasena) {
		this.NIF = NIF;
		this.usuario = usuario;
		this.contrasena = contrasena;
		estado = EstadoUsuario.Pendiente;
		iniciado = false;
	}
	/**
	 * @return the nIF
	 */
	public String getNIF() {
		return NIF;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}
	
	/**
	 * @return the estado
	 */
	public EstadoUsuario getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
	}
	
	

	/**
	 * @return the iniciado
	 */
	public boolean isIniciado() {
		return iniciado;
	}
	/**
	 * @param iniciado the iniciado to set
	 */
	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
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
	 * @return the colectivosSuscrito
	 */
	public List<Colectivo> getColectivosSuscrito() {
		return colectivosSuscrito;
	}
	/**
	 * @param colectivosSuscrito the colectivosSuscrito to set
	 */
	public void setColectivosSuscrito(List<Colectivo> colectivosSuscrito) {
		this.colectivosSuscrito = colectivosSuscrito;
	}
	/**
	 * Metodo para crear proyectos sociales
	 * 
	 * @param app App
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
	public boolean crearProyectoSocial(Aplicacion app,String titulo,String descripcion, double presupuestosol, int minVotos, Estados estado,String grupoSocial, tipoSocial tipo) { 
		
		
		if(this.iniciado==false||this.estado==EstadoUsuario.Bloqueado) {
			return false;
		}
		
		int i = proyectosCreados.size();
		Date fecha = new Date();
		Social social = new Social (i+1, titulo, descripcion, presupuestosol, 0.00, 1, fecha, 
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
	
		if(this.iniciado==false||this.estado==EstadoUsuario.Bloqueado) {
			return false;
		}
		
		int i = proyectosCreados.size();
		Date fecha = new Date();
		Infraestructura infraestructura = new Infraestructura (i+1, titulo, descripcion, presupuestosol, 0.00, 1, fecha, 
				estado, distrito, imagen);
		
		proyectosCreados.add(infraestructura);
		List <Proyecto> p = app.getProyectos();
		p.add(infraestructura);
		app.setProyectos(p);
		
		
		
		return true;
	}
	

	/**
	 * Metodo para crear colectivos
	 * 
	 * @param nombre nombre
	 * @param app App
	 * 
	 * 
	 * @return true
	 */
	
	public boolean crearColectivo(String nombre, Aplicacion app) {
		
		
		if(this.iniciado==false||this.estado==EstadoUsuario.Bloqueado) {
			return false;
		}
		
		Colectivo colectivo = new Colectivo(nombre);
		
		colectivosCreados.add(colectivo);
		List <Colectivo> c = app.getColectivos();
		c.add(colectivo);
		app.setColectivos(c);
		
		
		return true;
	}
	

	/**
	 * Metodo para cambiar la contrase�a del usuario
	 * 
	 * Tiene que coincidir la contrase�a antigua con la contrase�a actual del usuario para poder 
	 * verificar la identidad del usuario y poder asi cambiarla por otra nueva
	 * 
	 * @param contrAntigua antigua
	 * @param contrNueva nueva
	 * 
	 * 
	 */
	
	public void cambiarContrasena(String contrAntigua, String contrNueva) {
		
		if(contrAntigua.contentEquals(contrasena)==true) {
			
			contrasena=contrNueva;
		}
		
	}
	
	/**
	 * Metodo para ver el informe de popularidad
	 * 
	 * 
	 * @param proyecto proyecto
	 * @return los votos totales del proyecto
	 * 
	 * 
	 */
	
	public int informePopularidad(Proyecto proyecto) {
		
		if(this.iniciado==false||this.estado==EstadoUsuario.Bloqueado) {
			return -1;
		}
		return proyecto.getVotos();
	}
	
	/**
	 * Metodo para ver el informe de afinidad
	 * 
	 * 
	 * @param c1 colectivo1
	 * @param c2 colectivo2
	 * 
	 * @return afinidad
	 */
	public double informeAfinidad(Colectivo c1, Colectivo c2) {
		
		
		
		if(this.iniciado==false||this.estado==EstadoUsuario.Bloqueado) {
			return -1;
		}
		
		
		double afinidad;
		int comun=0;
		int votosColectivo1=c1.getProyectosVotados().size();
		int votosColectivo2=c2.getProyectosVotados().size();
		int creadosColectivo1=c1.getProyectosCreados().size();
		int creadosColectivo2=c2.getProyectosCreados().size();
		
		int totalVotos= votosColectivo1 + votosColectivo2 + creadosColectivo1 +creadosColectivo2;
		
		List <Proyecto> proyVotados1=c1.getProyectosVotados();
		List <Proyecto> proyVotados2=c2.getProyectosVotados();
		
		List <Proyecto> proyCreados1=c1.getProyectosCreados();
		List <Proyecto> proyCreados2=c2.getProyectosCreados();
		
		
		
		if(c1.getUsuarios().contains(this) == false || c2.getUsuarios().contains(this)==false) {
			return 0;
		}
		
		
		for(int i=0;i<=proyCreados1.size();i++) {
			
			for(int j=0;j<=proyVotados2.size();j++) {
				
				if(proyVotados2.get(j).equals(proyCreados1.get(i))==true) {
					
					comun++;
				}
				
			}
			
		}
		
		for(int i=0;i<=proyCreados2.size();i++) {
			
			for(int j=0;j<=proyVotados1.size();j++) {
				
				if(proyVotados1.get(j).equals(proyCreados2.get(i))==true) {
					
					comun++;
				}
				
			}
			
		}
		
		
		afinidad= comun/totalVotos;
		
		
		return afinidad;
		
	}

	/**
	 * Metodo para bloquear un usuario
	 * 
	 *	Resta los votos del usuario que vamos a bloquear en todos los proyectos que habia apoyado
	 * 
	 * 
	 * 
	 */
	public void bloquearUsuario() {
		
		for(Proyecto pro:proyectosVotados) {
			
			pro.setVotos(pro.getVotos()-1);
			
		}
		
		
		estado=EstadoUsuario.Bloqueado;
		
	}
	
	/**
	 * Metodo para desbloquear un usuario
	 * 
	 *	Vuelve a sumar los votos que se habian restado cuando el usuario fue bloqueado
	 * 
	 * 
	 */
	
	public void desbloquearUsuario() {
		
		
		for(Proyecto pro:proyectosVotados) {
			
			pro.setVotos(pro.getVotos()+1);
			
		}


		estado=EstadoUsuario.Aceptado;
	}
	
	
}
