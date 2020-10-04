package Menu_Raton_Teclado;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
import javax.swing.JFrame;

/**
 * Este programa detecta los eventos del teclado.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/03/2013
 */
public class EjemploTeclado implements GLEventListener, KeyListener {
	
	/* GLJPanel donde se desplegará el dibujo */
	private static GLJPanel miLienzo;
	
	/* Variables de color */
	private float r = 1.0f, g = 1.0f, b = 1.0f;
	
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
	    
	    /* Color de fondo */
		gl.glClearColor(r, g, b, 0.0f);

		/* Limpia el buffer de la ventana */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();
	}

	/**
     * Se ejecuta cuando el usuario redimensiona la ventana.
     */
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {}
	
	/**
     * Se ejecuta cuando se cambia el modo de despliegue (Ej de 16 a 32 bits o visceversa).
     */
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
	
	/* Métodos del KeyListener */
	
	/* Evento de pulsación de cualquier tecla */
	public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
	        case 'r': r = 1.0f; g = 0.0f; b = 0.0f; break;
	        case 'v': r = 0.0f; g = 1.0f; b = 0.0f; break;
	        case 'a': r = 0.0f; g = 0.0f; b = 1.0f; break;
	        
	        case 'q':
			case 27: // tecla Escape
				System.exit(0);
        }

        switch (e.getKeyCode()) {
        	//case KeyEvent.VK_ALT : r = 1.0f; g = 0.1f; b = 1.0f; break;
        	case KeyEvent.VK_F1  : r = 1.0f; g = 0.3f; b = 0.0f; break;
        	case KeyEvent.VK_F2  : r = 1.0f; g = 0.6f; b = 0.0f; break;
        	case KeyEvent.VK_UP  : r = 1.0f; g = 0.8f; b = 0.0f; break;
        }
        
        miLienzo.display();
	}

	/* Evento de liberación de cualquier tecla */
	public void keyReleased(KeyEvent e) {
		r = 1.0f; g = 1.0f; b = 1.0f;
		miLienzo.display();
	}
	
	/* Evento de pulsación de una tecla de acción */
	public void keyTyped(KeyEvent e) {}

	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Ejemplo del Manejo del Teclado");
		
		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(512, 512);
		
		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLJPanel */
		miLienzo = new GLJPanel();

		/* Se crea una instancia de la Clase */
		EjemploTeclado miPanel = new EjemploTeclado();

		/* Se adiciona los eventos del OpenGL al GLJPanel */
		miLienzo.addGLEventListener(miPanel);
		
		/* Indicamos que el GLJPanel detecte los eventos del teclado */
		miLienzo.addKeyListener((KeyListener) miPanel);
		
		/* Coloca miLienzo activo para que detecte los eventos del teclado */
		miLienzo.setFocusable(true);

		/* Añadimos el GLJPanel al Componente del JFrame */
		miMarco.getContentPane().add(miLienzo);

		/* Por ultimo hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
		
	}
}
