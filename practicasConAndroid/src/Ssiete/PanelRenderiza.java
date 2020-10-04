package Ssiete;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


/**
 * Clase Renderiza
 *  
 * @author Jhonny Felipez
 * @version 1.0 13/03/2014
 *
 */
public class PanelRenderiza implements GLEventListener {
	/* Inicializa la variable GLU */
	GLU glu = new GLU();
	
	/* Objetos */
	Casa casa;
	Carretera carretera;
	Vehiculo vehiculo;
	
	/* Desplazamiento */
	private float incremento = -2.0f;
	
	/* Angulo de rotación */
	private float angulo = 0.0f;

	public void init(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		casa = new Casa();
		carretera = new Carretera();
		vehiculo = new Vehiculo();

		/* Color de fondo */
		gl.glClearColor(0, 0.5f, 1, 0);

		gl.setSwapInterval(1);
	}

	public void display(GLAutoDrawable drawable) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Borra el buffer de la ventana y del z-buffer */
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		/* Push MVM */
		gl.glPushMatrix();

		/* Se ubica la camara */
		glu.gluLookAt(0, 0, 10, 0, 0, 0, 0, 1, 0); // Acomoda la camara de vista

		casa.dibuja(gl);
		carretera.dibuja(gl);

		/* Traslada */
		gl.glTranslatef(incremento, 0, 0);

		vehiculo.dibuja(gl, angulo);

		/* Pop MVM */
		gl.glPopMatrix();

		/* Se asegura que se ejecute las instrucciones anteriores */
		gl.glFlush();

		incremento = incremento + 0.01f;
		if (incremento > 2)
			incremento = -2;

		angulo = angulo + 10;
		if (angulo > 360)
			angulo = 0;
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		/* Inicializa la variable GL */
		GL gl = drawable.getGL();

		/* Matriz de Proyección */
		gl.glMatrixMode(GL.GL_PROJECTION);

		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();

		/* Proyección en perspectiva */
		glu.gluPerspective(15, (float) w / (float) h, 1.0, 1000.0);

		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL.GL_MODELVIEW);

		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}
	
}
