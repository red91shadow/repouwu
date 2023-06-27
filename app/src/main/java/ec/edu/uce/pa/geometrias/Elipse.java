package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class Elipse {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante = 4; // 4 bytes per float
    private final static int compPorVertice = 2;
    private final static int compPorColores = 4;

    public Elipse() {
        float[] vertices = Funciones.calculateEllipseCoordinates(10, 2, 3, 0);
        // Assign any color
        float[] colores = {
                1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f
        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);
        bufferVertices.position(0);

        buffer = ByteBuffer.allocateDirect(colores.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);
        bufferColores.position(0);
    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, GL10.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, GL10.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        // Set the line width
        gl.glLineWidth(3.0f);

        // Correct the number of vertices to match the actual count
        int vertexCount = 10; // Adjust this to the actual number of vertices

        // Render the ellipse
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, vertexCount);

        gl.glFrontFace(GL10.GL_CCW);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
