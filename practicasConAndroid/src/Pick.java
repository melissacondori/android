//package s02_interaccion;

/**
 * Pick3.java		1.0 13/08/2013
 */
import java.awt.Point;
import java.awt.event.*;
import java.nio.IntBuffer;
import javax.swing.JFrame;
import javax.media.opengl.*;
import static javax.media.opengl.GL.*;
import javax.media.opengl.glu.GLU;
import com.sun.opengl.util.BufferUtil;

/**
 * Este programa dibuja un cuadrado rojo y otro azul. Cuando se elije un
 * objeto con el ratón, el programa le indica que objeto se ha seleccionado.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/08/2013
 */
public class Pick implements GLEventListener, MouseListener, KeyListener {
	/* Inicializa la variable GLU */
    GLU  glu  = new GLU();
    
	private final int TAMBUFFER = 512;
	
	private Point punto;

	public void init(GLAutoDrawable drawable) {
		/* Inicializa la variable GL*/
		GL gl = drawable.getGL();
		
		/* Color de conFondo (rojo,verde,azul,alfa) alfa [0..1]: 1 totalmente opaco, 0 totalmente trasparente */
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
	}

	public void display(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Borra el buffer de la ventana */
		gl.glClear(GL_COLOR_BUFFER_BIT);

		if (punto != null)
			seleccionaRectangulo(gl);

		dibujaObjetos(gl, GL_RENDER);
		
		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL(); 

		/* Se define la ventana de despliegue */
		gl.glViewport(0, 0, w, h); 
		
		/* Matriz de Proyección */
		gl.glMatrixMode(GL_PROJECTION); 
		
		/* Inicializa la matriz de la proyección */
		gl.glLoadIdentity();

		/* Proyección paralela */
		glu.gluOrtho2D(-2.0f, 2.0f, -2.0f, 2.0f); 

		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL_MODELVIEW);
		
		/* Inicializa MVM */
		gl.glLoadIdentity();
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}

	/* Métodos del MouseListener */
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			GLJPanel lienzo = (GLJPanel) e.getComponent();
			punto = e.getPoint();
			// Actualmente el proceso de pick se realiza en el display() porque
			// las llamadas OpenGL tiene que ser realizados en los metodos
			// GLEventListener.
			lienzo.display();
        }
	}

	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}

	/* Métodos del KeyListener */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 27)
			System.exit(0);
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public static void dibujaObjetos(GL gl, int modo) {
		
		if (modo == GL_SELECT) { // ¿Modo selección?
			gl.glLoadName(1); // pop() y push(1)
			//gl.glPopName();
			//gl.glPushName(1);
			//gl.glPushName(3);
		}
		
		/* Color rojo */
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		
		/* Rectángulo */
		gl.glRectf(-0.5f, -0.5f, 1.0f, 1.0f);
		
		if (modo == GL_SELECT) // ¿Modo selección?
			gl.glLoadName(2); // pop() y push(2)
		
		/* Color azul */
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		
		/* Rectángulo */
		gl.glRectf(-1.0f, -1.0f, 0.5f, 0.5f);
	}
	
	/*
	 *  Habilita el modo de selección, nombre de la pila y la matriz de proyección
	 *  para seleccionar. Luego los objetos son dibujados.
	 */
    private void seleccionaRectangulo(GL gl) {
		int[] visor = new int[4];
		
		/* Buffer de IntBuffer */
		IntBuffer bufferObjSelec = BufferUtil.newIntBuffer(TAMBUFFER);
		
		/* Buffer de enteros */
		int[] bufferIntObjSelec = new int[TAMBUFFER];

		/* Obtiene los datos del VIEWPORT (x,y,w,h) */
		gl.glGetIntegerv(GL_VIEWPORT, visor, 0);
		
		/* Selecciona el buffer de nombres (objetos) */  
		gl.glSelectBuffer(TAMBUFFER, bufferObjSelec); // 
		
		/* Renderizado en el modo selección */
		gl.glRenderMode(GL_SELECT);

		/* Inicializa la pila de nombres. */
		gl.glInitNames(); // Crea la pila de nombres.
		gl.glPushName(0); // Inserta un nombre.
		
		/* Matriz de Proyección */
		gl.glMatrixMode(GL_PROJECTION);
		
		/* push(Matriz-Proy) */
		gl.glPushMatrix();
		
		/* Inicializa la matriz de la proyección */
		gl.glLoadIdentity();

		// El método gluPickMatrix() restringe el área donde openGL desplegará los objetos.
		// gluPickMatrix(double x, double y, double ancho, double alto, int viewport[3], int desplaza_viewport)
		// Define una región seleccionada de 5x5 pixeles cerca de la posición del cursor 
		glu.gluPickMatrix((double) punto.x,
				(double) (visor[3] - punto.y), 5.0, 5.0, visor, 0);

		/* Proyección Paralela */
		glu.gluOrtho2D(-2.0, 2.0, -2.0, 2.0);
		
		dibujaObjetos(gl, GL_SELECT);

		/* Matriz de Proyección */
		gl.glMatrixMode(GL.GL_PROJECTION);
		
		/* Matriz-Proy = pop() */
		gl.glPopMatrix();
		
		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();

		/* Retorna al renderizado del modo normal */
		int hits = gl.glRenderMode(GL_RENDER);
		
		/* De IntBuffer a Arreglo de enteros */
		bufferObjSelec.get(bufferIntObjSelec);
		
		/* Procesa los hits del renderizado en el modo de selección */ 
		procesaHits(hits, bufferIntObjSelec);
    }

    /*
     * Imprime el arreglo que contiene la información del área seleccionada.
     */ 
	private void procesaHits(int hits, int[] buffer) {
		int cantNombres;
		int indiceBuf = 0;
		System.out.println("hits = " + hits);
		for (int i = 0; i < hits; i++) { /* por cada hit */
			cantNombres = buffer[indiceBuf];
			indiceBuf = indiceBuf + 3;
			for (int j = 0; j < cantNombres; j++) { /* por cada nombre */
				if (buffer[indiceBuf] == 1)
					System.out.println("rectángulo rojo");
				else if (buffer[indiceBuf] == 2)
					System.out.println("rectángulo azul");
				indiceBuf++;
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {

		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Pick3");

		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(512, 512);
			    
		/* Se ubica la ventana al centro de la pantalla */
		miMarco.setLocationRelativeTo(null);

		/* Nos aseguramos que al cerrar la ventana el programa finalice. */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Se crea el objeto GLJPanel */
		GLJPanel miLienzo = new GLJPanel(new GLCapabilities());
		
		/* Se crea una instancia de la Clase */
		Pick miPanel = new Pick();

		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miLienzo.addGLEventListener(miPanel);
		
		/* Indicamos que el GLJPanel detecte los eventos del ratón */
		miLienzo.addMouseListener((MouseListener) miPanel);

		/* Indicamos que el GLJPanel detecte los eventos del teclado */
		miLienzo.addKeyListener((KeyListener) miPanel);
		
		/* Coloca miLienzo activo para que detecte los eventos del teclado */
		miLienzo.setFocusable(true);

		/* Añadimos el GLJPanel al Componente del JFrame */ 
	    miMarco.getContentPane().add(miLienzo);
		
	    /* Hacemos visible el elemento de mayor nivel */
	    miMarco.setVisible(true);
	    
	}
}
