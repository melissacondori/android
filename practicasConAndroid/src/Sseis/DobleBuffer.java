package Sseis;
/*
 * @(#)DobleBuffer.java	1.0	13/07/2012
 */ 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.media.opengl.*;

import com.sun.opengl.util.FPSAnimator;

/**
 * Este es un simple programa de doble buffer. Presione el botón izquierdo del ratón
 * para rotar el rectangulo. Presione el botón del medio o el botón derecho del ratón 
 * para detener la rotación.
 *  
 * @author J Felipez
 * @version 1.0 13/07/2012
 */
public class DobleBuffer implements GLEventListener, MouseListener, KeyListener {
	private float angulo = 0.0f, INCREMENTO = 0.0f;

	public void init(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();
		
		/* Fondo negro */
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	    
	    /* Modo plano: De un solo color, sin interpolación de colores */
	    gl.glShadeModel(GL.GL_FLAT);
	}

	public void display(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Borra el buffer de la ventana y del z-buffer */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		/* Push MVM */
		gl.glPushMatrix();
		
		/* Rota */
		gl.glRotatef(angulo, 0.0f, 0.0f, 1.0f);
		
		/* Color blanco */
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		
		/* Rectangulo */
		gl.glRectf(-25.0f, -25.0f, 25.0f, 25.0f);
		
		/* Pop MVM */
		gl.glPopMatrix();
		
	    /* Intercambia los buffers */
	    drawable.swapBuffers();
		
		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();

		incrementaAngulo();
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();
		
		/* Ventana de despliegue */
	    gl.glViewport(0, 0, w, h);
	    
	    /* Matriz de Proyección */
	    gl.glMatrixMode(GL.GL_PROJECTION);
	    
	    /* Inicializa la Matriz de Proyección */
	    gl.glLoadIdentity();
	    
	    /* Proyección Paralela */
	    if (w <= h) 
	        gl.glOrtho (-50.0, 50.0, -50.0*(float)h/(float)w, 
	            50.0*(float)h/(float)w, -1.0, 1.0);
	    else 
	        gl.glOrtho (-50.0*(float)w/(float)h, 
	            50.0*(float)w/(float)h, -50.0, 50.0, -1.0, 1.0);
	    
	    /* Matriz del Modelo-Vista */
	    gl.glMatrixMode(GL.GL_MODELVIEW);
	    
	    /* Inicializa la Matriz del Modelo-Vista */
	    gl.glLoadIdentity();
	}
	
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}

	/* Métodos del MouseListener */
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	public void mousePressed(MouseEvent mouse) {
	    switch (mouse.getButton()) {
	      case MouseEvent.BUTTON1:
	        INCREMENTO = 2f;
	        break;
	      case MouseEvent.BUTTON2:
	      case MouseEvent.BUTTON3:
	        INCREMENTO = 0f;
	        break;
	    }
	}

	/* Métodos del KeyListener */
	public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
        case 'q':
		case 27: // tecla Escape
			System.exit(0);
        }
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	/* Se incrementa el ángulo */
	private void incrementaAngulo() {
	    angulo = angulo + INCREMENTO;
	    if (angulo > 360f) 
	    	angulo = angulo - 360;
	}
	
	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Doble buffer");

		/* Se le otorga un tamaño a la ventana */
	    miMarco.setSize(512, 512);
			    
	    /* Ubicar la ventana al centro de la pantalla */
	    miMarco.setLocationRelativeTo(null);

	    /* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLCapabilities */
		GLCapabilities miCapacidad = new GLCapabilities();
		
		/* Solicita doble buffer en el modo de despliegue */
		miCapacidad.setDoubleBuffered(true);
		
	    /* Se crea el objeto GLJPanel */
	    GLJPanel miLienzo = new GLJPanel(miCapacidad);
	    
	    /* Se crea una instancia de la Clase */
		DobleBuffer miPanel = new DobleBuffer();

		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miLienzo.addGLEventListener(miPanel);
		
		/* Indicamos que el GLJPanel detecte los eventos del ratón */
		miLienzo.addMouseListener((MouseListener) miPanel);

		/* Indicamos que el GLJPanel detecte los eventos del teclado */
		miLienzo.addKeyListener((KeyListener) miPanel);
		
		/* Coloca miLienzo activo para que detecte los eventos del teclado */
		miLienzo.setFocusable(true);
	    
		/* Agrega animación al GLJPanel - 60 cuadros por segundo */
	    FPSAnimator animador = new FPSAnimator(miLienzo, 60);
	    
	    /* Añadimos el GLJPanel al Componente del JFrame */
	    miMarco.getContentPane().add(miLienzo);
		
	    /* Hacemos visible el elemento de mayor nivel */
	    miMarco.setVisible(true);
	    
	    /* Inicia la animación */
	    animador.start();
	}
}