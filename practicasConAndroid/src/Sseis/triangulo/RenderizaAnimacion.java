package Sseis.triangulo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * Clase Renderiza (OpenGL)
 * 
 * @author Jhonny Felipez
 * @version 1.0 01/04/2014
 * 
 */
public class RenderizaAnimacion implements GLEventListener, MouseListener {
	
	/* Objeto */
	private Triangulo triangulo;
    
    /* Incremento de la animación */
	private float incremento = 0;
	
	/* Intervalo de la animación */
	private float intervalo = 100.0f;
	
	/* Se almacena el desplazamiento del triángulo */
	private float tx, ty;
	
	/* ¿Se realiza la animación? */
	private boolean animacion = true;
    
	/**
	 * Se ejecuta al inicio.
	 */
	public void init(GLAutoDrawable drawable) {

		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		triangulo = new Triangulo();

		/* Fondo negro */
		gl.glClearColor(0, 0, 0, 0);
	}

	/**
	 * Se ejecuta para desplegar.
	 */
	public void display(GLAutoDrawable drawable) {

		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Inicializa el buffer de la ventana y del z-buffer */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();

		/* Calcula desplazamiento */
		float theta = (float)(incremento / intervalo * Math.PI * 2.0f);

		tx = (float) (Math.cos(theta) * 2);
		ty = (float) (Math.sin(theta) * 2);
		
		/* Traslada */
		gl.glTranslatef(tx, ty,0);

		triangulo.dibuja(gl);

		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();
		
		/* Intercambia los buffers */
	    drawable.swapBuffers();

	    if (animacion)
	    	incremento = incremento + 1;
	}

	/**
	 * Se ejecuta cuando el usuario redimensiona la ventana.
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Inicializa la variable GLU */
		GLU glu = new GLU();

		/* Ventana de despliegue */
		gl.glViewport(0, 0, w, h);

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
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	public void mousePressed(MouseEvent mouse) {
	    switch (mouse.getButton()) {
	      case MouseEvent.BUTTON1:
	        animacion = !animacion;
	        break;
	      case MouseEvent.BUTTON2:
	      case MouseEvent.BUTTON3:
	    }
	}
}
