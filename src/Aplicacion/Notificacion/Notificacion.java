/**
 * 
 */
package Aplicacion.Notificacion;

import java.util.*;

/**
 * @author eps
 *
 */
public class Notificacion {
	
	private String texto;
	private Date fecha;
	
	/**
	 * Constructor de la clase Notificacion
	 * 
	 * @param texto - Cuerpo de la notificacion
	 * @param fecha - fecha en el momento de notificar
	 */

	public Notificacion (String texto, Date fecha) {
		this.texto = texto;
		this.fecha = fecha;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
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
	
	public void enviarNotificacion(String usuario) {
		
		System.out.println(this.texto);
	
	}
}
