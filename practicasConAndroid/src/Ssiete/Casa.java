
package Ssiete;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.media.opengl.GL;
import com.sun.opengl.util.BufferUtil;

/**
 * Clase Casa
 *  
 * @author Jhonny Felipez
 * @version 1.0 13/03/2014
 *
 */
public class Casa {
	private float vertices[] = new float [] {
		// Casa 1
		-0.5f, 0.05f, // 0
		 0.0f, 0.05f, // 1
		 0.0f, 0.80f, // 2
		-0.5f, 0.80f, // 3
		// Puerta 1
		-0.30f, 0.25f, // 4
		-0.15f, 0.25f, // 5
		-0.15f, 0.05f, // 6
		-0.30f, 0.05f, // 7
		// Casa 2
		 0.0f, 0.60f, // 8
		 0.5f, 0.60f, // 9
		 0.5f, 0.05f, // 10
		 0.0f, 0.05f, // 11
		// Puerta 2
		 0.20f, 0.25f, // 12
		 0.35f, 0.25f, // 13
		 0.35f, 0.05f, // 14
		 0.20f, 0.05f  // 15
	};

	byte maxValor = (byte)255;
	
	private byte colores[] = new byte[] {
		// Casa 1
		maxValor, 0, maxValor, maxValor, // 0
		maxValor, 0, maxValor, maxValor, // 1
		maxValor, 0, maxValor, maxValor, // 2
		maxValor, 0, maxValor, maxValor, // 3
		// Puerta 1
		maxValor, (byte)128, maxValor, maxValor, // 4 
		maxValor, (byte)128, maxValor, maxValor, // 5
		maxValor, (byte)128, maxValor, maxValor, // 6
		maxValor, (byte)128, maxValor, maxValor, // 7
		// Casa 2
		maxValor, maxValor, 0, maxValor, // 8
		maxValor, maxValor, 0, maxValor, // 9
		maxValor, maxValor, 0, maxValor, // 10
		maxValor, maxValor, 0, maxValor, // 11
		// Puerta 2
		maxValor, maxValor, (byte)128, maxValor, // 12
		maxValor, maxValor, (byte)128, maxValor, // 13
		maxValor, maxValor, (byte)128, maxValor, // 14
		maxValor, maxValor, (byte)128, maxValor  // 15
	};
	
	private short indices[] = new short[] {
		 0,  1,  2,  0,  2,  3, // Casa 1
		 4,  5,  6,  4,  6,  7, // Puerta 1
		 8,  9, 10,  8, 10, 11, // Casa 2
		12, 13, 14, 12, 14, 15  // Puerta 2
	};

	FloatBuffer bufVertices;
	ByteBuffer bufColores;
	ShortBuffer bufIndices;
	
	public Casa(){
		/* Lee los vértices */
		bufVertices = BufferUtil.newFloatBuffer(vertices.length);
		bufVertices.put(vertices);
		bufVertices.rewind(); // puntero al principio del buffer

		/* Lee los colores */
		bufColores = BufferUtil.newByteBuffer(colores.length);
		bufColores.put(colores);
		bufColores.rewind(); // puntero al principio del buffer
		
		/* Lee los indices */
		bufIndices = BufferUtil.newShortBuffer(indices.length);
		bufIndices.put(indices);
		bufIndices.rewind(); // puntero al principio del buffer
	}
	
	public void dibuja(GL gl) {
		
		/* Activa la matriz de vértices del lado del cliente */
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
		
		/* Activa la matriz de colores del lado del cliente */
		gl.glEnableClientState(GL.GL_COLOR_ARRAY);
		
		/* Especifica los datos para el arreglo de vértices */
		gl.glVertexPointer(2, GL.GL_FLOAT, 0, bufVertices);

		/* Especifica los datos para el arreglo de colores */
		gl.glColorPointer(4, GL.GL_UNSIGNED_BYTE, 0, bufColores);

		/* Renderiza las primitivas desde los datos de un arreglo */
		gl.glDrawElements(GL.GL_TRIANGLES, 24, GL.GL_UNSIGNED_SHORT, bufIndices);

		/* Desactiva la matriz de vértices del lado del cliente */
		gl.glDisableClientState(GL.GL_VERTEX_ARRAY);

		/* Desactiva la matriz de colores del lado del cliente */
		gl.glDisableClientState(GL.GL_COLOR_ARRAY);
	}
}
