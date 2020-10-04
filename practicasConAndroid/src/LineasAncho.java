import javax.swing.JFrame;
import javax.media.opengl.*;

import static javax.media.opengl.GL.*;

/**
 * Programa que despliega los anchos de líneas en OpenGL.
 * 
 * @author Jhonny Felipez
 * @version 1.0 13/08/2012
 *
 */
public class LineasAncho implements GLEventListener {

	public void init(GLAutoDrawable arg0) {}
	
	public void display(GLAutoDrawable drawable) {
		
		/* Inicializa la variable GL*/
	    GL gl = drawable.getGL();

	    /* Proyección en paralelo */
	    gl.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);
	    
	    /* Fondo negro */
		gl.glClearColor(0, 0, 0, 0);
		
		/* Borra el buffer de la ventana */
		gl.glClear(GL_COLOR_BUFFER_BIT);

		float valores[] = new float[2];

		/* Obtiene el incremento de los anchos */
		gl.glGetFloatv(GL_LINE_WIDTH_GRANULARITY, valores, 0);
		System.out.println("GL_LINE_WIDTH_GRANULARITY valor es " + valores[0]);
		
		/* Obtiene el rango de anchos */
		gl.glGetFloatv(GL_LINE_WIDTH_RANGE, valores, 0);
		System.out.println("GL_LINE_WIDTH_RANGE valores son " + valores[0] + ", "
                + valores[1]);
		
		/* Mejora la calidad de las líneas */
		gl.glEnable (GL_LINE_SMOOTH);								// habilita la calidad mas uniforme de la linea
		gl.glEnable(GL_BLEND);										// habilita la combinación de colores 
		gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 		// define la operacion de combinación
		gl.glHint(GL_LINE_SMOOTH_HINT,GL_DONT_CARE); 				// define la calidad de suavizado
		
		/* Color verde */
		gl.glColor3ub((byte)0, (byte)255, (byte)0);
		
		/* desde abajo hacia arriba */
		float j = -0.6f;
		float ancho = 0.5f;
		for (int i = 1; i < 11; i++, j += 0.13f, ancho += 0.125f) {
			
			/* Ancho de la linea */
			gl.glLineWidth(ancho);
			
			dibujaLinea(gl, -0.6f, j, 0.6f, j);
		}
		
		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();
	}

	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {}
	
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}
	
	public void dibujaLinea(GL gl, float x1, float y1, float x2, float y2) {
		
		/* Inicia secuencia: linea */
		gl.glBegin(GL_LINES);
			
			/* lista de vertices (x,y) */
			gl.glVertex2f(x1, y1);
			gl.glVertex2f(x2, y2);
		
		/* Finaliza secuencia */
		gl.glEnd();
	}

	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Anchos de Lineas");
		
		/* Se le otorga un tamaño de la ventana */
		miMarco.setSize(300, 300);

		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Se crea el objeto GLJPanel */
		GLJPanel miLienzo = new GLJPanel();
		
		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miLienzo.addGLEventListener(new LineasAncho());
		
		/* A continuacion insertamos el GLJPanel en el JFrame */
		miMarco.add(miLienzo);
		
		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
