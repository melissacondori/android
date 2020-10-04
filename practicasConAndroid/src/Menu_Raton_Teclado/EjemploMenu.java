package Menu_Raton_Teclado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.*;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Este programa genera un menú.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/03/2013
 */
public class EjemploMenu implements GLEventListener, ActionListener {
	
	/* GLJPanel donde se desplegará el dibujo */
	private static GLJPanel miLienzo;
	
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
	
	private void creaMenus(JFrame marco) {
		JMenuBar barra = new JMenuBar();
		
		JMenu menu1 = new JMenu("Colores");
		menu1.setMnemonic('c');
		
		menu11 = new JMenuItem("Rojo");
	    menu11.setMnemonic('r');
	    
		menu12 = new JMenuItem("Verde");
	    menu12.setMnemonic('v');
	    
		menu13 = new JMenuItem("Azul");
	    menu13.setMnemonic('a');
	    
	    menu11.addActionListener(this);
	    menu12.addActionListener(this);
	    menu13.addActionListener(this);
	    
	    menu1.add(menu11);
	    menu1.add(menu12);
	    menu1.add(menu13);
	    
	    barra.add(menu1);
	    
		marco.setJMenuBar(barra);
	}
		
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menu11) {
			r = 1.0f; g = 0.0f; b = 0.0f;
			miLienzo.repaint(); // redibuja
		} else if (e.getSource() == menu12) {
			r = 0.0f; g = 1.0f; b = 0.0f;
			miLienzo.repaint(); // redibuja
		} else if (e.getSource() == menu13) {
			r = 0.0f; g = 0.0f; b = 1.0f;
			miLienzo.repaint(); // redibuja
		}
	}
	
	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Menu");
		
		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(512, 512);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLJPanel */
		miLienzo = new GLJPanel();

		/* Se crea una instancia de la Clase */
		EjemploMenu miPanel = new EjemploMenu();
		
		/* Crea menú */
		miPanel.creaMenus(miMarco);

		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miLienzo.addGLEventListener(miPanel);

		/* Añadimos el GLJPanel al Componente del JFrame */
		miMarco.getContentPane().add(miLienzo);

		/* Por ultimo hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
