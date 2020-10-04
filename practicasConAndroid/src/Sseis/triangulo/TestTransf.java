package Sseis.triangulo;

import javax.media.opengl.GLJPanel;
import javax.swing.JFrame;
/**
 * Transformaciones.
 * 
 * Programa que despliega un Triangulo en OpenGL, con glDrawArrays.
 * 
 * (glVertexPointer, glColorPointer, glEnableClientState)
 * 
 * @author Jhonny Felipez
 * @version 1.0 01/04/2014
 * 
 */
public class TestTransf extends JFrame {
	private static final long serialVersionUID = 1L;

	/* GLJPanel donde se renderiza el dibujo */
	private GLJPanel miLienzo;
	
	/**
     * Constructor de la Aplicación
     */
	public TestTransf() {
		
		/* Titulo de la ventana */
		setTitle("Transformación - DrawArray");

		/* Se le otorga un tamaño de la ventana */
		setSize(512, 512);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLJPanel */
		miLienzo = new GLJPanel();

		/* Se agrega la implementación de los eventos del OpenGL al GLJPanel */
		miLienzo.addGLEventListener(new RenderizaTransf());

		/* Añadimos el GLJPanel al Componente del JFrame */
		getContentPane().add(miLienzo);
		
	}
	
	public static void main(String[] args) {

		/* Primero se crea el objeto JFrame */
		TestTransf miMarco = new TestTransf();

		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
