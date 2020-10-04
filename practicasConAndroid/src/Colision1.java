/*
 * Colision1.java
 *
 * Dibuja un circulo y determina si este se intersecta con otro circulo
 * Este programa dibuja un circulo azul de fondo y dibuja un circulo rojo 
 * al rededor del cursor.
 * 
 * Modifique el programa, para adicionar código al método verificaColision(),
 * para determinar si dos circulos se intersectan.
 *
 * Para usar:
 *   Haga click para crear un segundo circulo
 *   Presione la tecla Esc para salir.
 *   
 * @author J Felipez
 * @version 1.0, 13/07/2012   
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.*;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import com.sun.opengl.util.FPSAnimator;

public class Colision1 implements GLEventListener, MouseListener, KeyListener {

    class Punto {
        int x;
        int y;
    
        public Punto() {}
    
        public Punto(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    class Circulo {
        Punto centro;
        int radio;
    
        public Circulo() {}
    
        public Circulo(Punto centro, int radio) {
            this.centro = centro;
            this.radio = radio;
        }
    }

    /* Número de segmentos para dibujar el circulo */
    static final int MAXGRADO = 72; 
    
    /* ¿El usuario ha hecho una selección? */
    int numPuntos = 0; 

    /* Guarda la referencia del canvas para simular glut.glutPostRedisplay() */
    GLJPanel canvas;
    
    /* Guarda la referencia del animador para la animación */
    FPSAnimator animador;

    Circulo circuloFondo = new Circulo(new Punto(50, 100), 25);
    Circulo circuloCursor = new Circulo(new Punto(0, 0), 25);

    /* tamaño de la ventana */
    int alto;
    int ancho;

    public Colision1(GLJPanel canvas, FPSAnimator animador) {
        this.canvas = canvas;
        this.animador = animador;
    }
    
    /**
     * Se ejecuta al inicio.
     */
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glEnable(GL_RGB);
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL_COLOR_BUFFER_BIT);
        /* 100% azul */
        gl.glColor3ub((byte) 0, (byte) 0, (byte) 0xFF); 
        dibujaCirculo(drawable, circuloFondo); /* Dibuja el círculo de fondo */

        /* Si el usuario realizo una selección se dibuja el otro circulo */
        if (this.numPuntos > 0) {
            /* Rojo */
            gl.glColor3ub((byte) 0xFF, (byte) 0, (byte) 0);
            /* Dibuja el circulo al rededor del cursor */
            dibujaCirculo(drawable, circuloCursor); 
        }
        gl.glFlush();
    }

    /**
     * Se ejecuta cuando el usuario redimensiona la ventana.
     */
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
        final GL gl = drawable.getGL();
        final GLU glu = new GLU();

        /* Se requiere que el alto y ancho sean positivos */
        this.alto = (h > 1) ? h : 2;
        this.ancho = (w > 1) ? w : 2;

        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0.0, w, 0.0, h);

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glViewport(0, 0, w, h);
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}

    /* Metodos del MouseListener */
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        int xPos = e.getX();
        /* Modifica y, porque el 'y' del openGL está invertido */
        int yPos = alto - e.getY();
        System.out.printf("\nUbicación (x, y) = (%d, %d)\n", xPos, yPos);

        /* Se indica el centro del circulo del cursor, el radio ya está definido */
        circuloCursor.centro.x = xPos;
        circuloCursor.centro.y = yPos;
        numPuntos = 1;
        
        /* Si algo se cambio: se redespliega */
        canvas.repaint();

        /* Llama a la rutina que determina si los dos circulos se intersectan. */
        if (verificaColision(circuloFondo, circuloCursor))
            System.out.printf("Ay!\n");
        else
            System.out.printf("Falló!\n");
    }

    /* Metodos del KeyListener */
    public void keyPressed(KeyEvent e) {
    	switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
            animador.stop();
            System.exit(0);
        }
	}

	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}

    /**
     * Verifica la Colisión - ¿Se intersectan los circulos?
     * 
     * Implemente este método.
     */
    boolean verificaColision(Circulo c1, Circulo c2) {
        System.out.printf("Se llama a verificaColision - ubique su código aqui\n");
        
        /* Se necesita retornar algo para la compilación */
        return false;
    }
    
    void dibujaCirculo(GLAutoDrawable drawable, Circulo c) {
        GL gl = drawable.getGL();

        /* Dibuja el centro */
        gl.glBegin(GL_POINTS);
        {
            gl.glVertex2i(c.centro.x, c.centro.y);
        }
        gl.glEnd();

        /* Dibuja circulo */
        gl.glBegin(GL_LINE_STRIP);
        {
            for (int i = 0; i <= MAXGRADO; i++) {
                float theta = (float) (i * Math.PI * 2) / MAXGRADO;
                gl.glVertex2i((int) (c.radio * Math.sin(theta)) + c.centro.x,
                        (int) (c.radio * Math.cos(theta)) + c.centro.y);
            }
        }
        gl.glEnd();
    }

    public static void main(String[] args) {

		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Colision1");

		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(500, 500);
			    
		/* Se ubica la ventana */
	    miMarco.setLocation(100, 100);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Se crea el objeto GLCapabilities */
		GLCapabilities miCapacidad = new GLCapabilities();
		
		/* Solicita doble buffer en el modo de despliegue */
		miCapacidad.setDoubleBuffered(false);
				
		/* Se crea el objeto GLJPanel */
		GLJPanel miLienzo = new GLJPanel(miCapacidad);
		
		/* Agrega animación al GLJPanel - 60 cuadros por segundo */
	    FPSAnimator animador = new FPSAnimator(miLienzo, 60);
		
		/* Se crea una instancia de la Clase */
		Colision1 miPanel = new Colision1(miLienzo, animador);

		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miLienzo.addGLEventListener(miPanel);
		
		/* Indicamos que el GLJPanel detecte los eventos del ratón */
		miLienzo.addMouseListener((MouseListener) miPanel);

		/* Indicamos que el GLJPanel detecte los eventos del teclado */
		miLienzo.addKeyListener((KeyListener) miPanel);

	    /* Añadimos el GLJPanel al Componente del JFrame */
	    miMarco.getContentPane().add(miLienzo);
		
	    /* Hacemos visible el elemento de mayor nivel */
	    miMarco.setVisible(true);
	    
	    /*
	       Se solicita que la ventana del GLJPanel esté activa
	       (para que detecte eventos del ratón y teclado)
	    */
	    miLienzo.requestFocusInWindow();
	    
	    /* Inicia la animación */
	    animador.start();
	}
}
