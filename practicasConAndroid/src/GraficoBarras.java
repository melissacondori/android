
import javax.swing.JFrame;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
import javax.media.opengl.glu.GLU;
import static javax.media.opengl.GL.*;
import com.sun.opengl.util.GLUT;
import static com.sun.opengl.util.GLUT.*;

/**
 * Programa que grafica barras en OpenGL.
 * 
 * @author J Felipez
 * @version 1.0 27/08/2011
 */
public class GraficoBarras implements GLEventListener{
    GLU  glu  = new GLU(); // Inicializa la variable GLU
    GLUT  glut  = new GLUT(); // Inicializa la variable GLUT
    
	static int ancho = 630, alto = 500;
	String etiqueta[] = {"Ene", "Feb", "Mar",
						"Abr", "May", "Jun",
						"Jul", "Ago", "Sep",
						"Oct", "Nov", "Dic"};
	
	int[] valores = {420, 342, 324, 310, 262, 185, 190, 196, 217, 240, 312, 438};
	
	public void init(GLAutoDrawable drawable) {
	    GL  gl  = drawable.getGL(); // Inicializa la variable GL
	    gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // fondo blanco
		gl.glMatrixMode(GL_PROJECTION);	// Especifica el tipo de proyección
	    gl.glLoadIdentity();	// Resetea la matriz identidad
	    glu.gluOrtho2D(0.0, ancho, 0.0, alto);	// Proyección paralela
	}
	
	public void display(GLAutoDrawable drawable) {
		GL gl = drawable.getGL(); // Inicia la variable GL
	    
		int mes;
		
		gl.glClear(GL_COLOR_BUFFER_BIT);	// borra el buffer de la ventana
		
		/* Dibuja las lineas */
		gl.glColor3f(1.0f, 0.0f, 0.0f);	// rojo
		for (mes = 0; mes < 12; mes++)
			gl.glRecti(mes * 50 + 20, 165, mes * 50 + 40, valores[mes]); // rectángulo
		
		/* Agrega texto al eje X */
		gl.glColor3f(0.0f, 0.0f, 0.0f);	// negro
		for (mes = 0; mes < 12; mes++) {
			gl.glRasterPos2i(mes * 50 + 20, 150); // posición
			glut.glutBitmapString(BITMAP_HELVETICA_12, etiqueta[mes]); // muestra cadena
		}

	    gl.glFlush();
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {}
	
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}

	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Gráfico de Barras");
		
		/* Se le otorga un tamaño de la ventana */
		miMarco.setSize(ancho, alto);
		
		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Se crea el objeto GLJPanel */
		GLJPanel miLienzo = new GLJPanel();

		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miLienzo.addGLEventListener(new GraficoBarras());
		
		/* A continuacion insertamos el GLJPanel en el JFrame */
		miMarco.add(miLienzo);
		
		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
