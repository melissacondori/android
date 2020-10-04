package rectangulo;

import java.awt.event.MouseListener;
import javax.media.opengl.GLJPanel;
import javax.swing.JFrame;

/**
 * TestInteraccion.
 * 
 * Programa que interactua con el usuario en OpenGL, con glDrawArrays.
 * 
 * (glVertexPointer, glColorPointer, glEnableClientState)
 * 
 * @author Jhonny Felipez
 * @version 1.0 21/08/2014
 * 
 */
public class TestInteraccion {

	public static void main(String[] args) {

		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Detecta areas (DrawArray)");

		/* Se le otorga un tamaño de la ventana */
		miMarco.setSize(300, 300);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLJPanel */
		// GLCanvas miLienzo = new GLCanvas();
		GLJPanel miLienzo = new GLJPanel();

		/* Se crea una instancia de la Clase */
		Renderiza miPanel = new Renderiza();

		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miLienzo.addGLEventListener(miPanel);
		
		/* Indicamos que el GLJPanel detecte los eventos del ratón */
		miLienzo.addMouseListener((MouseListener) miPanel);

		/* Añadimos el GLJPanel al Componente del JFrame */
		miMarco.getContentPane().add(miLienzo);

		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}

}
