package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;


public class Linea {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante = 4; // 4 bytes per float
    private final static int compPorVertice = 2;
    private final static int compPorColores = 4;

    public Linea() {
        float[] vertices = {
                4.0f, 4.0f,   // 0
                4.0f, -4.0f,  // 1
                -4.0f, -4.0f, // 2
                -4.0f, 4.0f,  // 3
                4.0f, 4.0f    // 0
        };
        float[] colores = {
                1.0f, 0.0f, 0.0f, 1.0f, // red assigned to vertex 0
                0.0f, 1.0f, 0.0f, 1.0f, // green assigned to vertex 1
                0.0f, 0.0f, 1.0f, 1.0f, // blue assigned to vertex 2
                1.0f, 0.0f, 1.0f, 1.0f, // yellow assigned to vertex 3
                1.0f, 0.0f, 1.0f, 1.0f  // yellow assigned to vertex 3
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
        gl.glLineWidth(2.0f);

        // Calculate the step size for the dashed line segments
        float dashLength = 0.1f;
        float gapLength = 0.1f;
        float segmentLength = dashLength + gapLength;

        // Draw the dashed line segments
        for (int i = 0; i < 4; i++) {
            float x1 = bufferVertices.get(i * 2);
            float y1 = bufferVertices.get(i * 2 + 1);
            float x2 = bufferVertices.get((i + 1) * 2);
            float y2 = bufferVertices.get((i + 1) * 2 + 1);
            float dx = x2 - x1;
            float dy = y2 - y1;
            float length = (float) Math.sqrt(dx * dx + dy * dy);
            float segmentCount = length / segmentLength;

            for (int j = 0; j < segmentCount; j++) {
                if (j % 2 == 0) {
                    gl.glDrawArrays(GL10.GL_LINES, (i * 2 + j) * 2, 2);
                }
            }
        }

        gl.glFrontFace(GL10.GL_CCW);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
