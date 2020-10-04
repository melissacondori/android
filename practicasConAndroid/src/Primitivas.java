import javax.swing.JFrame;
import static javax.media.opengl.GL.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
import javax.media.opengl.glu.GLU;

import static com.sun.opengl.util.GLUT.*;
import com.sun.opengl.util.GLUT;
/**
 * Programa que despliega a las figuras primitivas en OpenGL.
 * 
 * @author J Felipez
 * @version 2.0 13/08/2011
 */
public class Primitivas implements GLEventListener{

	public void init(GLAutoDrawable drawable) {}
	
	public void display(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();
		
		/* Inicia la variable GLU */
		GLU glu = new GLU();
		
		/* Inicia la variable GLUT */
		GLUT glut = new GLUT(); 
		
		/* Fondo blanco */
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		/* Borra el buffer de la ventana */
		gl.glClear(GL_COLOR_BUFFER_BIT);
		
		/* Proyección en paralelo 2D */
	    glu.gluOrtho2D(-1, 1, -1, 1);
	    
	    /* Color negro */
	    gl.glColor3f(0, 0, 0);
	    
	    /* Tamaño del punto */
	    gl.glPointSize(5);
	    
	    gl.glBegin(GL_POINTS);
    		gl.glVertex2f(-0.82f, 0.47f);
	    	gl.glVertex2f(-0.78f, 0.46f);
	    	gl.glVertex2f(-0.76f, 0.41f);
	    	gl.glVertex2f(-0.83f, 0.43f);
	    gl.glEnd();
	    
	    gl.glRasterPos2f(-0.95f, 0.3f); // posición
	    glut.glutBitmapString(BITMAP_HELVETICA_12, "GL_POINTS");

	    /* Ancho de la línea */
	    gl.glLineWidth(2);
	    gl.glBegin(GL_LINES);
			gl.glVertex2f(-0.6f, 0.28f);
    		gl.glVertex2f(-0.49f, 0.4f);
    		gl.glVertex2f(-0.48f, 0.36f);
    		gl.glVertex2f(-0.41f, 0.26f);
    	gl.glEnd();
    	gl.glRasterPos2f(-0.65f, 0.18f); // posición
		glut.glutBitmapString(BITMAP_HELVETICA_12, "GL_LINES");
		
		/* Ancho de la línea */
		gl.glLineWidth(2);
		gl.glBegin(GL.GL_LINE_STRIP);
			gl.glVertex2f(-0.15f, 0.28f);
			gl.glVertex2f(0.01f, 0.33f);
			gl.glVertex2f(-0.1f, 0.6f);
			gl.glVertex2f(0.08f, 0.5f);
			gl.glVertex2f(-0.25f, 0.45f);
			gl.glVertex2f(-0.21f, 0.56f);
			gl.glVertex2f(-0.11f, 0.4f);
		gl.glEnd();
		gl.glRasterPos2f(-0.37f, 0.18f); // posición
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, "GL_LINE_STRIP");
		
		/* Ancho de la línea */
		gl.glLineWidth(2);
		gl.glBegin(GL.GL_LINE_LOOP);
			gl.glVertex2f(0.41f, 0.1f);
			gl.glVertex2f(0.5f, 0.28f);
			gl.glVertex2f(0.38f, 0.27f);
			gl.glVertex2f(0.35f, 0.38f);
			gl.glVertex2f(0.48f, 0.4f);
			gl.glVertex2f(0.25f, 0.30f);
		gl.glEnd();
		gl.glRasterPos2f(0.2f, 0f); // posición
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, "GL_LINE_LOOP");
		
		gl.glColor3f(1, 0, 1);	// lila
		gl.glBegin(GL.GL_POLYGON);
			gl.glVertex2f(0.86f, 0.62f);
			gl.glVertex2f(0.71f, 0.54f);
			gl.glVertex2f(0.71f, 0.42f);
			gl.glVertex2f(0.8f, 0.35f);
			gl.glVertex2f(0.93f, 0.4f);
			gl.glVertex2f(0.96f, 0.52f);
		gl.glEnd();
		gl.glColor3f(0, 0, 0);	// negro
		gl.glRasterPos2f(0.6f, 0.28f); // posición
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, "GL_POLYGON");

	    gl.glFlush();	
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}

	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Primitivas");
		
		/* Se le otorga un tamaño de la ventana */
		miMarco.setSize(512, 512);
		
		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Se crea el objeto GLJPanel */
		GLJPanel miLienzo = new GLJPanel();

		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miLienzo.addGLEventListener(new Primitivas());
		
		/* A continuacion insertamos GLJPanel en el JFrame */
		miMarco.add(miLienzo);
		
		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
