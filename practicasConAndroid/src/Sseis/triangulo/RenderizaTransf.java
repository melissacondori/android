package Sseis.triangulo;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
/**
 * Clase Renderiza (OpenGL)
 * 
 * @author Jhonny Felipez
 * @version 1.0 01/04/2014
 * 
 */
public class RenderizaTransf implements GLEventListener {
	
	Triangulo triangulo;

    /* Matriz de transformación */
    float[] m = new float[16];
    
	/**
	 * Se ejecuta al inicio.
	 */
	public void init(GLAutoDrawable drawable) {

		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		triangulo = new Triangulo();

		/* Fondo negro */
		gl.glClearColor(0, 0, 0, 0);
	}

	/**
	 * Se ejecuta para desplegar.
	 */
	public void display(GLAutoDrawable drawable) {

		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Borra el buffer de la ventana */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL.GL_MODELVIEW);
		
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
		
		/* Transformación */
		//m[0] = 1; m[4] = 0;  m[8] = 0; m[12] = 2;
		//m[1] = 0; m[5] = 1;  m[9] = 0; m[13] = 1;
		//m[2] = 0; m[6] = 0; m[10] = 1; m[14] = 0;
		//m[3] = 0; m[7] = 0; m[11] = 0; m[15] = 1;
		
		/* MVM = Inv_camara * m */
		//gl.glLoadMatrixf(m, 0);
		
		/* MVM = MVM * m */
		//gl.glMultMatrixf(m, 0);
		
		/* Traslación */
		//gl.glTranslatef(2, 1, 0);
		
		/* Escalamiento */
		gl.glScalef(2, 1, 0);
		
		/* Rotación */
		//gl.glRotatef(90, 0, 0, 1);
		
		gl.glGetFloatv(GL.GL_MODELVIEW_MATRIX, m, 0);
		
		System.out.println("Matriz:");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.printf("[%8.4f] ", m[i+j*4]);
			}
			System.out.println();
		}

		triangulo.dibuja(gl);

		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();
	}

	/**
	 * Se ejecuta cuando el usuario redimensiona la ventana.
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Inicializa la variable GLU */
		GLU glu = new GLU();

		/* Ventana de despliegue */
		gl.glViewport(0, 0, w, h);

		/* Matriz de Proyección */
		gl.glMatrixMode(GL.GL_PROJECTION);

		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();

		/* Proyección paralela */
		glu.gluOrtho2D(-5, 5, -5, 5);

		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL.GL_MODELVIEW);

		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
	}

	/**
	 * Se ejecuta cuando se cambia el modo de despliegue (Ej de 16 a 32 bits o visceversa).
	 */
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}
}
