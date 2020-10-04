package Ssiete;
import java.nio.FloatBuffer;
import javax.media.opengl.GL;
import com.sun.opengl.util.BufferUtil;

/**
 * Clase Vehiculo
 *  
 * @author Jhonny Felipez
 * @version 1.0 13/03/2014
 *
 */
public class Vehiculo {
	FloatBuffer bufVerticesCarroceria;
	FloatBuffer bufVerticesVentana;
	FloatBuffer bufVerticesCirculo;
	
	public Vehiculo(){
		/* Lee los vértices */
		bufVerticesCarroceria = BufferUtil.newFloatBuffer(16);
		bufVerticesCarroceria.put(0.15f);
		bufVerticesCarroceria.put(0.1f);
		bufVerticesCarroceria.put(0.1f);
		bufVerticesCarroceria.put(0.2f);
		bufVerticesCarroceria.put(-0.1f);
		bufVerticesCarroceria.put(0.2f);
		bufVerticesCarroceria.put(-0.15f);
		bufVerticesCarroceria.put(0.1f);
		bufVerticesCarroceria.put(-0.25f);
		bufVerticesCarroceria.put(0.1f);
		bufVerticesCarroceria.put(-0.25f);
		bufVerticesCarroceria.put(0);
		bufVerticesCarroceria.put(0.25f);
		bufVerticesCarroceria.put(0);
		bufVerticesCarroceria.put(0.25f);
		bufVerticesCarroceria.put(0.1f);
		bufVerticesCarroceria.rewind(); // puntero al principio del buffer

		/* Lee los vértices */
		bufVerticesVentana = BufferUtil.newFloatBuffer(8);
		bufVerticesVentana.put(-0.13f);
		bufVerticesVentana.put(0.11f);
		bufVerticesVentana.put(0.13f);
		bufVerticesVentana.put(0.11f);
		bufVerticesVentana.put(0.09f);
		bufVerticesVentana.put(0.19f);
		bufVerticesVentana.put(-0.09f);
		bufVerticesVentana.put(0.19f);
		bufVerticesVentana.position(0); // puntero al principio del buffer
		
		/* Lee los vértices */
		bufVerticesCirculo = BufferUtil.newFloatBuffer(720);
	}
	
	public void dibuja(GL gl, float angulo) {
		/* Push MVM */
		gl.glPushMatrix();

		/* Activa la matriz de vértices del lado del cliente */
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);

		// dibuja la carroceria

		/* Color rojo */
		gl.glColor3f(1, 0, 0);

		/* Especifica los datos para el arreglo de vértices */
		gl.glVertexPointer(2, GL.GL_FLOAT, 0, bufVerticesCarroceria);

		/* Renderiza las primitivas desde los datos de un arreglo */
		gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, 8);

		// dibuja la ventana
		
		/* Color Verde */
		gl.glColor3f(0, 1, 0);

		/* Especifica los datos para el arreglo de vértices */
		gl.glVertexPointer(2, GL.GL_FLOAT, 0, bufVerticesVentana);

		/* Renderiza las primitivas desde los datos de un arreglo */
		gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, 4);

		/* Desactiva la matriz de vértices del lado del cliente */
		gl.glDisableClientState(GL.GL_VERTEX_ARRAY);

		/* Push MVM */
		gl.glPushMatrix();

		// vaya a la posición de la rueda de adelante
		gl.glTranslatef(-0.15f, 0, 0); // traslada

		// dibujar rueda y pernos
		dibuja_rueda_pernos(gl, angulo);

		/* Pop MVM */
		gl.glPopMatrix();

		// vaya a la posición de la rueda de atrás
		gl.glTranslatef(0.15f, 0, 0); // traslada

		// dibujar rueda y pernos
		dibuja_rueda_pernos(gl, angulo);

		/* Pop MVM */
		gl.glPopMatrix();
	}
	
	public void dibuja_rueda_pernos(GL gl, float angulo) {
		float angulo2;
		int i;

		// dibujar rueda
		gl.glColor3f(0, 0, 1); // azul

		circulo(gl, 0, 0, 0.05f, 360, true);

		gl.glColor3f(1, 1, 1); // blanco

		// rota los cinco pernos
		gl.glRotatef(-angulo, 0, 0, 1); // rotacion

		// dibuja los cinco pernos
		angulo2 = 0.0f;
		for (i = 1; i <= 5; i++) {
			gl.glPushMatrix();
			// b) ubica el perno
			gl.glRotatef(-angulo2, 0, 0, 1); // rotacion

			// a) desplaza el perno
			circulo(gl, 0, 0.025f, 0.01f, 360, true);
			
			angulo2 = angulo2 + 72;
			
			gl.glPopMatrix();
		}
	}
	
	void circulo(GL gl, float cx, float cy, float r, int segmentos, boolean llenado) {
		
		/* Traslada */
		gl.glTranslatef(cx, cy, 0.0f);
		
	 	int j = 0;
	 	for (float i = 0; i < 360.0f; i+=(360.0f/segmentos)) {
	 		bufVerticesCirculo.put(j++, (float) Math.cos(Math.toRadians(i)) * r);
	 		bufVerticesCirculo.put(j++, (float) Math.sin(Math.toRadians(i)) * r);
	 	}
	 	
	 	/* Especifica los datos para el arreglo de vértices */
	 	gl.glVertexPointer (2, GL.GL_FLOAT, 0, bufVerticesCirculo);
	 	
	 	/* Activa la matriz de vértices del lado del cliente */
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
		
		/* Renderiza las primitivas desde los datos de un arreglo */
	 	gl.glDrawArrays ((llenado) ? GL.GL_TRIANGLE_FAN : GL.GL_LINE_LOOP, 0, segmentos);
	 	
	 	/* Desactiva la matriz de vértices del lado del cliente */
		gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
	}
}
