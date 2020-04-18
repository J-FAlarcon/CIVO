/**
 * 
 */
package Aplicacion.Proyecto;


import java.util.*;



/**
 * @author eps
 *
 */
public class Infraestructura extends Proyecto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String distrito;
	private String imagen;
	
	/**
	 * Constructor de la clase Infraestructura
	 * 
	 * @param distrito - Nombre del distrito a donde va orientado el proyecto
	 * @param imagen - Path donde esta guardada la imagen
	 * @param id Id
	 * @param titulo Titulo
	 * @param descripcion Descripcion
	 * @param presupuestoSolicitado Presupuesto Solicitado
	 * @param presupuestoConcedido Presupuesto Concedido
	 * @param votos Votos
	 * @param fecha Fecha
	 * @param estado Estado
	 * 
	 */

	public Infraestructura (int id, String titulo, String descripcion, double presupuestoSolicitado, double presupuestoConcedido, 
			int votos, Date fecha, Estados estado, String distrito, String imagen) {
		super(id, titulo, descripcion, presupuestoSolicitado, presupuestoConcedido, votos, fecha, estado);
		this.distrito = distrito;
		this.imagen = imagen;
	}
	/**
	 * @return the distrito
	 */
	public String getDistrito() {
		return distrito;
	}
	/**
	 * @param distrito the distrito to set
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}
	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}
