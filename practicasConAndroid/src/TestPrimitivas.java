import static javax.media.opengl.GL.GL_BLEND;
import static javax.media.opengl.GL.GL_DONT_CARE;
import static javax.media.opengl.GL.GL_LINE_SMOOTH;
import static javax.media.opengl.GL.GL_LINE_SMOOTH_HINT;
import static javax.media.opengl.GL.GL_ONE_MINUS_SRC_ALPHA;
import static javax.media.opengl.GL.GL_POINT_SMOOTH;
import static javax.media.opengl.GL.GL_SRC_ALPHA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import com.sun.opengl.util.GLUT;
/**
 * Programa que despliega las primitivas del OpenGL.
 * 
 * @author J Felipez
 * @version 1.0 13/09/2012
 * 
 */
public class TestPrimitivas extends JPanel {

	private static final long serialVersionUID = 1L;

	// GLJPanel donde se desplegará el dibujo
	private GLJPanel miLienzo;

	// Clase utilizada para renderizar el dibujo
	private Renderiza renderiza;

	// Clase utilizada para detectar los botones de control
	Controles controles;

	public TestPrimitivas() {

		miLienzo = new GLJPanel();
		renderiza = new Renderiza();
		controles = new Controles(renderiza, this);

		// Indicamos que el GLJPanel detecte los eventos del openGL
		miLienzo.addGLEventListener(renderiza);

		// No utiliza al administrador del layout
		setLayout(null);

		// Añade el area de visualización (GLJPanel) a la izquierda del Componente
		miLienzo.setBounds(0, 0, 512, 512);
		add(miLienzo);

		// Añade los botones de control (JPanel) a la derecha del Componente
		controles.setBounds(512, 0, 700, 512);
		add(controles);
	}

	static class Renderiza implements GLEventListener {
		public int PRIMITIVA = 0;
		GLUT glut = new GLUT();

		public void init(GLAutoDrawable drawable) {
			GL gl = drawable.getGL(); // Inicia la variable GL
			gl.glClearColor(0.0f, 1.0f, 1.0f, 1.0f); // fondo celeste
		}

		public void display(GLAutoDrawable drawable) {
			GL gl = drawable.getGL(); // Inicia la variable GL

			gl.glClear(GL.GL_COLOR_BUFFER_BIT); // borra el buffer de la ventana
			gl.glOrtho(-1, 1, -1, 1, -1, 1); // Proyección en paralelo

			switch (PRIMITIVA) {
				case 1: dibujaPuntos(gl); break;
				case 2: dibujaLineas(gl); break;
				case 3: dibujaLineasConectadas(gl); break;
				case 4: dibujaLineasCerradas(gl); break;
				case 5: dibujaTriangulo(gl); break;
				case 6: dibujaTriangulosConectados(gl); break;
				case 7: dibujaTrianguloConectadosComoAbanico(gl);	break;
				case 8: dibujaPoligono(gl); break;
				case 9: dibujaCuadrilatero(gl);	break;
				case 10: dibujaCuadrilaterosConectados(gl); break;
			}

			gl.glFlush();
		}

		public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}

		public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
		
		public void dibujaPuntos(GL gl) {
	        gl.glColor3f(0, 0, 0); // negro
			gl.glPointSize(5); // tamaño de punto
			
			/* Mejora la calidad de los puntos */
			gl.glEnable (GL_POINT_SMOOTH);
			
		    gl.glBegin(GL.GL_POINTS);
	    		gl.glVertex2f(-0.82f, 0.47f);
		    	gl.glVertex2f(-0.78f, 0.46f);
		    	gl.glVertex2f(-0.76f, 0.41f);
		    	gl.glVertex2f(-0.83f, 0.43f);
		    gl.glEnd();
		    gl.glRasterPos2f(-0.95f, 0.3f); // posición
			glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "GL_POINTS");
		}
		
		public void dibujaLineas(GL gl) {
			gl.glColor3f(0, 0, 0); // negro
			gl.glLineWidth(2); // ancho de línea
			
			/* Mejora la calidad de las líneas */
			gl.glEnable (GL_LINE_SMOOTH);	// habilita la calidad mas uniforme de la linea
			gl.glEnable(GL_BLEND);	// habilita la combinación de colores 
			gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // define la operación de combinación
			gl.glHint(GL_LINE_SMOOTH_HINT,GL_DONT_CARE); // define la calidad de suavizado
			
		    gl.glBegin(GL.GL_LINES);
				gl.glVertex2f(-0.6f, 0.28f);
	    		gl.glVertex2f(-0.49f, 0.4f);
	    		gl.glVertex2f(-0.48f, 0.36f);
	    		gl.glVertex2f(-0.41f, 0.26f);
	    	gl.glEnd();
	    	gl.glDisable(GL_BLEND);	// deshabilita la combinación de colores
	    	gl.glRasterPos2f(-0.65f, 0.18f); // posición
			glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "GL_LINES");
		}

		public void dibujaLineasConectadas(GL gl) {
			gl.glColor3f(0, 0, 0); // negro
			gl.glLineWidth(2); // ancho de línea
			
			/* Mejora la calidad de las líneas */
			gl.glEnable(GL_BLEND);	// habilita la combinación de colores 
			gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // define la operación de combinación
			gl.glHint(GL_LINE_SMOOTH_HINT,GL_DONT_CARE); // define la calidad de suavizado
			
			gl.glBegin(GL.GL_LINE_STRIP);
				gl.glVertex2f(-0.15f, 0.28f);
				gl.glVertex2f(0.01f, 0.33f);
				gl.glVertex2f(-0.1f, 0.6f);
				gl.glVertex2f(0.08f, 0.5f);
				gl.glVertex2f(-0.25f, 0.45f);
				gl.glVertex2f(-0.21f, 0.56f);
				gl.glVertex2f(-0.11f, 0.4f);
			gl.glEnd();
			gl.glDisable(GL_BLEND);	// deshabilita la combinación de colores
			gl.glRasterPos2f(-0.37f, 0.18f); // posición
			glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "GL_LINE_STRIP");
		}

		public void dibujaLineasCerradas(GL gl) {
			gl.glColor3f(0, 0, 0); // negro
			gl.glLineWidth(2); // ancho de línea
			
			/* Mejora la calidad de las líneas */
			gl.glEnable(GL_BLEND);	// habilita la combinación de colores 
			gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // define la operación de combinación
			gl.glHint(GL_LINE_SMOOTH_HINT,GL_DONT_CARE); // define la calidad de suavizado
			
			gl.glBegin(GL.GL_LINE_LOOP);
				gl.glVertex2f(0.41f, 0.1f);
				gl.glVertex2f(0.5f, 0.28f);
				gl.glVertex2f(0.38f, 0.27f);
				gl.glVertex2f(0.35f, 0.38f);
				gl.glVertex2f(0.48f, 0.4f);
				gl.glVertex2f(0.25f, 0.30f);
			gl.glEnd();
			gl.glDisable(GL_BLEND);	// deshabilita la combinación de colores
			gl.glRasterPos2f(0.2f, 0f); // posición
			glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "GL_LINE_LOOP");
		}

		public void dibujaPoligono(GL gl) {
			gl.glColor3f(1, 0, 1);	// lila
			gl.glBegin(GL.GL_POLYGON);
				gl.glVertex2f(0.86f, 0.62f);
				gl.glVertex2f(0.71f, 0.54f);
				gl.glVertex2f(0.71f, 0.42f);
				gl.glVertex2f(0.8f, 0.35f);
				gl.glVertex2f(0.93f, 0.4f);
				gl.glVertex2f(0.96f, 0.52f);
			gl.glEnd();
		}

		public void dibujaTriangulo(GL gl) {
			gl.glBegin(GL.GL_TRIANGLES);
				gl.glColor3f(1, 0, 0);	gl.glVertex2f(0, 0.9f);
				gl.glColor3f(0, 1, 0);	gl.glVertex2f(0.9f, -0.9f);
				gl.glColor3f(0, 0, 1);	gl.glVertex2f(-0.9f, -0.9f);
			gl.glEnd();
		}

		public void dibujaTriangulosConectados(GL gl) {
			gl.glBegin(GL.GL_TRIANGLE_STRIP);
				gl.glColor3f(1, 0, 0);	gl.glVertex2f(-0.9f, 0.9f);
				gl.glColor3f(0, 1, 0);	gl.glVertex2f(-0.9f, -0.9f);
				gl.glColor3f(0, 0, 1);	gl.glVertex2f(-0.3f, 0.8f);
				gl.glColor3f(1, 1, 0);	gl.glVertex2f(0, -0.6f);
				gl.glColor3f(0, 1, 1);	gl.glVertex2f(0.6f, 0.4f);
			gl.glEnd();
		}

		public void dibujaTrianguloConectadosComoAbanico(GL gl) {
			gl.glBegin(GL.GL_TRIANGLE_FAN);
				gl.glColor3f(1, 0, 0); 	gl.glVertex2f(-0.7f, -0.4f); 
				gl.glColor3f(0, 1, 0);	gl.glVertex2f(-0.9f, 0); 
				gl.glColor3f(0, 0, 1);	gl.glVertex2f(0.7f, 0.9f); 
				gl.glColor3f(1, 1, 0);	gl.glVertex2f(0.8f, 0.7f);
				gl.glColor3f(0, 1, 1);	gl.glVertex2f(0.9f, 0);
				gl.glColor3f(1, 1, 0);	gl.glVertex2f(0.8f, -0.5f);
				gl.glColor3f(0, 1, 1);	gl.glVertex2f(0.5f, -0.9f);
			gl.glEnd();
		}

		public void dibujaCuadrilatero(GL gl) {
			gl.glBegin(GL.GL_QUADS);
				gl.glColor3f(1, 0, 0);	gl.glVertex2f(-0.9f, -0.9f);
				gl.glColor3f(0, 1, 0);	gl.glVertex2f(-0.9f, 0.9f);
				gl.glColor3f(0, 0, 1);	gl.glVertex2f(0.9f, 0.9f);
				gl.glColor3f(1, 1, 0);	gl.glVertex2f(0.9f, -0.9f);
			gl.glEnd();
		}

		public void dibujaCuadrilaterosConectados(GL gl) {
			gl.glBegin(GL.GL_QUAD_STRIP);
				gl.glColor3f(1, 0, 0);	gl.glVertex2f(-0.9f, -0.9f);
				gl.glColor3f(0, 1, 0);	gl.glVertex2f(-0.9f, 0);
				gl.glColor3f(0, 0, 1);	gl.glVertex2f(-0.5f, -0.4f);
				gl.glColor3f(1, 1, 1);	gl.glVertex2f(-0.5f, 0.3f);
				gl.glColor3f(1, 1, 0);	gl.glVertex2f(0, -0.6f);
				gl.glColor3f(0, 1, 1);	gl.glVertex2f(0.3f, 0.6f);
				gl.glColor3f(1, 1, 0);	gl.glVertex2f(0.9f, -0.5f);
				gl.glColor3f(0, 1, 1);	gl.glVertex2f(0.9f, 0.8f);
			gl.glEnd();
		}
	}

	static class Controles extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		Renderiza renderiza;
		TestPrimitivas elPanel;
		public JButton miBoton1, miBoton2, miBoton3, miBoton4, miBoton5;
		public JButton miBoton6, miBoton7, miBoton8, miBoton9, miBoton10;

		public Controles(Renderiza renderiza, TestPrimitivas elPanel) {
			this.renderiza = renderiza;
			this.elPanel = elPanel;
			
			// Color de fondo
			setBackground(Color.black);
			
			// No utiliza al administrador del layout
			setLayout( null );

			// Crea botones
			miBoton1 = new JButton("Puntos");
			miBoton2 = new JButton("Lineas");
			miBoton3 = new JButton("Lineas Strip");
			miBoton4 = new JButton("Lineas Loop");
			miBoton5 = new JButton("Triángulo");
			miBoton6 = new JButton("Triángulo Strip");
			miBoton7 = new JButton("Triángulo Fan");
			miBoton8 = new JButton("Polígono");
			miBoton9 = new JButton("Cuadrilátero");
			miBoton10 = new JButton("Cuadrilátero Strip");

			// Se ubica los botones
			miBoton1.setBounds(20, 20, 140, 30);
			miBoton2.setBounds(20, 60, 140, 30);
			miBoton3.setBounds(20, 100, 140, 30);
			miBoton4.setBounds(20, 140, 140, 30);
			miBoton5.setBounds(20, 180, 140, 30);
			miBoton6.setBounds(20, 220, 140, 30);
			miBoton7.setBounds(20, 260, 140, 30);
			miBoton8.setBounds(20, 300, 140, 30);
			miBoton9.setBounds(20, 340, 140, 30);
			miBoton10.setBounds(20, 380, 140, 30);

			// Se agrega los botones al JPanel
			add(miBoton1); add(miBoton2); add(miBoton3); add(miBoton4); add(miBoton5);
			add(miBoton6); add(miBoton7); add(miBoton8); add(miBoton9); add(miBoton10);

			// Para que detecte si se pulsa un botón
			miBoton1.addActionListener(this);
			miBoton2.addActionListener(this);
			miBoton3.addActionListener(this);
			miBoton4.addActionListener(this);
			miBoton5.addActionListener(this);
			miBoton6.addActionListener(this);
			miBoton7.addActionListener(this);
			miBoton8.addActionListener(this);
			miBoton9.addActionListener(this);
			miBoton10.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == miBoton1)	renderiza.PRIMITIVA = 1;
			if (e.getSource() == miBoton2)	renderiza.PRIMITIVA = 2;
			if (e.getSource() == miBoton3)	renderiza.PRIMITIVA = 3;
			if (e.getSource() == miBoton4)	renderiza.PRIMITIVA = 4;
			if (e.getSource() == miBoton5)	renderiza.PRIMITIVA = 5;
			if (e.getSource() == miBoton6)	renderiza.PRIMITIVA = 6;
			if (e.getSource() == miBoton7)	renderiza.PRIMITIVA = 7;
			if (e.getSource() == miBoton8)	renderiza.PRIMITIVA = 8;
			if (e.getSource() == miBoton9)	renderiza.PRIMITIVA = 9;
			if (e.getSource() == miBoton10)	renderiza.PRIMITIVA = 10;
			elPanel.redespliega();
		}
	}

	public static void main(String[] args) {

		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Primitivas");

		/* Se le otorga un tamaño de la ventana */
		miMarco.setSize(new Dimension(700, 512));
		
		// Ubicar la ventana al centro de la pantalla
		miMarco.setLocationRelativeTo(null);
		
		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Se crea una instancia de la Clase */
		TestPrimitivas miPanel = new TestPrimitivas();

		/* Añadimos GLJPanel al Componente del JFrame */
		miMarco.getContentPane().add(miPanel);

		/* Logramos que la ventana obtenga el tamaño más pequeño posible para ver todos los componentes */
		//miMarco.pack();

		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}

	public void redespliega() {
		miLienzo.display();
	}
}