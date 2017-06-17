import java.util.Set;

public interface IGrafo {

	
	public void addUsuario(String s);
	public void addGusto (String s);
	
	public boolean existeGusto(String s);
	public boolean existeUsuario(String s);
	
	public Set<Vertice> usuariosGustosComunes(String id);
	public Vertice gustoPopular();
}
