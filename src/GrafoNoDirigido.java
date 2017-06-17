import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrafoNoDirigido implements IGrafo {
	private Map<String, Vertice> gustos = new HashMap<>();
	private Map<String, Vertice> usuarios = new HashMap<>();

	enum TipoNodo {
		USUARIO, GUSTO;
	}

	@Override
	public void addUsuario(String s) {
		Vertice vertice = new Vertice(TipoNodo.USUARIO, s);
		usuarios.put(s, vertice);
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
	public void addGusto(String s) {
		Vertice n = new Vertice(TipoNodo.GUSTO, s);
		this.gustos.put(s, n);
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
	public Set<Vertice> usuariosGustosComunes(String id) {
		Set<Vertice> similars = new HashSet<>();
		if (this.existeUsuario(id)) {
			Vertice u = usuarios.get(id);
			for (Vertice n : this.usuarios.values()) {// Devuelve todos los vertices
													// usuarios
				if (cantidadGustosEnComun(u, n) >= 2) {
					similars.add(n);
				}
			}
			similars.remove(u);// Se remueve el vertice recibido de la lista de
								// similares
		}
		return similars;
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

	public Vertice usuarioMasLejano(String st) { // Dado un usuario, busco el usuario
										// con el gusto más lejano al mismo.
		Vertice usuarioInicial = this.usuarios.get(st);
		Set<Vertice> S = new HashSet<Vertice>(); // S conjunto solución
		S.add(usuarioInicial); // Inicializo el conjunto S = {1}
		Map<Vertice, Integer> D = new HashMap<>(); // Matriz de Distancias, sería
												// equivalente al D[i]

		initDistancias(usuarioInicial, D); // Calcula la primera fila de la matriz de
										// Distancias
		iterateDistancias(usuarioInicial, S, D);

		Vertice usuarioMasLejano = getUsuarioMasLejano(D); // Se queda con el usuario con la
											// distancia mayor

		return usuarioMasLejano;
	}

	private Vertice getUsuarioMasLejano(Map<Vertice, Integer> D) {
		int longestDistancia = 0;
		Vertice usuarioMasLejano = null;

		for (Map.Entry<Vertice, Integer> m : D.entrySet()) {
			System.out.println(m.getValue() + " dato: " + m.getKey().getDato());
			if (m.getValue() > longestDistancia) {
				longestDistancia = m.getValue();

				usuarioMasLejano = m.getKey();
			}

		}
		return usuarioMasLejano;
	}

	private void iterateDistancias(Vertice usuarioInicial, Set<Vertice> S, Map<Vertice, Integer> D) {

		for (int i = 1; i < (usuarios.values().size() - 1); i++) {
			Set<Vertice> vlS = new HashSet<>(usuarios.values()); // V less S o
																// (V-S)
			vlS.removeAll(S); // V-S
			Vertice w = verticeMenorDistancia(vlS, D); // Elijo el minimo w de la tabla de
										// Distancias
			S.add(w); // agrego w al conjunto Solución
			vlS.remove(w);
			// Ahora que elejimos un w, hay que actualizar la tabla de
			// distancias, parados en este w.
			for (Vertice v : vlS) { // para cada vértice V en V-S,
				D.put(v, Math.max(D.get(v), D.get(w) + getDistancia(w, v)));// en
																			// lugar
																			// de
																			// buscar
																			// la
																			// distancia
																			// minima
																			// buscamos
																			// el
																			// camino
																			// más
																			// largo
			}

		}
	}
	
	private void initDistancias(Vertice usuarioInicial, Map<Vertice, Integer> D) {
		for (Vertice i : usuarios.values()) {
			System.out.println("algo: " + i.getDato());
			if (i.equals(usuarioInicial)) { // Salteo el usuario dado (equivale al
										// i=2)
				continue;
			}
			D.put(i, getDistancia(usuarioInicial, i));
		}
	}

	/** Elige un vértice w en V-S tal que D[w] sea un mínimo; */

	private Vertice verticeMenorDistancia(Set<Vertice> vlS, Map<Vertice, Integer> d) {
		int distanciaMinima = Integer.MAX_VALUE;
		Vertice verticeMinimo = null;

		for (Vertice w : vlS) {
			if (d.get(w) < distanciaMinima) { // con d.get(w) pido la distancia q
											// tengo guardada en el arreglo de
											// distancias
				distanciaMinima = d.get(w);
				verticeMinimo = w;
			}
		}
		return verticeMinimo;
	}

	/**
	 * Debería devolver 1 si los vertices están conectados o Integer.MAX_VALUE si
	 * no están conectados.
	 */

	private Integer getDistancia(Vertice usuarioInicial, Vertice i) {

		for (Vertice gusto : usuarioInicial.getConexiones()) { // Las relaciones que tiene
														// el usuario son con
														// los gustos, no con
														// otros usuarios.
			for (Vertice usuario : gusto.getConexiones()) { // Las relaciones que tienen
													// los gustos son con los
													// usuarios, no con los
													// gustos.
				// Agarramos todos los gustos de ese usuario y para cada gusto
				// pedimos los usuarios que gustan de ese gusto
				// y si alguno es el vertice i devolvemos 1 (están conectados)
				// si no están conectados, devuelvo Integer.MAX_VALUE
				if (usuario.equals(usuarioInicial)) {
					return 1;
				}
			}
		}
		return Integer.MAX_VALUE;
	}

}
