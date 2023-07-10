package ec.edu.uce.pa.evaluacion;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CuboColores {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

    private ByteBuffer bufferIndices;
    private final static int byteFlotante = 4;
    private final static int compPorVertice = 3;
    private final static int compPorColores = 4;

    public CuboColores() {
        float[] vertices = {
                -0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,

                0.5f, 0.5f, -0.5f,
                -0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,

                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, 0.5f,

                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,

                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, 0.5f,

                -0.5f, 0.5f, -0.5f,
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,
        };
        float[] colores = {
                // Front face
                1.0f, 0.0f, 0.0f, 1.0f, // Red
                1.0f, 0.0f, 0.0f, 1.0f, // Red
                1.0f, 0.0f, 0.0f, 1.0f, // Red
                1.0f, 0.0f, 0.0f, 1.0f, // Red
                // Back face
                0.0f, 1.0f, 0.0f, 1.0f, // Green
                0.0f, 1.0f, 0.0f, 1.0f, // Green
                0.0f, 1.0f, 0.0f, 1.0f, // Green
                0.0f, 1.0f, 0.0f, 1.0f, // Green
                // Top face
                0.0f, 0.0f, 1.0f, 1.0f, // Blue
                0.0f, 0.0f, 1.0f, 1.0f, // Blue
                0.0f, 0.0f, 1.0f, 1.0f, // Blue
                0.0f, 0.0f, 1.0f, 1.0f, // Blue
                // Bottom face
                1.0f, 1.0f, 0.0f, 1.0f, // Yellow
                1.0f, 1.0f, 0.0f, 1.0f, // Yellow
                1.0f, 1.0f, 0.0f, 1.0f, // Yellow
                1.0f, 1.0f, 0.0f, 1.0f, // Yellow
                // Right face
                1.0f, 0.0f, 1.0f, 1.0f, // Magenta
                1.0f, 0.0f, 1.0f, 1.0f, // Magenta
                1.0f, 0.0f, 1.0f, 1.0f, // Magenta
                1.0f, 0.0f, 1.0f, 1.0f, // Magenta
                // Left face
                0.0f, 1.0f, 1.0f, 1.0f, // Cyan
                0.0f, 1.0f, 1.0f, 1.0f, // Cyan
                0.0f, 1.0f, 1.0f, 1.0f, // Cyan
                0.0f, 1.0f, 1.0f, 1.0f, // Cyan
        };
        byte[] indices = {
                0, 1, 2, 2, 3, 0,
                4, 5, 6, 6, 7, 4,
                8, 9, 10, 10, 11, 8,
                12, 13, 14, 14, 15, 12,
                16, 17, 18, 18, 19, 16,
                20, 21, 22, 22, 23, 20
        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        buffer = ByteBuffer.allocateDirect(colores.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);

        bufferIndices = ByteBuffer.allocateDirect(indices.length * 4);
        bufferIndices.order(ByteOrder.nativeOrder());
        bufferIndices.put(indices);
        bufferIndices.position(0);

    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLES, 144, gl.GL_UNSIGNED_BYTE, bufferIndices);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
