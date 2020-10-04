/*
 * @(#)Triangulo.java	1.0	13/08/2011
 */ 
import javax.swing.JFrame;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
/**
 * Programa que despliega un Triángulo en OpenGL.
 * 
 * @author J Felipez
 * @version 1.0 13/08/2011
 */
public class Triangulo implements GLEventListener{

	public void init(GLAutoDrawable drawable) {}
	
	public void display(GLAutoDrawable drawable) {
		
		/* inicializa la variable GL*/
	    GL gl = drawable.getGL();
	    
	    /* fondo negro */
		gl.glClearColor(0, 0, 0, 0);
		
		/* borra el buffer de la ventana */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		/* proyección en paralelo */ 
		gl.glOrtho(-1, 1, -1, 1, -1, 1);
		
		/* inicia secuencia: poligono */ 
	    gl.glBegin(GL.GL_TRIANGLES);
	    
	    	/* color rojo */
	    	gl.glColor3f(1, 0, 0);
	    	
	    	/* vertice (x,y) */
	    	gl.glVertex2f(-1, -1);
	    	
	    	/* color verde */
	    	gl.glColor3f(0, 1, 0);
	    	
	    	/* vertice (x,y) */
	    	gl.glVertex2f(1, -1);
	    	
	    	/* color azul */
	    	gl.glColor3f(0, 0, 1);

	    	/* vertice (x,y) */
	    	gl.glVertex2f(0, 1);

	    /* finaliza secuencia */
	    gl.glEnd();
	    
	    /* se asegura que se ejecute las anteriores instrucciones */
	    gl.glFlush();
	}

	/**
     * Se ejecuta cuando el usuario redimensiona la ventana.
     */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}

	/**
     * Se ejecuta cuando se cambia el modo de despliegue (Ej de 16 a 32 bits o visceversa).
     */
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}

	public static void main(String[] args) {
		
		/* Primero se crea el objeto JFrame */
		JFrame miMarco = new JFrame("Triangulo");
		
		/* Se le otorga un tamaño de la ventana */
		miMarco.setSize(512, 512);
		
		/* Nos aseguramos que al cerrar la ventana el programa finalice */
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Se crea el objeto GLJPanel */
		GLJPanel miPanel = new GLJPanel();

		/* Indicamos que el GLJPanel detecte los eventos del openGL */
		miPanel.addGLEventListener(new Triangulo());
		
		/* A continuacion insertamos el GLJPanel en el JFrame */
		miMarco.add(miPanel);

		/* Hacemos visible el elemento de mayor nivel */
		miMarco.setVisible(true);
	}
}
