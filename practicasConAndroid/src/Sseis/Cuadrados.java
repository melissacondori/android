package Sseis;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;

/**
 * El programa abre una ventana la pinta de negro, luego dibuja un cuadrado en la
 * posición del ratón cada vez que se presiona el botón izquierdo. El programa
 * también se ejecuta correctamente cuando la ventana se mueve o se
 * redimensiona, pintando la nueva ventana de negro.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/05/2013
 * 
 */
public class Cuadrados implements GLEventListener, MouseMotionListener {

	/* GLJPanel donde se desplegará el dibujo */
	private static GLJPanel miLienzo;

	/* Mitad del lado del cuadrado */
	private static final float tam = 3.0f;
	
	/* La clases Random */
	private static Random random = new Random();
	
	/* Posición (x,y) del ratón */
	private static int Xelegido, Yelegido;

	/**
	 * Se ejecuta al inicio.
	 */
	public void init(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Inicializa la variable GLU */
		GLU glu = new GLU();

		/* Se obtiene la dimensión de la ventana */
		Dimension dim = miLienzo.getSize();

		/* Se define la ventana de despliegue */
		gl.glViewport(0, 0, dim.width, dim.height);

		/* Matriz de Proyección */
		gl.glMatrixMode(GL.GL_PROJECTION);

		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();

		/* Proyección Paralela */
		glu.gluOrtho2D(0.0, (double) dim.width, 0.0, (double) dim.height);

		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL.GL_MODELVIEW);

		/* Fondo negro */
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		/* Borra el buffer de la ventana */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * Se ejecuta para desplegar.
	 */
	public void display(GLAutoDrawable drawable) {

		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		if (Xelegido >= 0 && Yelegido >= 0)
			dibujaCuadrado(gl, Xelegido, Yelegido);
		else
			/* Borra el buffer de la ventana */
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * Se ejecuta cuando el usuario redimensiona la ventana.
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Inicializa la variable GLU */
		GLU glu = new GLU();

		/* Matriz de Proyección */
		gl.glMatrixMode(GL.GL_PROJECTION);

		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();

		/* Proyección Paralela */
		glu.gluOrtho2D(0.0, (double) w, 0.0, (double) h);

		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL.GL_MODELVIEW);

		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();

		/* Ventana de despliegue */
		gl.glViewport(0, 0, w, h);

		/* Borra el buffer de la ventana */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * Se ejecuta cuando se cambia el modo de despliegue (Ej de 16 a 32 bits o
	 * visceversa).
	 */
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
	}

	/* Métodos del MouseMotionListener */
	public void mouseDragged(MouseEvent e) {
		GLJPanel canvas = (GLJPanel) e.getComponent();

		Xelegido = e.getX();
		Yelegido = canvas.getHeight() - e.getY() - 1;
		canvas.display();
		Xelegido = -1;
		Yelegido = -1;
	}

	public void mouseMoved(MouseEvent arg0) {}

	// Dibuja un cuadrado con un color aleatorio.
	private void dibujaCuadrado(GL gl, int x, int y) {

		/* Color aleatorio */
		gl.glColor3f(random.nextFloat(), random.nextFloat(), random.nextFloat());

		/* Inicia secuencia del poligono */
		gl.glBegin(GL.GL_QUADS);

			/* lista de vertices (x,y) */
			gl.glVertex2f((float) (x + tam), (float) (y + tam));
			gl.glVertex2f((float) (x - tam), (float) (y + tam));
			gl.glVertex2f((float) (x - tam), (float) (y - tam));
			gl.glVertex2f((float) (x + tam), (float) (y - tam));

		/* Finaliza la secuencia */
		gl.glEnd();

		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();
	}

	public static void main(String[] args) {

		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Cuadrados");

		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(512, 512);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLCapabilities */
		GLCapabilities miCapacidad = new GLCapabilities();

		/* Solicita doble buffer en el modo de despliegue */
		miCapacidad.setDoubleBuffered(false);

		/* Se crea el objeto GLJPanel */
		miLienzo = new GLJPanel(miCapacidad);

		/* Se crea una instancia de la Clase */
		Cuadrados miPanel = new Cuadrados();

		/* Habilita auto intercambio del modo buffer */
		miLienzo.setAutoSwapBufferMode(false);

		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miLienzo.addGLEventListener(miPanel);

		/* Indicamos que el GLJPanel detecte los eventos cuando se arrastra el ratón */
		miLienzo.addMouseMotionListener((MouseMotionListener) miPanel);

		/* Añadimos el GLJPanel al Componente del JFrame */
		miMarco.getContentPane().add(miLienzo);

		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
