package Sseis;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

/**
 * Este programa muestra el Push y Pop de la pila de matrices. 
 * Dibuja un anillo de los anillos de triángulos.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/09/2011
 */
public class Anilllos implements GLEventListener, KeyListener {
	/* Grados */
	final int INCREMENTO = 30;

	public void init(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Fondo negro */
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}

	public void display(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();
		int angulo;

		/* Limpia el buffer de la ventana */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		for (angulo = 0; angulo < 360; angulo = angulo + INCREMENTO) {

			/* Push MVM */
			gl.glPushMatrix();

			/* Rota */
			gl.glRotated(angulo, 0, 0, 1);

			/* Traslada */
			gl.glTranslatef(0.0f, 0.75f, 0.0f);

			/* Escala */
			gl.glScalef(0.15f, 0.15f, 0.15f);

			dibujaAnillo(gl);

			/* Pop MVM */
			gl.glPopMatrix();
		}
		
		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Inicializa la variable GLU */
		GLU glu = new GLU();

		/* Se define la ventana de despliegue */
		gl.glViewport(0, 0, w, h);

		/* Matriz de Proyección */
		gl.glMatrixMode(GL.GL_PROJECTION);

		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();

		/* Proyección Paralela */
		glu.gluOrtho2D(-1.0, 1.0, -1.0, 1.0);

		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL.GL_MODELVIEW);

		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}

	/* Metodos del KeyListener */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'q':
		case 27: // tecla Escape
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {}

	/* Dibuja un triangulo a la derecha del origen */
	void dibujaTriangulo(GL gl) {

		/* Inicia secuencia del poligono */
		gl.glBegin(GL.GL_POLYGON);

			/* lista de vertices (x,y) */
			gl.glVertex2d(-1, 1);
			gl.glVertex2d(-1, -1);
			gl.glVertex2d(1, -1);

		/* Finaliza la secuencia */
		gl.glEnd();
	}

	/* Dibuja una anillo de triangulos */
	void dibujaAnillo(GL gl) {
		int angulo = 0;
		for (angulo = 0; angulo < 360; angulo = angulo + INCREMENTO) {

			/* Push MVM */
			gl.glPushMatrix();

			/* Rota */
			gl.glRotated(angulo, 0, 0, 1);

			/* Traslada */
			gl.glTranslatef(0.0f, 0.75f, 0.0f);

			/* Escala */
			gl.glScalef(0.2f, 0.2f, 0.2f);

			/* No es rojo o verde: 100% azul */
			gl.glColor3f((float) angulo / 360f, 0f,
					1.0f - ((float) angulo / 360f));

			dibujaTriangulo(gl);

			/* Pop MVM */
			gl.glPopMatrix();
		}
		
		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();
	}

	public static void main(String[] args) {

		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Anillos");

		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(500, 500);

		/* Se ubica la ventana */
		miMarco.setLocation(100, 100);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea el objeto GLJPanel */
		GLJPanel miLienzo = new GLJPanel();

		/* Se crea una instancia de la Clase */
		Anilllos miPanel = new Anilllos();

		/* Se adiciona los eventos del OpenGL al GLJPanel */
		miLienzo.addGLEventListener(miPanel);

		/* Indicamos que el GLJPanel detecte los eventos del ratón */
		miLienzo.addKeyListener((KeyListener) miPanel);

		/* Añadimos el GLJPanel al Componente del JFrame */
		miMarco.getContentPane().add(miLienzo);

		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);

		/*
		 * Se solicita que la ventana del GLJPanel esté activa (para que detecte
		 * eventos del ratón y teclado)
		 */
		miLienzo.requestFocusInWindow();
	}
}
