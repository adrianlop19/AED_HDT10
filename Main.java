/**
 * 
 */
//package Files;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase principal del programa.
 * @author Adrián López
 * @version 21/05/2024
 *
 */
public class Main {

	/**
	 * Metodo main del programa.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean mainEnd = false;
		GraphManager manager = new GraphManager();
		
		System.out.println("Bienvenido al sistema de verificaci�n de rutas.");
		while(!mainEnd) {
			boolean found = false;
			boolean end = false;
			while(!found) {
				String[] fileContent = null;
				try { //Se encuentra el archivo
					fileContent = FileController.readFile();
					manager.fileToGraph(fileContent);
					found = true;
				} catch (IOException e) { //Si no se encuentra el archivo
					System.out.println("\nArchivo no encontrado.\nPor favor, asegurese de que el archivo guategrafo.txt sea valido y se encuentre en la carpeta donde se ubica el programa.");
					System.out.println("Presione enter para volver a buscar el archivo.");
					scan.nextLine();
				} catch (InvalidGraph e) {
					System.out.println("El grafo contiene vertices no conectados.");
				}
			}				
			System.out.println("\nArchivo encontrado.");
			while(!end) {
				String menu = "\n1. Calcular la ruta mas corta entre dos ciudades.\n2. Mostrar la ciudad al centro del grafo.\n3. Modificar el grafo.\n4. Salir";
				int option = pregunta(menu, 4, scan);
				String origen = "";
				String destino = "";
				int peso = 0;
				switch(option) { 
				case 1:
					System.out.println("Ingrese la ciudad de origen:");
					origen = scan.nextLine();
					System.out.println("Ingrese la ciudad destino:");
					destino = scan.nextLine();
					System.out.println(manager.shorterRoute(origen,destino));
					break;
				case 2:
					System.out.println("La ciudad al centro del grafo es: "+ manager.getGraphCenter());
					break;
				case 3:
					String menuMod = "";
					System.out.println("Indique el tipo de modificacion a realizar.");
					menuMod = "1. Interrupcion de trafico entre dos ciudades.\n2. Nueva ruta entre dos ciudades";
					option = pregunta(menuMod,2,scan);
					switch(option) {
					case 1:
						System.out.println("Indique la ciudad de origen:");
						origen = scan.nextLine();
						System.out.println("Indique la ciudad destino:");
						destino = scan.nextLine();
						System.out.println(manager.breakRoute(origen,destino));
						break;
					case 2:
						System.out.println("Indique la ciudad de origen:");
						origen = scan.nextLine();
						System.out.println("Indique la ciudad destino:");
						destino = scan.nextLine();
						peso = numeroEntero("Indique la cantidad de kilometros entre ambas ciudades:", scan);
						try {
							System.out.println(manager.newRoute(origen,destino,peso));
						}catch (InvalidGraph e) {
							System.out.println("Ha ocurrido un error al manipular el grafo, saliendo del programa...");
							end = true;
							mainEnd = true;
						}
						break;
					default:
						System.out.println("Opcion no valida");
						break;
					}
					break;
				case 4:
					System.out.println("Gracias por utilizar el programa!"); 
					end = true;
					mainEnd = true;
					break;
				default: //Opcion no valida
					System.out.println("Opcion no valida");
					break;
				}				
			}
		}
		FileController.deleteFile();
		try {
			manager.rewriteFile();
		}catch(IOException e) {
			System.out.println("Ha ocurrido un error a reescribir el archivo.");
		}
	}
	
	/**
	 * Se encarga de solicitar al usuario que ingrese un dato en especifico y validarlo.
	 * @param pregunta. Mensaje a desplegar.
	 * @param opciones. Opciones consideradas como validas.
	 * @param scan Objeto Scanner.
	 * @return int.
	 */
	public static int pregunta(String pregunta, int opciones, Scanner scan)
	  {
	      boolean bucle = true;
	      int respuesta = 0;
	      try 
	      {
	          while(bucle)
	          {
	              System.out.println(pregunta);
	              respuesta = scan.nextInt();
	              scan.nextLine();
	              if(respuesta > 0 && respuesta <= opciones) bucle = false;
	              else System.out.println("\nRepuesta no valida.\n");
	          }    
	      } catch (Exception e) {
	          System.out.println("\nRepuesta no valida. Ingrese solamente numeros.\n");
	          scan.nextLine();
	          respuesta = pregunta(pregunta, opciones, scan);
	      }
	      return respuesta;
	  }
	
	/**
	 * Solicita al usuario que ingrese un entero
	 * @param pregunta Mensaje a imprimir
	 * @param scan Objeto Scanner
	 * @return Int.
	 */
	public static int numeroEntero(String pregunta, Scanner scan) {
		boolean bucle = true;
		int num = 0;
		try 
		{
			while(bucle)
			{
				System.out.println(pregunta);
				num = scan.nextInt();
				scan.nextLine();
				if(num > 0) bucle = false;
				else System.out.println("\nRepuesta no valida.\n");
			}    
		} catch (Exception e) {
			scan.nextLine();
			System.out.println("\nRepuesta no valida. Ingrese solamente numeros.\n");
			num = numeroEntero(pregunta, scan);
		}
		return num;
	}
}
