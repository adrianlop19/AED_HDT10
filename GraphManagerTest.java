/**
 * Tests
 * @author Adrián López
 * @version 21/05/2024
 *
 */

package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Files.GraphManager;
import Files.InvalidGraph;

class GraphManagerTest {

	@Test
	void testShorterRoute() {
		GraphManager manager = new GraphManager();
		String[] lines = new String[] {"Mixco Antigua 30", "Antigua Escuintla 25", "Escuintla Santa-Lucia 15", "Santa-Lucia Guatemala 90", "Guatemala Mixco 15", "Guatemala Antigua 40", "Escuintla Guatemala 70"};
		try {
			manager.fileToGraph(lines);
		} catch (InvalidGraph e) {
			// TODO Auto-generated catch block
			System.out.println("El grafo contiene vertices no conectados.");
		}
		assertEquals(manager.shorterRoute("Mixco", "Antigua"), "Ruta: 30.0 km");
	}

	@Test
	void testgetGraphCenter() {
		GraphManager manager = new GraphManager();
		String[] lines = new String[] {"Mixco Antigua 30", "Antigua Escuintla 25", "Escuintla Santa-Lucia 15", "Santa-Lucia Guatemala 90", "Guatemala Mixco 15", "Guatemala Antigua 40", "Escuintla Guatemala 70"};
		try {
			manager.fileToGraph(lines);
		} catch (InvalidGraph e) {
			// TODO Auto-generated catch block
			System.out.println("El grafo contiene vertices no conectados.");
		}
		assertEquals(manager.getGraphCenter(), "Antigua");
	}

	@Test
	void testBreakRoute() {
		GraphManager manager = new GraphManager();
		String[] lines = new String[] {"Mixco Antigua 30", "Antigua Escuintla 25", "Escuintla Santa-Lucia 15", "Santa-Lucia Guatemala 90", "Guatemala Mixco 15", "Guatemala Antigua 40", "Escuintla Guatemala 70"};
		try {
			manager.fileToGraph(lines);
		} catch (InvalidGraph e) {
			// TODO Auto-generated catch block
			System.out.println("El grafo contiene vertices no conectados.");
		}
		assertEquals(manager.shorterRoute("Mixco", "Antigua"), "Ruta: 30.0 km");
		assertEquals(manager.breakRoute("Mixco", "Antigua"),"Ruta eliminada correctamente, se han recalculado las rutas mas cortas.");
		assertEquals(manager.shorterRoute("Mixco", "Antigua"), "Ruta: 55.0 km\nCiudades intermedias: Guatemala");
		
	}

	@Test
	void testNewRoute() {
		GraphManager manager = new GraphManager();
		String[] lines = new String[] {"Mixco Antigua 30", "Antigua Escuintla 25", "Escuintla Santa-Lucia 15", "Santa-Lucia Guatemala 90", "Guatemala Mixco 15", "Guatemala Antigua 40", "Escuintla Guatemala 70"};
		try {
			manager.fileToGraph(lines);
			manager.newRoute("Guatemala", "Mexico", 70);
		} catch (InvalidGraph e) {
			// TODO Auto-generated catch block
			System.out.println("Ha ocurrido un error al manipular el grafo, saliendo del programa...");
		}
		assertEquals(manager.shorterRoute("Antigua", "Mexico"), "Ruta: 110.0 km\nCiudades intermedias: Guatemala");
		
	}

}
