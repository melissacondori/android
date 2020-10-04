package Menu_Raton_Teclado;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class EjemploRaton implements GLEventListener, MouseListener {
	
	/* GLJPanel donde se desplegará el dibujo */
	private static GLJPanel miLienzo;
	
	/* Variables de color */
	private float r = 1.0f, g = 1.0f, b = 1.0f;
	
	/**
     * Se ejecuta al inicio.
     */
	public void init(GLAutoDrawable drawable) {
		// drawable.addMouseListener(this);
		// drawable.addKeyListener(this);
	}
	
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
	
	/* Métodos del MouseListener */
	
	/* Evento cuando se oprime un botón del ratón */
	public void mouseClicked(MouseEvent e) {
		/*int xPos = e.getX();
		int yPos = e.getY();
		String texto = "Se hizo clic " + e.getClickCount() + " Veces";
		if (e.isMetaDown()) // botón derecho del ratón
			texto += " con el botón derecho del ratón";
		else if (e.isAltDown()) // botón de en medio del ratón
			texto += " con el botón central del ratón";
		else
			// botón izquierdo del ratón
			texto += " con el botón izquierdo del ratón";
		//barraEstado.setText( "Se hizo clic en [" + evento.getX() + ", " + evento.getY() + "]" );
*/	}
	
	/* Evento cuando el ratón entra al área */
	public void mouseEntered(MouseEvent arg0) {
		r = 1.0f; g = 1.0f; b = 0.0f;
		miLienzo.display();
	}
	
	/* Evento cuando el ratón sale del área */
	public void mouseExited(MouseEvent arg0) {
		r = 0.0f; g = 0.0f; b = 0.0f;
		miLienzo.display();
	}
	
	/* Evento de soltar el botón del ratón */
	public void mouseReleased(MouseEvent arg0) {
		r = 1.0f; g = 1.0f; b = 1.0f;
		miLienzo.display();
	}

	/* Evento de oprimir un boton del ratón */
	public void mousePressed(MouseEvent mouse) {
	    switch (mouse.getButton()) {
	      case MouseEvent.BUTTON1:
	    	  r = 1.0f; g = 0.0f; b = 0.0f; break;
	      case MouseEvent.BUTTON2:
	    	  r = 0.0f; g = 1.0f; b = 0.0f; break;
	      case MouseEvent.BUTTON3:
	    	  r = 0.0f; g = 0.0f; b = 1.0f; break;
	    }
	    miLienzo.display();
	}

	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Ejemplo del Manejo del Ratón");
		
		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(512, 512);
		
		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLJPanel */
		miLienzo = new GLJPanel();

		/* Se crea una instancia de la Clase */
		EjemploRaton miPanel = new EjemploRaton();

		/* Se adiciona los eventos del OpenGL al GLJPanel */
		miLienzo.addGLEventListener(miPanel);
		
		/* Indicamos que el GLJPanel detecte los eventos del ratón */
		miLienzo.addMouseListener((MouseListener) miPanel);

		/* Añadimos el GLJPanel al Componente del JFrame */
		miMarco.getContentPane().add(miLienzo);

		/* Por ultimo hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
