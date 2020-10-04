package Sseis.triangulo;
import java.awt.event.MouseListener;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLJPanel;
import javax.swing.JFrame;
import com.sun.opengl.util.FPSAnimator;
/**
 * Animaci�n.
 * 
 * Programa que despliega un Triangulo en OpenGL, con glDrawArrays.
 * 
 * (glVertexPointer, glColorPointer, glEnableClientState)
 * 
 * @author Jhonny Felipez
 * @version 1.0 01/04/2014
 * 
 */
public class TestAnimacion {
	
	/**
     * Inicia la aplicaci�n
     * @param args Par�metros no utilizados
     */
	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Animaci�n - DrawArray");

		/* Se le otorga un tama�o de la ventana */
		miMarco.setSize(512, 512);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLCapabilities */
		GLCapabilities miCapacidad = new GLCapabilities();

		/* Solicita doble buffer en el modo de despliegue */
		miCapacidad.setDoubleBuffered(true);

		/* Se crea el objeto GLJPanel */
		GLJPanel miLienzo = new GLJPanel(miCapacidad);
		
		/* Se crea una instancia de la Clase */
		RenderizaAnimacion miPanel = new RenderizaAnimacion();

		/* Se agrega la implementaci�n de los eventos del OpenGL al GLJPanel */
		miLienzo.addGLEventListener(miPanel);
		
		/* Indicamos que el GLJPanel detecte los eventos del rat�n */
		miLienzo.addMouseListener((MouseListener) miPanel);
		
		/* Agrega animaci�n al GLJPanel - 60 cuadros por segundo */
		FPSAnimator animador = new FPSAnimator(miLienzo, 20);

		/* A�adimos el GLJPanel al Componente del JFrame */
		miMarco.getContentPane().add(miLienzo);
		
		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);

		/* Inicia la animaci�n */
		animador.start();
	}
}
