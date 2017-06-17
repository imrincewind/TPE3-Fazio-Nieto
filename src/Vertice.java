import java.util.HashSet;
import java.util.Set;

public class Vertice {
	private String dato;
	private GrafoNoDirigido.TipoNodo tipo;
	private Set<Vertice> conexiones;
	
	public Vertice(GrafoNoDirigido.TipoNodo tipo, String dato){
		this.tipo = tipo;
		this.dato = dato;
		this.conexiones = new HashSet<>();
	}
	public void addConexion(Vertice n){
	this.conexiones.add(n);
	}
	public Set<Vertice> getConexiones(){
		return this.conexiones;
	}
	
 	public String getDato() {
		return dato;
	}

	@Override
	public boolean equals(Object obj) {  
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice otro = (Vertice) obj;
		if (conexiones == null) {
			if (otro.conexiones != null)
				return false;
		} else if (!conexiones.equals(otro.conexiones))
			return false;
		if (dato == null) {
			if (otro.dato != null)
				return false;
		} else if (!dato.equals(otro.dato))
			return false;
		if (tipo != otro.tipo)
			return false;
		return true;
	}
	
}
