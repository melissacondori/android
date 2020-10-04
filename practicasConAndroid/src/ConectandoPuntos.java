/**
 * ConectandoPuntos.java		1.0 13/09/2011
 */
import java.awt.event.*;
import javax.swing.JFrame;
import javax.media.opengl.*;
import static javax.media.opengl.GL.*;
import javax.media.opengl.glu.GLU;

/**
 * Este programa dibuja lineas conectadas, ubicando los vertices(puntos) con el ratón.
 * 
 * Para utilizar: 
 * 		Click izquierdo para ubicar un punto. 
 * 			Número máximo de puntos permitidos 64. 
 * 		Presione "i" para elim inar el primer punto. 
 * 		Presione "f" para eliminar el último punto.
 * 		Presione escape para salir.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/09/2011
 */
public class ConectandoPuntos implements GLEventListener, MouseListener, KeyListener {
	final int maxNumPtos = 64;
	float[][] puntos = new float[maxNumPtos][2];
	int numPtos;
	
	// Tamaño de la ventana en pixeles
	int altoVentana;
	int anchoVentana;

	public void init(GLAutoDrawable drawable) {
		/* Inicializa la variable GL*/
		GL gl = drawable.getGL();
		
		/* Borra el buffer de la ventana */
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

		/* Tamaño de los puntos */
		gl.glPointSize(8);
		
		/* Ancho de la linea */
		gl.glLineWidth(5);

		/*
		 * Las siguientes instrucciones logran que OpenGL cree puntos redondos,
		 * además de puntos y lineas con antialias. (Esta implementación
		 * puede reducir el tiempo de la renderizacion considerablemente.)
		 */
		gl.glEnable(GL_POINT_SMOOTH); // habilita la mejora de la calidad de los puntos
		gl.glHint(GL_POINT_SMOOTH_HINT, GL_NICEST); // Redondea puntos
		gl.glEnable(GL_LINE_SMOOTH); // habilita la calidad mas uniforme de la linea
		gl.glEnable(GL_BLEND); // habilita la combinación de colores 
		gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // define operación
		gl.glHint(GL_LINE_SMOOTH_HINT, GL_NICEST); // define la calidad de suavizado
	}

	public void display(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL(); //
		
		/* Limpia el buffer de la ventana */
		gl.glClear(GL_COLOR_BUFFER_BIT);

		/* Dibuja las lineas */
		gl.glColor3f(1.0f, 0.0f, 0.8f); // linea morada
		if (numPtos > 1) {
			gl.glBegin(GL_LINE_STRIP); // inicia secuencia: lineas conectadas.
				for (int i = 0; i < numPtos; i++)
					gl.glVertex2f(puntos[i][0], puntos[i][1]); // vertice (x,y)
			gl.glEnd(); // finaliza secuencia
		}

		/* Dibuja los puntos */
		gl.glColor3f(0.0f, 0.0f, 0.0f); // puntos negros
		if (numPtos > 1) {
			gl.glBegin(GL_POINTS); // inicia secuencia: puntos.
				for (int i = 0; i < numPtos; i++)
					gl.glVertex2f(puntos[i][0], puntos[i][1]); // vertice (x,y)
			gl.glEnd(); // finaliza secuencia
		}

		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();
		
		/* Inicializa la variable GLU */
		GLU glu = new GLU();
		
		/* Se requiere que el alto y ancho sean positivos */
		anchoVentana = (w > 1) ? w : 2; // ancho de la ventana
		altoVentana = (h > 1) ? h : 2; // alto de la ventana
		
		/* Se define la ventana de despliegue */
		gl.glViewport(0, 0, w, h);
		
		/* Matriz de Proyección */
		gl.glMatrixMode(GL_PROJECTION);
		
		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();

		/* La vista será de [0,1] x [0,1] */
		/* Proyección en paralelo en 2D */
		glu.gluOrtho2D(0.0f, 1.0f, 0.0f, 1.0f);

		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL_MODELVIEW);

		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}

	/* Metodos del MouseListener */
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	public void mousePressed(MouseEvent e) {
		/* Recupera la referencia del GLJPanel */
		GLJPanel lienzo = (GLJPanel) e.getComponent();
		
		/* Presionando el boton izquierdo ubicamos un punto */
		if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
			
			float xPos = ((float) e.getX()) / ((float) (anchoVentana - 1)); // 0..1
			float yPos = ((float) e.getY()) / ((float) (altoVentana - 1)); // 0..1
			
			/* Modifica y, porque el 'y' del openGL está invertido */
			yPos = 1.0f - yPos; 

			System.out.println(anchoVentana + "  " + altoVentana);
			System.out.println(e.getX() + "  " + e.getY());
			System.out.println(xPos + "  " + yPos);

			/* Se adiciona puntos */
			adiNuevoPunto(xPos, yPos);
			
			/* Si algo se cambio: se redespliega */
			lienzo.display();
		}
	}
	
	/* Metodos del KeyListener */
	public void keyPressed(KeyEvent e) {
		GLJPanel lienzo = (GLJPanel) e.getComponent();
		switch (e.getKeyChar()) {
		case 'i':
			eliminaPrimerPunto();
			lienzo.display();
			break;
		case 'f':
			eliminaUltimoPunto();
			lienzo.display();
			break;
		case 27: // tecla Escape
			System.exit(0);
		}
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}

	public void eliminaPrimerPunto() {
		if (numPtos > 0) {
			// Elimina primer punto, recorriendo el resto a una posicion antes del vector.
			numPtos--;
			for (int i = 0; i < numPtos; i++) {
				puntos[i][0] = puntos[i + 1][0];
				puntos[i][1] = puntos[i + 1][1];
			}
		}
	}

	public void eliminaUltimoPunto() {
		numPtos = (numPtos > 0) ? numPtos - 1 : numPtos;
	}

	/*
	 * Adiciona un nuevo punto al final de la lista.
	 * Elimina el primer punto de la lista si hay muchos puntos.
	 */
	public void adiNuevoPunto(float x, float y) {
		if (numPtos >= maxNumPtos) {
			eliminaUltimoPunto();
		}
		puntos[numPtos][0] = x;
		puntos[numPtos][1] = y;
		numPtos++;
	}
	
	public static void main(String[] args) {

		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Conectando Puntos");

		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(512, 512);
			    
		/* Se ubica la ventana al centro de la pantalla */
		miMarco.setLocationRelativeTo(null);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Se crea el objeto GLJPanel */
		GLJPanel miLienzo = new GLJPanel(new GLCapabilities());
		
		/* Se crea una instancia de la Clase */
		ConectandoPuntos miPanel = new ConectandoPuntos();

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
