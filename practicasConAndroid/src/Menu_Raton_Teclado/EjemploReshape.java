package Menu_Raton_Teclado;


import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

/**
 * Reshape: Programa que despliega un Rectángulo en OpenGL.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/05/2013
 * 
 */
public class EjemploReshape implements GLEventListener {
	/**
	 * Se ejecuta al inicio.
	 */
	public void init(GLAutoDrawable drawable) {}

	/**
	 * Se ejecuta para desplegar.
	 */
	public void display(GLAutoDrawable drawable) {

		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Borra el buffer de la ventana */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		/* Inicia secuencia del poligono */
		gl.glBegin(GL.GL_POLYGON);

			/* lista de vertices (x,y) */
			gl.glVertex2f(-5f, -5f);
			gl.glVertex2f(5f, -5f);
			gl.glVertex2f(5f, 5f);
			gl.glVertex2f(-5f, 5f);

		/* Finaliza la secuencia */
		gl.glEnd();

		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();
	}

	/**
	 * Se ejecuta cuando el usuario redimensiona la ventana.
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {

		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Inicializa la variable GLU */
		GLU glu = new GLU();

		/* Se define la ventana de despliegue */
		gl.glViewport(0, 0, w, h);

		System.out.println(x + " " + y + " " + w + " " + h);

		/* Matriz de Proyección */
		gl.glMatrixMode(GL.GL_PROJECTION);

		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();

		/* Proyección paralela */
		glu.gluOrtho2D(-10, 10, -10, 10);
	}

	/**
	 * Se ejecuta cuando se cambia el modo de despliegue (Ej de 16 a 32 bits o visceversa).
	 */
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}

	public static void main(String[] args) {

		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Reshape");

		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(300, 300);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLJPanel */
		// GLCanvas miLienzo = new GLCanvas();
		GLJPanel miLienzo = new GLJPanel();

		/* Se adiciona los eventos del OpenGL al GLJPanel */
		miLienzo.addGLEventListener(new EjemploReshape());

		/* A continuacion insertamos el GLJPanel en el JFrame */
		miMarco.add(miLienzo);

		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
