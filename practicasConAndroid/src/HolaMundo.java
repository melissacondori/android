import javax.swing.JFrame;
import javax.media.opengl.*;

/**
 * Programa que despliega un Rectángulo en OpenGL.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/08/2011
 *
 */
public class HolaMundo implements GLEventListener {

	/**
     * Se ejecuta al inicio.
     */
	public void init(GLAutoDrawable arg0) {}
	
	/**
     * Se ejecuta para desplegar.
     */
	public void display(GLAutoDrawable drawable) {
		
		/* Inicializa la variable GL*/
	    GL gl = drawable.getGL();
	    
	    /* Fondo negro */
		gl.glClearColor(0, 0, 0, 0);
		
		/* Borra el buffer de la ventana */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		/* Color blanco */
		gl.glColor3f(1, 1, 1);
		
		/* Proyección en paralelo */ 
		gl.glOrtho(-1, 1, -1, 1, -1, 1);
		
		/* Inicia secuencia: poligono */ 
		gl.glBegin(GL.GL_POLYGON);

			/* lista de vertices (x,y) */
			gl.glVertex2f(-0.5f, -0.5f);
			gl.glVertex2f(-0.5f, 0.5f);
			gl.glVertex2f(0.5f, 0.5f);
			gl.glVertex2f(0.5f, -0.5f);

		/* Finaliza secuencia */
		gl.glEnd();
		
		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush(); 
	}

	/**
     * Se ejecuta cuando el usuario redimensiona la ventana.
     */
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {}
	
	/**
     * Se ejecuta cuando se cambia el modo de despliegue (Ej de 16 a 32 bits o visceversa).
     */
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}

	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Hola Mundo JOGL");
		
		/* Se le otorga un tamaño de la ventana */
		miMarco.setSize(300, 300);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Se crea el objeto GLJPanel */
		// GLCanvas miLienzo = new GLCanvas();
		GLJPanel miLienzo = new GLJPanel();
		
		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miLienzo.addGLEventListener(new HolaMundo());
		
		/* A continuacion insertamos el GLJPanel en el JFrame */
		miMarco.add(miLienzo);
		
		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
