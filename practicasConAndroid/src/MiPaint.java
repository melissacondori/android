//package s02_interaccion;

/**
 * MiPaint.java		1.0 13/07/2013
 */
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.media.opengl.*;

import static javax.media.opengl.GL.*;

import com.sun.opengl.util.GLUT;

/**
 * MiPaint.java
 * 
 * Simple programa donde se ingresa texto, lineas, triángulos, rectangulos y puntos.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/07/2013
 */
public class MiPaint implements GLEventListener, KeyListener, MouseListener, ActionListener {
	
	/* GLJPanel donde se desplegará el dibujo */
	private static GLJPanel miLienzo;
	
	enum tipoDeDibujo {
		NINGUNO, LINEA, RECTANGULO, TRIANGULO, PUNTOS, TEXTO
	};

	/* tamaño inicial de la ventana */
	private int alto = 500, ancho = 500;
	
	/* color de dibujo */
	private static float r = 1.0f, g = 1.0f, b = 1.0f;
	
	/* la mitad del lado del cuadrado */
	private static float tamanio = 3.0f;
	
	/* bandera de llenado */
	static boolean rellenar = false; 

	int[] xp = new int[2];
	int[] yp = new int[2];
	int x, y;
	
	/* Inicializa la variable GLUT */
	private GLUT glut = new GLUT(); 
	private Random random = new Random();
	private boolean dibuja = false;
	int cant = 0;
	
	/* Posición del renderizado */
	int rx, ry;
	
	/* Tipo de dibujo */
	private tipoDeDibujo tipo_de_dibujo = tipoDeDibujo.NINGUNO; 
	private char tecla;

	/* Variables JPopupMenu */
	private JPopupMenu botonMedioPopup;
	private JPopupMenu botonDerechoPopup;
	
	/* Opciones del menu */
	private JMenuItem rojoJMenuItem, verdeJMenuItem, azulJMenuItem;
	private JMenuItem cianJMenuItem, magentaJMenuItem, amarilloJMenuItem;
	private JMenuItem blancoJMenuItem, negroJMenuItem;	
	private JMenuItem incrementaJMenuItem, decrementaJMenuItem;
	private JMenuItem siJMenuItem, noJMenuItem;
	private JMenuItem salirJMenuItem, limpiarJMenuItem;

	public void init(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Ajusta y limpia el puerta de visión, define el tamaño */
		gl.glViewport(0, 0, ancho, alto);

		/*
		 * Elije la ventana 2D igual al tamaño de la ventana. Esta elección
		 * evita escalar los objetos cada vez que la ventana es redimensionada.
		 */
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0.0, (double) ancho, 0.0, (double) alto, -1.0, 1.0);

		/* Coloca el color a negro y limpia la ventana */
		gl.glClearColor(0.8f, 0.8f, 0.8f, 1.0f); // Color de conFondo (rojo,verde,azul,alfa)
		
		/* alfa [0..1]: 1 totalmente opaco, 0 totalmente trasparente. */
		gl.glClear(GL_COLOR_BUFFER_BIT);
		gl.glFlush();

	}

	public void display(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		gl.glPushAttrib(GL_ALL_ATTRIB_BITS);
		if (dibuja) {
			dibujaFigura(gl, tipo_de_dibujo);
		} else {
			dibujaPaleta(gl);
		}
		gl.glPopAttrib();
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Matriz de Proyección */
		gl.glMatrixMode(GL_PROJECTION);
		
		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();
		
		/* Proyección Paralela */
		gl.glOrtho(0.0, (double) w, 0.0, (double) h, -1.0, 1.0);
		
		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL_MODELVIEW);
		
		/* Inicializa MVM */
		gl.glLoadIdentity(); 

		/* Se define la ventana de despliegue */
		gl.glViewport(0, 0, w, h);

		/* tamaño de la ventana */ 
		ancho = w;
		alto = h;
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}

	/* Métodos del MouseListener */
	/* Evento de oprimir un boton del ratón */
	public void mousePressed(MouseEvent e) {
		verificaEventoPopup(e);
		x = e.getX();
		y = e.getY();
		if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
			tipoDeDibujo donde = elige(x, y);
			if (donde != tipoDeDibujo.NINGUNO) {
				cant = 0;
				tipo_de_dibujo = donde;
			} else {
				GLJPanel canvas = (GLJPanel) e.getComponent();
				switch (tipo_de_dibujo) {
				case LINEA:
					if (cant == 0) {
						cant++;
						xp[0] = x;
						yp[0] = y;
					} else {
						dibuja = true;
						/* Si algo se cambio: se redespliega */
						canvas.display();
						tipo_de_dibujo = tipoDeDibujo.NINGUNO;
						cant = 0;
					}
					break;
				case RECTANGULO:
					if (cant == 0) {
						cant++;
						xp[0] = x;
						yp[0] = y;
					} else {
						dibuja = true;
						/* Si algo se cambio: se redespliega */
						canvas.display();
						tipo_de_dibujo = tipoDeDibujo.NINGUNO;
						cant = 0;
					}
					break;
				case TRIANGULO:
					if (cant < 2) {
						xp[cant] = x;
						yp[cant] = y;
						cant++;
					} else {
						dibuja = true;
						/* Si algo se cambio: se redespliega */
						canvas.display();
						tipo_de_dibujo = tipoDeDibujo.NINGUNO;
						cant = 0;
					}
					break;
				case PUNTOS:
					dibuja = true;
					/* Si algo se cambio: se redespliega */
					canvas.display();
					cant++;
					break;
				case TEXTO:
					rx = x;
					ry = alto - y;
					dibuja = true;
					/* Si algo se cambio: se redespliega */
					canvas.display();
					cant = 0;
					break;
				default:
					break;
				}
			}
		}

	}
	
	/* Evento de soltar el botón del ratón */
	public void mouseReleased(MouseEvent e) {
		verificaEventoPopup(e);
	}

	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	/* Métodos del KeyListener */
	public void keyPressed(KeyEvent e) {
		if (tipo_de_dibujo != tipoDeDibujo.TEXTO)
			return;

		GLJPanel canvas = (GLJPanel) e.getComponent();
		tecla = e.getKeyChar();
		dibuja = true;
		/* Si algo se cambio: se redespliega */
		canvas.display();
		tecla = 0;
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}
	
	// determina si el evento debe desencadenar el menú contextual
	// trigger - disparador (procedimiento)
	private void verificaEventoPopup(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON2)
			botonMedioPopup.show(e.getComponent(), e.getX(), e.getY());
		if (e.getButton() == MouseEvent.BUTTON3)
			botonDerechoPopup.show(e.getComponent(), e.getX(), e.getY());
	}

	private void dibujaFigura(GL gl, tipoDeDibujo tipo) {
		gl.glColor3f(r, g, b);

		switch (tipo) {
		case LINEA:
			gl.glBegin(GL_LINES);
			gl.glVertex2i(x, alto - y);
			gl.glVertex2i(xp[0], alto - yp[0]);
			gl.glEnd();
			break;
		case RECTANGULO:
			if (rellenar)
				gl.glBegin(GL_POLYGON);
			else
				gl.glBegin(GL_LINE_LOOP);
			gl.glVertex2i(x, alto - y);
			gl.glVertex2i(x, alto - yp[0]);
			gl.glVertex2i(xp[0], alto - yp[0]);
			gl.glVertex2i(xp[0], alto - y);
			gl.glEnd();
			break;
		case TRIANGULO:
			if (rellenar)
				gl.glBegin(GL_POLYGON);
			else
				gl.glBegin(GL_LINE_LOOP);
			gl.glVertex2i(xp[0], alto - yp[0]);
			gl.glVertex2i(xp[1], alto - yp[1]);
			gl.glVertex2i(x, alto - y);
			gl.glEnd();
			break;
		case PUNTOS:
			dibujaCuadrado(gl, x, y);
			break;
		case TEXTO:
			if (tecla == 0)
				break;
			gl.glColor3f(0.0f, 0.0f, 0.0f);
			gl.glRasterPos2i(rx, ry);
			glut.glutBitmapCharacter(GLUT.BITMAP_9_BY_15, tecla);
			// glut.glutStrokeCharacter(gl, GLUT.STROKE_ROMAN, i);
			rx += glut.glutBitmapWidth(GLUT.BITMAP_9_BY_15, tecla);

			break;
		default:
			break;
		}
		dibuja = false;
	}

	private void dibujaPaleta(GL gl) {
		gl.glClear(GL_COLOR_BUFFER_BIT);
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		dibuja_caja(gl, 0, alto - ancho / 10, ancho / 10);
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		dibuja_caja(gl, ancho / 10, alto - ancho / 10, ancho / 10);
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		dibuja_caja(gl, ancho / 5, alto - ancho / 10, ancho / 10);
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		dibuja_caja(gl, 3 * ancho / 10, alto - ancho / 10, ancho / 10);
		gl.glColor3f(1.0f, 1.0f, 0.0f);
		dibuja_caja(gl, 2 * ancho / 5, alto - ancho / 10, ancho / 10);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glBegin(GL_LINES);
			gl.glVertex2i(alto / 40, alto - ancho / 20);
			gl.glVertex2i(alto / 40 + ancho / 20, alto - ancho / 20);
		gl.glEnd();

		gl.glBegin(GL_TRIANGLES);
			gl.glVertex2i(ancho / 5 + ancho / 40, alto - ancho / 10 + ancho / 40);
			gl.glVertex2i(ancho / 5 + ancho / 20, alto - ancho / 40);
			gl.glVertex2i(ancho / 5 + 3 * ancho / 40, alto - ancho / 10 + ancho / 40);
			gl.glEnd();
			gl.glPointSize(3.0f);
			gl.glBegin(GL_POINTS);
			gl.glVertex2i(3 * ancho / 10 + ancho / 20, alto - ancho / 20);
		gl.glEnd();
		gl.glRasterPos2i(2 * ancho / 5, alto - ancho / 20);
		glut.glutBitmapCharacter(GLUT.BITMAP_9_BY_15, 'A');
		int shift = glut.glutBitmapWidth(GLUT.BITMAP_9_BY_15, 'A');
		gl.glRasterPos2i(2 * ancho / 5 + shift, alto - ancho / 20);
		glut.glutBitmapCharacter(GLUT.BITMAP_9_BY_15, 'B');
		shift += glut.glutBitmapWidth(GLUT.BITMAP_9_BY_15, 'B');
		gl.glRasterPos2i(2 * ancho / 5 + shift, alto - ancho / 20);
		glut.glutBitmapCharacter(GLUT.BITMAP_9_BY_15, 'C');
		gl.glFlush();
	}

	void dibujaCuadrado(GL gl, int x, int y) {
		y = alto - y;
		gl.glColor3f(random.nextFloat(), random.nextFloat(), random.nextFloat());
		gl.glBegin(GL_POLYGON);
			gl.glVertex2f(x + tamanio, y + tamanio);
			gl.glVertex2f(x - tamanio, y + tamanio);
			gl.glVertex2f(x - tamanio, y - tamanio);
			gl.glVertex2f(x + tamanio, y - tamanio);
		gl.glEnd();
	}

	public tipoDeDibujo elige(int x, int y) {
		y = alto - y;
		if (y < alto - ancho / 10)
			return tipoDeDibujo.NINGUNO;
		else if (x < ancho / 10)
			return tipoDeDibujo.LINEA;
		else if (x < ancho / 5)
			return tipoDeDibujo.RECTANGULO;
		else if (x < 3 * ancho / 10)
			return tipoDeDibujo.TRIANGULO;
		else if (x < 2 * ancho / 5)
			return tipoDeDibujo.PUNTOS;
		else if (x < ancho / 2)
			return tipoDeDibujo.TEXTO;
		else
			return tipoDeDibujo.NINGUNO;
	}

	public void dibuja_caja(GL gl, int x, int y, int s) {
		gl.glBegin(GL_QUADS);
			gl.glVertex2i(x, y);
			gl.glVertex2i(x + s, y);
			gl.glVertex2i(x + s, y + s);
			gl.glVertex2i(x, y + s);
		gl.glEnd();
	}

	private void creaMenusPopup(final Component lienzo) {
		
		// Crea Pop Up Menú
		JMenu colorJMenu = new JMenu("Colores");
		
		rojoJMenuItem = new JMenuItem("Rojo");
		verdeJMenuItem = new JMenuItem("Verde");
		azulJMenuItem = new JMenuItem("Azul");
		cianJMenuItem = new JMenuItem("Cian");
		magentaJMenuItem = new JMenuItem("Magenta");
		amarilloJMenuItem = new JMenuItem("Amarillo");
		blancoJMenuItem = new JMenuItem("Blanco");
		negroJMenuItem = new JMenuItem("Negro");

		// Agrega opciones al JMenu.
		colorJMenu.add(rojoJMenuItem);
		colorJMenu.add(verdeJMenuItem);
		colorJMenu.add(azulJMenuItem);
		colorJMenu.add(cianJMenuItem);
		colorJMenu.add(magentaJMenuItem);
		colorJMenu.add(amarilloJMenuItem);
		colorJMenu.add(blancoJMenuItem);
		colorJMenu.add(negroJMenuItem);
		
		// Para que detecte si se pulsa un botón
		rojoJMenuItem.addActionListener(this);
		verdeJMenuItem.addActionListener(this);
		azulJMenuItem.addActionListener(this);
		cianJMenuItem.addActionListener(this);
		magentaJMenuItem.addActionListener(this);
		amarilloJMenuItem.addActionListener(this);
		blancoJMenuItem.addActionListener(this);
		negroJMenuItem.addActionListener(this);
		
		// Crea Pop Up Menú
		JMenu pixelJMenu = new JMenu("Tamaño del Pixel");

		incrementaJMenuItem = new JMenuItem("Incrementa");
		decrementaJMenuItem = new JMenuItem("Decrementa");

		// Agrega opciones al JMenu.
		pixelJMenu.add(incrementaJMenuItem);
		pixelJMenu.add(decrementaJMenuItem);
		
		// Para que detecte si se pulsa un botón
		incrementaJMenuItem.addActionListener(this);
		decrementaJMenuItem.addActionListener(this);
		
		// Crea Pop Up Menú
		JMenu rellenarJMenu = new JMenu("Rellenar");

		siJMenuItem = new JMenuItem("Si");
		noJMenuItem = new JMenuItem("No");

		// Agrega opciones al JMenu.
		rellenarJMenu.add(siJMenuItem);
		rellenarJMenu.add(noJMenuItem);
		
		// Para que detecte si se pulsa un botón
		siJMenuItem.addActionListener(this);
		noJMenuItem.addActionListener(this);
		
		botonMedioPopup = new JPopupMenu();
		botonMedioPopup.add(colorJMenu);
		botonMedioPopup.add(pixelJMenu);
		botonMedioPopup.add(rellenarJMenu);

		salirJMenuItem = new JMenuItem("Salir");
		salirJMenuItem.setMnemonic('S');
		limpiarJMenuItem = new JMenuItem("Limpiar");
		limpiarJMenuItem.setMnemonic('L');

		// Agrega opciones al JPopupMenu.
		botonDerechoPopup = new JPopupMenu();
		botonDerechoPopup.add(salirJMenuItem);
		botonDerechoPopup.add(limpiarJMenuItem);
		
		// Para que detecte si se pulsa un botón
		salirJMenuItem.addActionListener(this);
		limpiarJMenuItem.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rojoJMenuItem) {
			r = 1.0f; g = 0.0f; b = 0.0f;
		} else if (e.getSource() == verdeJMenuItem) {
			r = 0.0f; g = 1.0f; b = 0.0f;
		} else if (e.getSource() == azulJMenuItem) {
			r = 0.0f; g = 0.0f; b = 1.0f;
		} else if (e.getSource() == amarilloJMenuItem) {
			r = 1.0f; g = 1.0f; b = 0.0f;
		} else if (e.getSource() == cianJMenuItem) {
			r = 0.0f; g = 1.0f; b = 1.0f;
		} else if (e.getSource() == magentaJMenuItem) {
			r = 1.0f; g = 0.0f; b = 1.0f;
		} else if (e.getSource() == blancoJMenuItem) {
			r = 1.0f; g = 1.0f; b = 1.0f;
		} else if (e.getSource() == negroJMenuItem) {
			r = 0.0f; g = 0.0f; b = 0.0f;
		} else if (e.getSource() == incrementaJMenuItem) {
			tamanio *= 2;
		} else if (e.getSource() == decrementaJMenuItem) {
			tamanio /= 2;
		} else if (e.getSource() == siJMenuItem) {
			rellenar = true;
		} else if (e.getSource() == noJMenuItem) {
			rellenar = false;
		} else if (e.getSource() == salirJMenuItem) {
			System.exit(0); // termina la aplicación
		} else if (e.getSource() == limpiarJMenuItem) {
			miLienzo.repaint(); // redibuja
		}
	}

	public static void main(String[] args) {

		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Paint");
		
		/* Se le otorga un tamaño a la ventana */
		miMarco.setSize(512, 512);
		
		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		/* Se crea el objeto GLCapabilities */
		GLCapabilities miCapacidad = new GLCapabilities();

		/* Solicita doble buffer en el modo de despliegue */
		miCapacidad.setDoubleBuffered(true);

		/* Se crea el objeto GLJPanel */
		miLienzo = new GLJPanel(miCapacidad);

		/* Se crea una instancia de la Clase */
		MiPaint miPanel = new MiPaint();

		/* Se adiciona los eventos del OpenGL al GLJPanel */
		miLienzo.addGLEventListener(miPanel);

		/* Indicamos que el GLJPanel detecte los eventos del ratón */
		miLienzo.addMouseListener((MouseListener) miPanel);

		/* Indicamos que el GLJPanel detecte los eventos del teclado */
		miLienzo.addKeyListener((KeyListener) miPanel);
		
		/* Coloca miLienzo activo para que detecte los eventos del teclado */
		miLienzo.setFocusable(true);

		/* Crea menu Popup */
		miPanel.creaMenusPopup(miLienzo);

		/* Añadimos el GLJPanel al Componente del JFrame */ 
		miMarco.getContentPane().add(miLienzo);

		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);

	}
}
