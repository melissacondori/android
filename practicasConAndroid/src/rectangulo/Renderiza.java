package rectangulo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;

public class Renderiza implements GLEventListener, MouseListener {

	/* Objeto */
	Rectangulo rectangulo;
	
	/* Tamaño de la ventana en pixeles */
    private int ancho;
    private int alto;

	/**
	 * Se ejecuta al inicio.
	 */
	public void init(GLAutoDrawable drawable) {

		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		rectangulo = new Rectangulo();

		/* Fondo negro */
		gl.glClearColor(0, 0, 0, 0);
	}

	/**
	 * Se ejecuta para desplegar.
	 */
	public void display(GLAutoDrawable drawable) {

		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Borra el buffer de la ventana */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		rectangulo.dibuja(gl);

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
		
		/* Obtiene el ancho y el alto de la pantalla */
		ancho = w;
		alto = h;

		/* Ventana de despliegue */
		gl.glViewport(0, 0, ancho, alto);

		/* Matriz de Proyección */
		gl.glMatrixMode(GL.GL_PROJECTION);

		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();

		/* Proyección paralela */
		glu.gluOrtho2D(-5, 5, -5, 5);

		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL.GL_MODELVIEW);

		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
	}

	/**
	 * Se ejecuta cuando se cambia el modo de despliegue (Ej de 16 a 32 bits o visceversa).
	 */
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}
	
	/* Métodos del MouseListener */
	
	/* Evento cuando se oprime un botón del ratón */
	public void mouseClicked(MouseEvent e) {}
	
	/* Evento cuando el ratón entra al área */
	public void mouseEntered(MouseEvent arg0) {}
	
	/* Evento cuando el ratón sale del área */
	public void mouseExited(MouseEvent arg0) {}
	
	/* Evento de soltar el botón del ratón */
	public void mouseReleased(MouseEvent arg0) {}

	/* Evento de oprimir un boton del ratón */
	public void mousePressed(MouseEvent e) {
		/* Obtiene la coordenada de la pantalla */
		int x = e.getX();
		int y = e.getY();
	    if (e.getButton() == MouseEvent.BUTTON1) {
	    	//y = alto - y;
	    	 /* Verifica área digitada */
			if (( (2/10.0) * ancho < x && x < (5/10.0) * ancho) && ( (2/10.0) * alto < y && y < (5/10.0) * alto ))
				System.out.println("Rojo");
			else if (( (5/10.0) * ancho < x && x < (8/10.0) * ancho) && ( (2/10.0) * alto < y && y < (5/10.0) * alto ))
				System.out.println("Azul");
	    }
	}
}
