import java.util.ArrayList;
import java.util.Set;

public class Main {

	
	public static void main(String[] args) {
	String path;
	String path500k = "C:/TUDAI-ws/TPE3-Fazio-Nieto/datasets/dataset_500000.csv";
	String path1m = "C:/TUDAI-ws/TPE3-Fazio-Nieto/datasets/dataset_1000000.csv";
	String path3m = "C:/TUDAI-ws/TPE3-Fazio-Nieto/datasets/dataset_3000000.csv";
	String path10k = "C:/TUDAI-ws/TPE3-Fazio-Nieto/datasets/dataset_busqueda_10000.csv";
	path = path10k ;
		GrafoNoDirigido grafito = new GrafoNoDirigido();
		grafito.addDatos(CSVReader.reader(path));
		System.out.println(grafito.gustoPopular().getDato());
		System.out.println(grafito.usuarioMasLejano("67734766").getDato());
		Set<Vertice> e = grafito.usuariosGustosComunes("67734766");
		for (Vertice vertice : e) {
			System.out.println(vertice.getDato());
		}
		GrafoNoDirigido g = new GrafoNoDirigido();
		ArrayList<String[]> list = new ArrayList<>();
		String[] lista1 = {"5487866","futbol"};
		String[] lista2 = {"21444758","futbol","tenis"};
		String[] lista3 = {"67545565","futbol","tenis","equitacion","golf"};
		String[] lista4 = {"65324548","handball","equitacion"};
		String[] lista5 = {"54788865","futbol","tenis","handball","golf"};
		list.add(lista1);
		list.add(lista2);
		list.add(lista3);
		list.add(lista4);
		list.add(lista5);
		g.addDatos(list);
	
		
		
		
		
		
	}
	}


