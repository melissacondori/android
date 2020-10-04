package Menu_Raton_Teclado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.media.opengl.GL;
import static javax.media.opengl.GL.*;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
/**
 * Este programa genera un menú popup.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/03/2013
 */
public class EjemploPopupMenu implements GLEventListener, MouseListener, ActionListener {
	
	/* GLJPanel donde se desplegará el dibujo */
	private static GLJPanel miLienzo;
	
	/* Variable JPopupMenu */
	private JPopupMenu botonDerecho;
	
	/* Opciones del menu */
	private JMenuItem menu11, menu12, menu13;
	
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
	    
	    /* Fondo Negro */
		gl.glClearColor(r, g, b, 0.0f);

		/* Limpia el buffer de la ventana */
		gl.glClear(GL_COLOR_BUFFER_BIT);
		
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
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3)
			botonDerecho.show(e.getComponent(), e.getX(),
					e.getY());
	}
	
	private void creaMenusPopup() {

		// Crea Pop Up Menú
		JMenu menu1 = new JMenu("Colores");

		menu11 = new JMenuItem("Rojo");
		menu11.setMnemonic('R');
		
		menu12 = new JMenuItem("Verde");
		menu12.setMnemonic('V');
		
		menu13 = new JMenuItem("Azul");
		menu13.setMnemonic('A');
		
		// Agrega opciones al JMenu.
		menu1.add(menu11);
		menu1.add(menu12);
		menu1.add(menu13);
		
		// Crea JPopupMenu.
		botonDerecho = new JPopupMenu();
		
		// Adiciona el JMenu al JPopupMenu
		botonDerecho.add(menu1);
		
		// Para que detecte si se pulsa un botón
		menu11.addActionListener(this);
	    menu12.addActionListener(this);
	    menu13.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menu11) {
			r = 1.0f; g = 0.0f; b = 0.0f;
		} else if (e.getSource() == menu12) {
			r = 0.0f; g = 1.0f; b = 0.0f;
		} else if (e.getSource() == menu13) {
			r = 0.0f; g = 0.0f; b = 1.0f;
		}
		miLienzo.repaint(); // redibuja
	}
	
	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("JPopupMenu");
		
		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(512, 512);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLJPanel */
		miLienzo = new GLJPanel();

		/* Se crea una instancia de la Clase */
		EjemploPopupMenu miPanel = new EjemploPopupMenu();

		/* Crea menu Popup */
		miPanel.creaMenusPopup();
		
		/* Se adiciona los eventos del OpenGL al GLJPanel */
		miLienzo.addGLEventListener(miPanel);
		
		/* Indicamos que el GLJPanel detecte los eventos del ratón */
		miLienzo.addMouseListener((MouseListener) miPanel);

		/* Añadimos el GLJPanel al Componente del JFrame */
		miMarco.getContentPane().add(miLienzo);
		
		/*
	     * Se solicita que la ventana del GLJPanel esté activa
	     * (para que detecte eventos del ratón y teclado)
	     */
	    miLienzo.requestFocusInWindow();

		/* Por ultimo hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
