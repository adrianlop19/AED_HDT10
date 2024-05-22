package Files;

/**
 * Controlador de expresiones invalidad.
 * 
 * @author Adrián López
 * @version 21/05/2024
 *
 */
public class InvalidGraph extends Exception {

	/**
	 * Metodo constructor.
	 * 
	 * @param message
	 */
	public InvalidGraph(String message) {
		super(message);
	}

	/**
	 * Metodo constructor Excepcion para indicar una expresion invalida.
	 */
	public InvalidGraph() {
		super("El grafo ingresado contiene vertices independientes.");
	}
}
