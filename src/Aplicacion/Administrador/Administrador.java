/**
 * 
 */
package Aplicacion.Administrador;

/**
 * @author eps
 *
 */
public class Administrador {
	
	private String administrador, contrasena;
	
	/**
	 * Constructor de la clase Administrador
	 * 
	 * @param administrador - Nombre del administrador
	 * @param contrasena - Contrasena del administrador
	 */

	public Administrador (String administrador, String contrasena) {
		this.administrador = administrador;
		this.contrasena = contrasena;
	}

	/**
	 * @return the administrador
	 */
	public String getAdministrador() {
		return administrador;
	}

	/**
	 * @param administrador the administrador to set
	 */
	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
