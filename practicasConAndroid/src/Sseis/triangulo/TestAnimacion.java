package Sseis.triangulo;
import java.awt.event.MouseListener;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLJPanel;
import javax.swing.JFrame;
import com.sun.opengl.util.FPSAnimator;
/**
 * Animación.
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
     * Inicia la aplicación
     * @param args Parámetros no utilizados
     */
	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Animación - DrawArray");

		/* Se le otorga un tamaño de la ventana */
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

		/* Se agrega la implementación de los eventos del OpenGL al GLJPanel */
		miLienzo.addGLEventListener(miPanel);
		
		/* Indicamos que el GLJPanel detecte los eventos del ratón */
		miLienzo.addMouseListener((MouseListener) miPanel);
		
		/* Agrega animación al GLJPanel - 60 cuadros por segundo */
		FPSAnimator animador = new FPSAnimator(miLienzo, 20);

		/* Añadimos el GLJPanel al Componente del JFrame */
		miMarco.getContentPane().add(miLienzo);
		
		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);

		/* Inicia la animación */
		animador.start();
	}
}
