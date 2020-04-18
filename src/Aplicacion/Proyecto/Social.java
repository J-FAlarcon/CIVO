/**
 * 
 */
package Aplicacion.Proyecto;

import java.util.*;


/**
 * @author eps
 *
 */
public class Social extends Proyecto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String grupoSocial;
	private tipoSocial tipo;
	
	/**
	 * Constructor de la clase Social
	 * 
	 * @param grupoSocial - Nombre del grupo social al que va destinado
	 * @param id Id
	 * @param titulo Titulo
	 * @param descripcion Descripcion
	 * @param presupuestoSolicitado presupuestoSolicitado
	 * @param presupuestoConcedido presupuestoConcedido
	 * @param votos Votos
	 * @param fecha fecha
	 * @param estado estado
	 * @param grupoSocial grupoSocial
	 * @param tipo tipo
	 * 
	 */

	public Social (int id, String titulo, String descripcion, double presupuestoSolicitado, double presupuestoConcedido, 
			int votos, Date fecha, Estados estado, String grupoSocial, tipoSocial tipo) {
		super(id, titulo, descripcion, presupuestoSolicitado, presupuestoConcedido, votos, fecha, estado);
		this.grupoSocial = grupoSocial;
		this.tipo = tipo;
	}
	/**
	 * @return the grupoSocial
	 */
	public String getGrupoSocial() {
		return grupoSocial;
	}
	/**
	 * @param grupoSocial the grupoSocial to set
	 */
	public void setGrupoSocial(String grupoSocial) {
		this.grupoSocial = grupoSocial;
	}
	/**
	 * @return the tipo
	 */
	public tipoSocial getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(tipoSocial tipo) {
		this.tipo = tipo;
	}
}
