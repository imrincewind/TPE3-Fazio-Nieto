
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * Ejemplo de cómo leer un archivo CSV
 * 
 * @author Mariano A. Fernandez <fernandez.mariano.a@gmail.com>
 */
public class CSVReader {

    public static ArrayList<String[]> reader(String path)  {
    	ArrayList<String[]> users = new ArrayList<String[]>();
    	String csvFile = path;
        String line = "";
        String csvSplitBy = ";";
        int numLine = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
            	if(numLine > 0){
                    users.add(line.split(csvSplitBy));
            	}
            	numLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}