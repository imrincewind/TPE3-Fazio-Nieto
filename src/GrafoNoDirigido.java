import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class GrafoNoDirigido implements IGrafo {
	private Map<String, Vertice> gustos = new HashMap<>();
	private Map<String, Vertice> usuarios = new HashMap<>();

	enum TipoNodo {
		USUARIO, GUSTO;
	}
	@Override
	public Vertice gustoPopular() {
		Vertice masPopular = null;
		for (Vertice gusto : this.gustos.values()) {
			if (masPopular == null) {
				masPopular = gusto;
			} else if (masPopular.getConexiones().size() < gusto.getConexiones().size()) {
				masPopular = gusto;
			}
		}
		return masPopular;
	}
	@Override
	public boolean existeGusto(String s) {
		return gustos.containsKey(s);
	}
	@Override
	public boolean existeUsuario(String s) {
		return usuarios.containsKey(s);
	}
	@Override
	public void addUsuario(String s) {
		Vertice vertice = new Vertice(TipoNodo.USUARIO, s);
		usuarios.put(s, vertice);
	}
	@Override
	public void addGusto(String s) {
		Vertice n = new Vertice(TipoNodo.GUSTO, s);
		this.gustos.put(s, n);
	}
	public void setConexiones(String s, ArrayList<String> gustosUsuario) {
		Vertice usuario = this.usuarios.get(s);
		ArrayList<Vertice> conexiones = new ArrayList<>();
		for (String g : gustosUsuario) {
			if (!this.gustos.containsKey(g)) {
				addGusto(g);
			}
			conexiones.add(this.gustos.get(g));
			this.gustos.get(g).addConexion(usuario);
		}
		for (Vertice n : conexiones) {
			usuario.addConexion(n);
		}
	}

	@Override
	public Set<Vertice> usuariosGustosComunes(String id) {
		Set<Vertice> listaUsuariosGustosComunes = new HashSet<>();
		if (this.existeUsuario(id)) {
			Vertice u = usuarios.get(id);
			for (Vertice n : this.usuarios.values()) {
				if (cantidadGustosEnComun(u, n) >= 2) {
					listaUsuariosGustosComunes.add(n);
				}
			}
			listaUsuariosGustosComunes.remove(u);
		}
		return listaUsuariosGustosComunes;
	}

	private int cantidadGustosEnComun(Vertice u1, Vertice u2) {
		int contador = 0;
		for (Vertice gusto : u1.getConexiones()) {
			for (Vertice gusto2 : u2.getConexiones()) {
				if (gusto.equals(gusto2)) {
					contador++;
					break;
				}
			}
		}
		return contador;
	}

	

	public Vertice usuarioMasLejano(String s) { 
		Vertice usuarioInicial = this.usuarios.get(s);
		Set<Vertice> S = new HashSet<Vertice>(); 
		S.add(usuarioInicial); 
		Map<Vertice, Integer> D = new HashMap<>(); 
		for (Vertice i : usuarios.values()) {
			if (i.equals(usuarioInicial)) { 
				continue;
			}
			D.put(i, getDistancia(usuarioInicial, i));
		}
										
		for (int i = 1; i < (usuarios.values().size() - 1); i++) {
			Set<Vertice> vlS = new HashSet<>(usuarios.values()); 
			vlS.removeAll(S); 
			Vertice w = verticeMenorDistancia(vlS, D); 
										
			S.add(w); 
			vlS.remove(w);
	
			for (Vertice v : vlS) { 
				D.put(v, Math.max(D.get(v), D.get(w) + getDistancia(w, v)));
			}

		}

		Vertice usuarioMasLejano = getUsuarioMasLejano(D); 

		return usuarioMasLejano;
	}

	private Vertice getUsuarioMasLejano(Map<Vertice, Integer> D) {
		int longestDistancia = 0;
		Vertice usuarioMasLejano = null;

		for (Map.Entry<Vertice, Integer> m : D.entrySet()) {
			if (m.getValue() > longestDistancia) {
				longestDistancia = m.getValue();

				usuarioMasLejano = m.getKey();
			}

		}
		return usuarioMasLejano;
		
	}

	private Vertice verticeMenorDistancia(Set<Vertice> vlS, Map<Vertice, Integer> d) {
		int distanciaMinima = Integer.MAX_VALUE;
		Vertice verticeMinimo = null;

		for (Vertice w : vlS) {
			if (d.get(w) < distanciaMinima) { 
				distanciaMinima = d.get(w);
				verticeMinimo = w;
			}
		}
		return verticeMinimo;
	}

	private Integer getDistancia(Vertice usuarioInicial, Vertice i) {

		for (Vertice gusto : usuarioInicial.getConexiones()) { 
			for (Vertice usuario : gusto.getConexiones()) { 
				if (usuario.equals(usuarioInicial)) {
					return 1;
				}
			}
		}
		return Integer.MAX_VALUE;
	}
	public void addDatos(ArrayList<String[]> lista) {
		
		for (String[] strings : lista) {
			this.addUsuario(strings[0]);
			ArrayList<String> li = new ArrayList<>();
			for(int i = 1; i< strings.length;i++){
				li.add(strings[i]);
			}
			this.setConexiones(strings[0], li);
		
		}
		
	}

}
