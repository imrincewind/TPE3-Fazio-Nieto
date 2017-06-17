import java.util.Set;

public interface IGrafo {
	public Set<Vertice> usuariosGustosComunes(String idUsuario);
	public Vertice gustoPopular();
	public boolean existeGusto(String s);
	public boolean existeUsuario(String s);
	public void addUsuario(String s);
	public void addGusto (String s);
}
