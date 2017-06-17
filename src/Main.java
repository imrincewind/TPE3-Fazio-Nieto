import java.util.ArrayList;
import java.util.Set;

public class Main {

	
	public static void main(String[] args) {
	
		GrafoNoDirigido g = new GrafoNoDirigido();
		g.addUsuario("5487866");
		g.addUsuario("21444758");
		g.addUsuario("67545565");
		g.addUsuario("65324548");
		g.addUsuario("54788865");
		ArrayList<String> g1 = new ArrayList<>();
		g1.add("futbol");
		ArrayList<String> g2 = new ArrayList<>();
		g2.add("futbol");
		g2.add("tenis");
		ArrayList<String> g3 = new ArrayList<>();
		g3.add("futbol");
		g3.add("tenis");
		g3.add("equitacion");
		g3.add("golf");
		ArrayList<String> g4 = new ArrayList<>();
		g4.add("handball");
		g4.add("equitacion");
		ArrayList<String> g5 = new ArrayList<>();
		g5.add("handball");
		g5.add("futbol");
		g5.add("tenis");
		g5.add("golf");
		
		
		g.setConexiones("5487866", g1);
		g.setConexiones("21444758", g2);
		g.setConexiones("67545565", g3);
		g.setConexiones("65324548", g4);
		g.setConexiones("54788865", g5);
		
		Set<Vertice> e= g.usuariosGustosComunes("54788865");
		for (Vertice vertice : e) {
			System.out.println(vertice.getDato());
		}
		
		
		System.out.println(g.gustoPopular().getDato());
		System.out.println(g.usuarioMasLejano("5487866").getDato());
	}

}
