//package Files;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase FileController. Se usa para la manipulaci�n de archivos de texto.
 * @author Adrián López
 * @version 21/05/2024
 *
 */
public class FileController {
	
	public static final String PATH = System.getProperty("user.dir") + "\\guategrafo.txt";

	/**
	 * Metodo para obtener todas las filas del archivo datos.txt
	 * @return Array con cada una de las filas de texto
	 * @throws IOException
	 */
	public static String[] readFile() throws IOException {
		
		File doc = new File(PATH);

		  BufferedReader obj = new BufferedReader(new FileReader(doc));
		  ArrayList<String> linesList = new ArrayList<String>();

		  //leer y almacenar las filas del archivo de texto
		  String line;
		  while ((line = obj.readLine()) != null) {
		    linesList.add(line);
		  }
		  
		  obj.close();
		  
		  return linesList.toArray(new String[linesList.size()]); //convertir lista a array
	}
	
	/**
	 * Funcion para crear el archivo de almacenamiento y sobreescribirlo
	 * @param text. 
	 * @throws IOException
	 */
	public static void writeFile(String text) throws IOException {
		File file = new File(PATH);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file, true);

        fw.write(text);
        fw.close();        
    }
	
	public static void deleteFile() {
		File file = new File(PATH);
		file.delete();
	}
	
}