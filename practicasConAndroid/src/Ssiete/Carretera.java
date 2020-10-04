package Ssiete;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.media.opengl.GL;

import com.sun.opengl.util.BufferUtil;

/**
 * Clase Carretera
 *  
 * @author Jhonny Felipez
 * @version 1.0 13/03/2014
 *
 */
public class Carretera {
	private float vertices[] = new float[] {
		// Asfalto
	    -2.0f, -0.10f, // 0
	     2.0f, -0.10f, // 1
	     2.0f,  0.05f, // 2
	    -2.0f,  0.05f, // 3
		// Linea blanca 	
		-2.0f, -0.005f, // 4
		-1.9f, -0.005f, // 5
		-1.9f,  0.005f, // 6
		-2.0f,  0.005f  // 7
	};

	byte maxColor = (byte)255;
	byte minColor = (byte)0;
	
	private short indices[] = new short[] {
			0, 1, 2, 0, 2, 3 // Asfalto
	};
	
	FloatBuffer bufVertices;
	ByteBuffer bufColores;
	ShortBuffer bufIndices;
	
	public Carretera(){
		/* Lee los vértices y colores */
		bufVertices = BufferUtil.newFloatBuffer(21 * 8); // 21 rectángulos (vértices)
		bufColores = BufferUtil.newByteBuffer(21 * 16);  // 21 rectángulos (colores)
		
		// Asfalto
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufVertices.put(vertices[0]);	
		bufVertices.put(vertices[1]);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufVertices.put(vertices[2]);
		bufVertices.put(vertices[3]);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufVertices.put(vertices[4]);
		bufVertices.put(vertices[5]);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufColores.put(minColor);
		bufVertices.put(vertices[6]);
		bufVertices.put(vertices[7]);
		
		// Lineas blancas
		for (float i = 0; i <= 4; i += 0.2) { // 20 rectángulos
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufVertices.put(vertices[8] + i);
			bufVertices.put(vertices[9]);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufVertices.put(vertices[10] + i);
			bufVertices.put(vertices[11]);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufVertices.put(vertices[12] + i);
			bufVertices.put(vertices[13]);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufColores.put(maxColor);
			bufVertices.put(vertices[14] + i);
			bufVertices.put(vertices[15]);
		}
		bufVertices.rewind(); // puntero al principio del buffer
		bufColores.rewind(); // puntero al principio del buffer
		
		/* Lee los indices */
		bufIndices = BufferUtil.newShortBuffer(21 * 6);
		for (short i = 0; i < 4 * 21; i += 4) { // 21 rectángulos (índices)
			bufIndices.put((short)(indices[0] + i));
			bufIndices.put((short)(indices[1] + i));
			bufIndices.put((short)(indices[2] + i));
			bufIndices.put((short)(indices[3] + i));
			bufIndices.put((short)(indices[4] + i));
			bufIndices.put((short)(indices[5] + i));
		}
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
		gl.glDrawElements(GL.GL_TRIANGLES, 6 * 21, GL.GL_UNSIGNED_SHORT, bufIndices);

		/* Desactiva la matriz de vértices del lado del cliente */
		gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
		
		/* Desactiva la matriz de colores del lado del cliente */
		gl.glDisableClientState(GL.GL_COLOR_ARRAY);
	}
}
