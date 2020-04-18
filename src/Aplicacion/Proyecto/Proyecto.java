/**
 * 
 */
package Aplicacion.Proyecto;

import java.io.IOException;
import java.util.*;
import Aplicacion.Promotor.*;
import Aplicacion.Proyecto.Estados;
import es.uam.eps.sadp.grants.CCGG;
import es.uam.eps.sadp.grants.GrantRequest;
import es.uam.eps.sadp.grants.InvalidIDException;
import es.uam.eps.sadp.grants.InvalidRequestException;


/**
 * @author eps
 *
 */


public abstract class Proyecto implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id, votos;
	static int minVotos;
	private double presupuestoSolicitado, presupuestoConcedido;
	private String titulo, descripcion;
	private Date fecha, ultimaFecha;
	private Estados estado;
	private String codigo;
	
	List <Usuario > usuariosVotantes = new ArrayList <Usuario>();
	List <Colectivo > colectivosVotantes = new ArrayList <Colectivo>();
	
/**
 * Constructor de la clase Proyecto
 * 
 * @param id - Identificador del proyecto
 * @param votos - Numero de votos que lleva el proyecto
 * @param presupuestoSolicitado - Cantidad que el usuario estima que costara el proyecto
 * @param presupuestoConcedido - Cantidad que finalmente es destinada al proyecto
 * @param titulo - Titulo del proyecto
 * @param descripcion - Breve explicacion sobre lo que consistira el proyecto
 * @param fecha - Fecha en la que es creado el proyecto
 * @param estado - Estado del proyecto
 */
public Proyecto(int id, String titulo, String descripcion, double presupuestoSolicitado, double presupuestoConcedido,
		int votos, Date fecha, Estados estado){
	this.id = id;
	this.titulo = titulo;
	this.descripcion = descripcion;
	this.presupuestoSolicitado = presupuestoSolicitado;
	this.presupuestoConcedido = presupuestoConcedido;
	this.votos = votos;
	this.fecha = fecha;
	this.ultimaFecha = fecha;
	this.estado = estado;
	this.codigo = null;
}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the votos
	 */
	public int getVotos() {
		return votos;
	}
	/**
	 * @param votos the votos to set
	 */
	public void setVotos(int votos) {
		this.votos = votos;
	}
	/**
	 * @return the minVotos
	 */
	public int getMinVotos() {
		return minVotos;
	}
	/**
	 * @param minVotos the minVotos to set
	 */
	public void setMinVotos(int minVotos) {
		this.minVotos = minVotos;
	}
	/**
	 * @return the presupuesto solicitado
	 */
	public double getPresupuestoSol() {
		return presupuestoSolicitado;
	}
	/**
	 * @param presupuesto the presupuesto solicitado to set
	 */
	public void setPresupuestoSol(double presupuesto) {
		this.presupuestoSolicitado = presupuesto;
	}
	/**
	 * @return the presupuesto concedido
	 */
	public double getPresupuestoCon() {
		return presupuestoConcedido;
	}
	/**
	 * @param presupuesto the presupuesto concedido to set
	 */
	public void setPresupuestoCon(double presupuesto) {
		this.presupuestoConcedido = presupuesto;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the ultimaFecha
	 */
	public Date getUltimaFecha() {
		return ultimaFecha;
	}
	/**
	 * @param ultimaFecha the ultimaFecha to set
	 */
	public void setUltimaFecha(Date ultimaFecha) {
		this.ultimaFecha = ultimaFecha;
	}
	/**
	 * @return the estado
	 */
	public Estados getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estados estado) {
		this.estado = estado;
	}
	
	/**
	 * Metodo para votar un proyecto como usuario
	 * 
	 * 
	 * @param usuario Usuario
	 * 
	 * @return boolean
	 * 
	 */
	public boolean votarProyectoU(Usuario usuario){
		
		if(usuario.getEstado() != EstadoUsuario.Aceptado) {
			System.out.println("No se puede votar, el usuario esta bloqueado o pendiente de registro.");
			return false;
		}
		
		else {
			setVotos(getVotos()+1);	
			usuariosVotantes.add(usuario);
			actualizarUltFecha();
			List <Proyecto> p = usuario.getProyectosVotados();
			p.add(this);
			usuario.setProyectosVotados(p);
			return true;
		}
	}
	
	/**
	 * Metodo para votar un proyecto como colectivo
	 * 
	 * 
	 * @param colectivo Colectivo
	 * 
	 * @return boolean
	 * 
	 */

	public boolean votarProyectoC(Colectivo colectivo){
		
		List <Usuario> usuarios = new ArrayList <Usuario>();
		int v = getVotos();
		usuarios = colectivo.getUsuarios();
		for(int cont = 0 ; cont < usuarios.size(); cont++) {
			this.votarProyectoU(usuarios.get(cont));
		}
		
		if(v != getVotos()) {
			colectivosVotantes.add(colectivo);
			actualizarUltFecha();
			List <Proyecto> p = colectivo.getProyectosVotados();
			p.add(this);
			colectivo.setProyectosVotados(p);
			return true;
		}
		
		else
			return false;
	}
	
	/**
	 * Metodo para actualizar la ultima fecha en la que se bota
	 *  
	 * 
	 * 
	 */
	
	public void actualizarUltFecha() {
		Date date = new Date();
		setUltimaFecha(date);
	}
	
	/**
	 * Metodo para pedir financiacion
	 * 
	 * 
	 * @throws InvalidRequestException  InvalidRequestException
	 * @throws IOException IOException
	 * 
	 * @return codigo
	 * 
	 */
	
	public String pedirFinanciacion() throws IOException, InvalidRequestException {
		
		CCGG pasarela = CCGG.getGateway();
		GrantRequest Solicitud =(GrantRequest)this;
		
		String codigo = pasarela.submitRequest(Solicitud);
		
		return codigo;
	}
	
	/**
	 * Metodo para consultar el estado de la financiacion
	 * 
	 * 
	 * @throws IOException IOException
	 * 
	 * @return codigo
	 * @throws InvalidIDException InvalidIDException 
	 * 
	 */
	public double consultarFinanProyecto() throws IOException, InvalidIDException {
		try {
			CCGG pasarela = CCGG.getGateway();
			return pasarela.getAmountGranted(this.codigo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Metodo para establecer el minimo de votos
	 *
	 *@param votos Votos
	 * 
	 */
	public void establecerMinVotos(int votos) {
		
		setMinVotos(votos);
		
	}
}
