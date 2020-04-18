/**
 * 
 */
package Aplicacion;

import java.util.*;
import java.io.*;

import Aplicacion.Promotor.Usuario;
import Aplicacion.Proyecto.Estados;
import Aplicacion.Proyecto.Proyecto;
import Aplicacion.Promotor.Colectivo;
import Aplicacion.Promotor.EstadoUsuario;
import Aplicacion.Notificacion.Notificacion;

/**
 * @author eps
 *
 */
public class Aplicacion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private  List <Usuario> usuarios = new ArrayList <Usuario>();
	private  List<Proyecto> proyectos = new ArrayList <Proyecto>();
	private  List <Colectivo> colectivos = new ArrayList <Colectivo>();
	private  File file = new File("basedatos.txt");
	private  File dis = new File("Distritos.txt");
	private  Scanner input;

	
	
	/**
	 * @return the usuarios
	 */
	public  List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public  void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * @return the proyectos
	 */
	public  List<Proyecto> getProyectos() {
		return proyectos;
	}

	/**
	 * @param proyectos the proyectos to set
	 */
	public  void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	/**
	 * @return the colectivos
	 */
	public  List<Colectivo> getColectivos() {
		return colectivos;
	}

	/**
	 * @param colectivos the colectivos to set
	 */
	public  void setColectivos(List<Colectivo> colectivos) {
		this.colectivos = colectivos;
	}


	/**
	 * Metodo para iniciar sesion
	 * 
	 * @param clave Clave
	 * @param contrasena Contrasena
	 * 
	 * 
	 * @return true si se inicia sesion correctamente
	 */
	
	@SuppressWarnings("unused")
	public  boolean iniciarSesion(String clave, String contrasena) {
	
		try {
			for(int i = 0; i < usuarios.size(); i++) {
	    		if(clave.equals(usuarios.get(i).getUsuario()) || clave.equals(usuarios.get(i).getNIF())) {
	    			if(contrasena.equals(usuarios.get(i).getContrasena())) {
	    				if(usuarios.get(i).getEstado() == EstadoUsuario.Aceptado && usuarios.get(i).isIniciado() == false) {
	    					usuarios.get(i).setIniciado(true);
	    					System.out.println("Iniciado correctamente");
	    					return true;
	    				} else {
	    					System.out.println("Usuario bloqueado o ya iniciado.");
	    					return false;
	    				}
	    			} else {
	    				System.out.println("Usuario o contrasena incorrectos.");
	    				return false;
	    			}
	    		} else {
	    			System.out.println("Usuario o contrasena incorrectos.");
					return false;
	    		}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	

	/**
	 * Metodo para cerrar sesion
	 * 
	 * @param usuario Usuario
	 * 
	 * 
	 * @return true si se cierra correctamente
	 */
	
	public boolean cerrarSesion(String usuario) {
		
	    try {
	    	for(int i = 0; i < usuarios.size(); i++) {
	    		if(usuario.equals(usuarios.get(i).getUsuario())) {
	    			if(usuarios.get(i).isIniciado() == true) {
	    				usuarios.get(i).setIniciado(false);
	    				return true;
	    			}
	    		}
	    	}
	    }
			
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Metodo para enviar notificaciones
	 * 
	 * @param mensaje Mensaje
	 * @param destinatario Destinatario
	 * 
	 * 
	 */
	
	public void notificar(String mensaje, String destinatario) {
		Date date = new Date(System.currentTimeMillis());
		Notificacion n = new Notificacion(mensaje, date);
		n.enviarNotificacion(destinatario);
	}
	
	/**
	 * Metodo para borrar Proyectos
	 * 
	 * @param proyecto Proyecto
	 * 
	 * @return true si se borra correctamente
	 */
	
	public boolean borrarProyecto(Proyecto proyecto) {
		if(proyectos.contains(proyecto)) {
			proyectos.remove(proyecto);
			System.out.println("Proyecto borrado correctamente.");
			return true;
		} else {
			System.out.println("No existe el proyecto a borrar.");
			return false;
		}
	}
	
	/**
	 * Metodo para comprobar si la contrasena cumple los requisitos solicitados por nuestra aplicacion
	 * 
	 * @param contrasena
	 * 
	 * @return true si cumple los requisitos
	 */
	
	private  boolean checkContrasena(String contrasena) {
		char[] chars = contrasena.toCharArray();
		int flag = 0;
		
		for(char c : chars) {
			if(Character.isDigit(c)) {
				flag = 1;
			}
		}
		
		if(contrasena.length() < 8 || flag == 0) {
			return false; 
		}
		return true;
	}
	
	/**
	 * Metodo para registrarse en la aplicacion
	 * 
	 * @param NIF NIF
	 * @param usuario Usuario
	 * @param contrasena Contrasena
	 * 
	 * @return true si cumple los requisitos
	 */
	
	public  boolean registrar(String NIF, String usuario, String contrasena) {
		
		
		if(!(NIF.length() == 9)) {
			System.out.println("Entrada no adecuada.");
			return false;
		}
		
		for(int i = 0; i < usuarios.size(); i++) {
    		if(usuario.equals(usuarios.get(i).getUsuario())) {
    			System.out.println("Usuario ya existente.");
    			return false;
    		}
    		if(NIF.contentEquals(usuarios.get(i).getUsuario())) {
    			System.out.println("NIF ya existente.");
    			return false;
    		}
    	}
    		
		if(!checkContrasena(contrasena)) {
			System.out.println("La contrasena debe tener al menos 8 caracteres y un numero.");
			return false;
		}
		
		Usuario user = new Usuario(NIF, usuario, contrasena);
		usuarios.add(user);
		return true;
	}
	
	/**
	 * Metodo para grabar los datos de la aplicacion en el fichero basedatos.txt
	 * 
	 * @throws IOException IOException
	 * 
	 * @return true
	 */
	
	public  boolean grabarDatos() throws IOException {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			
			for(int u = 0; u < usuarios.size(); u++) {
				oos.writeObject(usuarios.get(u));
			}
			
			for(int p = 0; p < proyectos.size(); p++) {
				oos.writeObject(proyectos.get(p));
			}
			
			for(int c = 0; c < colectivos.size(); c++) {
				oos.writeObject(colectivos.get(c));
			}
			
			oos.close();
			
		}	
		catch (IOException e) {
			System.out.println("Ha ocurrido un error escribiendo.");
		}
		return true;
	}
	
	/**
	 * Metodo para cargar los datos de la aplicacion desde el fichero basedatos.txt
	 * 
	 * @throws IOException IOException
	 * @throws ClassNotFoundException ClassNotFoundException
	 * @throws EOFException EOFException
	 * 
	 * @return true
	 */
	
	public  boolean cargarDatos() throws IOException, ClassNotFoundException, EOFException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			
			Object aux = ois.readObject();;
			
			while(aux != null) {
				if(aux instanceof Usuario) {
					usuarios.add((Usuario) aux);
				}
				
				if(aux instanceof Proyecto) {
					proyectos.add((Proyecto) aux);
				}
				
				if(aux instanceof Colectivo) {
					colectivos.add((Colectivo) aux);
				}
				aux = ois.readObject();
			}
			ois.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		} 
		return true;
	}
	
	
	
	/**
	 * Metodo para cargar los distritos desde el fichero Distritos.txt
	 * 
	 * 
	 * @return true
	 */
	
	public  List<String> leerDistritos() {
	
		List<String> Distritos= new ArrayList<String>();
		try {
				input = new Scanner(dis);
				
				
				while(input.nextLine() != null) {
					
					String distrito=input.next();
					Distritos.add(distrito);
				}
		}
	
		catch(Exception e) {
			e.printStackTrace();
		}
		return Distritos;
		}
	
	/**
	 * Metodo para rechazar un usuario
	 * @param usuario Usuario
	 * 
	 * @return true
	 */
	
	public boolean rechazarUsuario(Usuario usuario) {
		usuario.setEstado(EstadoUsuario.Bloqueado);
		return true;
	}
	
	/**
	 * Metodo para aceptar un usuario
	 * @param usuario Usuario
	 * 
	 * @return true
	 */
	
	public boolean aceptarUsuario(Usuario usuario) {
		usuario.setEstado(EstadoUsuario.Aceptado);
		return true;
	}
	
	/**
	 * Metodo para rechazar un proyecto
	 * @param proyecto Proyecto
	 * 
	 * @return true
	 */
	
	public boolean rechazarProyecto(Proyecto proyecto) {
		proyecto.setEstado(Estados.Denegado);
		return true;
	}
	
	/**
	 * Metodo para aceptar un proyecto
	 * @param proyecto Proyecto
	 * 
	 * @return true
	 */
	
	public boolean aceptarProyecto(Proyecto proyecto) {
		proyecto.setEstado(Estados.Aceptado);
		return true;
	}
}