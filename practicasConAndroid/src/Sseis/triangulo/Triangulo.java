 package Sseis.triangulo;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import javax.media.opengl.GL;
import com.sun.opengl.util.BufferUtil;
/**
 * Clase Triangulo
 * 
 * @author Jhonny Felipez
 * @version 1.0 01/04/2014
 * 
 */
public class Triangulo {
	
	/**
	 *        2
     *       /\
     *      /  \
     *     /    \
     *    /      \
     *   /________\
     *  0          1  
     */
	
	private float vertices[] = new float [] {
		-1, -1, // 0
		 1, -1, // 1
		 0,  1  // 2
	};

	byte maxColor = (byte)255;
	
	private byte colores[] = new byte[] { 
		maxColor, 0, 0, 1, // 0
		maxColor, 0, 0, 1, // 1
		maxColor, 0, 0, 1  // 2
	};
	
	FloatBuffer bufVertices;
	ByteBuffer bufColores;

	public Triangulo() {
		/* Lee los vértices */
		bufVertices = BufferUtil.newFloatBuffer(vertices.length);
		bufVertices.put(vertices);
		bufVertices.rewind(); // puntero al principio del buffer

		/* Lee los colores */
		bufColores = BufferUtil.newByteBuffer(colores.length);
		bufColores.put(colores);
		bufColores.position(0); // puntero al principio del buffer
	}
	
	public void dibuja(GL gl) {
		
		/* Activa el arreglo de vértices del lado del cliente */
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);

		/* Activa el arreglo de colores del lado del cliente */
		gl.glEnableClientState(GL.GL_COLOR_ARRAY);
		
		/* Especifica los datos del arreglo de vértices */
		gl.glVertexPointer(2, GL.GL_FLOAT, 0, bufVertices);

		/* Especifica los datos del arreglo de colores */
		gl.glColorPointer(4, GL.GL_UNSIGNED_BYTE, 0, bufColores);

		/* Renderiza las primitivas desde los datos de los arreglos (vértices, colores) */
		gl.glDrawArrays(GL.GL_TRIANGLES, 0, 3);

		/* Desactiva el arreglo de vértices del lado del cliente */
		gl.glDisableClientState(GL.GL_VERTEX_ARRAY);

		/* Desactiva el arreglo de colores del lado del cliente */
		gl.glDisableClientState(GL.GL_COLOR_ARRAY);
	}
}
