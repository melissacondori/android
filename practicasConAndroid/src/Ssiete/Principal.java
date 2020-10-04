
package Ssiete;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLJPanel;
import javax.swing.JFrame;
import com.sun.opengl.util.FPSAnimator;

/**
 * Animaci�n en OpenGL utilizando Jerarquia.
 *  
 * @author Jhonny Felipez
 * @version 1.0 13/03/2014
 *
 */
public class Principal extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/* GLJPanel donde se renderiza el dibujo */
	private GLJPanel miLienzo;
	
	/**
     * Constructor de la Aplicaci�n
     */
	public Principal(){
		
		/* Titulo de la ventana */
		setTitle("Ejemplo de OpenGL (Auto)");

		/* Se le otorga un tama�o a la ventana */
		setSize(640, 480);

		/* Se ubica la ventana al centro de la pantalla */
		setLocationRelativeTo(null);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLCapabilities */
		GLCapabilities miCapacidad = new GLCapabilities();

		/* Solicita doble buffer en el modo de despliegue */
		miCapacidad.setDoubleBuffered(true);

		/* Se crea el objeto GLJPanel */
		miLienzo = new GLJPanel(miCapacidad);

		/* Panel donde se implementa los eventos del OpenGL */
		PanelRenderiza miPanel = new PanelRenderiza();

		/* Se agrega la implementaci�n de los eventos del OpenGL al GLJPanel */
		miLienzo.addGLEventListener(miPanel);

		/* Agrega animaci�n al GLJPanel - 60 cuadros por segundo */
		FPSAnimator animador = new FPSAnimator(miLienzo, 60);

		/* A�adimos el GLJPanel al Componente del JFrame */
		getContentPane().add(miLienzo);

		/* Inicia la animaci�n */
		animador.start();
		
	}
	
	/**
     * Inicia la aplicaci�n
     * @param args Par�metros no utilizados
     */
	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		Principal miMarco = new Principal();

		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);

	}
}
