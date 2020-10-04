package rectangulo;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.media.opengl.GL;

import com.sun.opengl.util.BufferUtil;

/**
 * Clase Rectangulo OpenGL
 * 
 * @author Jhonny Felipez
 * @version 1.0 21/08/2014
 * 
 */
public class Rectangulo {
	
    /**
     *    3 ------- 2
     *     |     / | 
     *     |   /   |
     *     | /     |
     *    0 ------- 1  
	 */
	private float vertices[] = new float[] {
		// Rectángulo 1
		-3, 0, // 0
		 0, 0, // 1
		 0, 3, // 2
		-3, 3, // 3
		// Rectángulo 2
		 0, 0, // 4
		 3, 0, // 5
		 3, 3, // 6
		 0, 3  // 7
	};

	private byte colores[] = new byte[] {
		// Rectángulo 1
		(byte)255, 0, 0, 0, // 0
		(byte)255, 0, 0, 0, // 1
		(byte)255, 0, 0, 0, // 2
		(byte)255, 0, 0, 0, // 3
		/*/ Rectángulo 2
		0, 0, (byte)255, 0, // 4
		0, 0, (byte)255, 0, // 5
		0, 0, (byte)255, 0, // 6
		0, 0, (byte)255, 0  // 7
		*/
		/////////////////
		 0, (byte)255, 0, 0, // 4
		 0, (byte)255, 0, 0, // 5
		 0, (byte)255, 0, 0, // 6
		 0, (byte)255, 0, 0, // 7
		
		///////////////
	};
	
	private short indices[] = new short [] { 
		 0, 1, 2, 0, 2, 3, // Rectángulo 1
		 4, 5, 6, 4, 6, 7  // Rectángulo 2
	};
	
	FloatBuffer bufVertices;
	ByteBuffer bufColores;
	ShortBuffer bufIndices;
	
	public Rectangulo(){
		
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
	
	public void dibuja(GL gl){
		
		/* Activa el arreglo de vértices del lado del cliente */
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);

		/* Activa el arreglo de las normales del lado del cliente */
		gl.glEnableClientState(GL.GL_COLOR_ARRAY);
		
		/* Especifica los datos del arreglo de vértices */
		gl.glVertexPointer(2, GL.GL_FLOAT, 0, bufVertices);

		/* Especifica los datos del arreglo de colores */
		gl.glColorPointer(4, GL.GL_UNSIGNED_BYTE, 0, bufColores);
		
		/* Renderiza las primitivas desde los datos de los arreglos (vertices, colores e indices) */
		gl.glDrawElements(GL.GL_TRIANGLES, indices.length, GL.GL_UNSIGNED_SHORT, bufIndices);

		/* Desactiva el arreglo de vértices del lado del cliente */
		gl.glDisableClientState(GL.GL_VERTEX_ARRAY);

		/* Desactiva el arreglo de colores del lado del cliente */
		gl.glDisableClientState(GL.GL_COLOR_ARRAY);
	}
}
