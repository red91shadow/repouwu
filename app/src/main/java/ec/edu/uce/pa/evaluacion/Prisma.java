package ec.edu.uce.pa.evaluacion;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Prisma {
    private static float[] vertices;
    private static short[] indices;

    private static FloatBuffer vertexBuffer;
    private static ShortBuffer indexBuffer;

    private static int numVertices;
    private static int numIndices;
    private static int numSides;

    public Prisma(float width, float height, int numSides) {
        numVertices = numSides * 2 + 2;  // Incluye los vértices de las tapas
        numIndices = numSides * 12;  // Incluye los índices de las tapas y los lados

        vertices = generateVertices(width, height, numSides);
        indices = generateIndices(numSides);

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    private float[] generateVertices(float width, float height, int numSides) {
        float[] vertices = new float[numVertices * 3];
        float angleStep = (float) (2.0 * Math.PI / numSides);

        // Generar vértices de las tapas
        vertices[0] = 0.0f;  // Vértice central de la tapa inferior
        vertices[1] = -height / 2.0f;
        vertices[2] = 0.0f;
        vertices[numVertices * 3 - 3] = 0.0f;  // Vértice central de la tapa superior
        vertices[numVertices * 3 - 2] = height / 2.0f;
        vertices[numVertices * 3 - 1] = 0.0f;

        for (int i = 0; i < numSides; i++) {
            float angle = i * angleStep;
            float x = (float) (width * Math.cos(angle));
            float y = height / 2.0f;
            float z = (float) (width * Math.sin(angle));

            // Vértices superiores
            vertices[i * 3 + 3] = x;
            vertices[i * 3 + 4] = y;
            vertices[i * 3 + 5] = z;

            // Vértices inferiores
            vertices[(i + numSides) * 3 + 3] = x;
            vertices[(i + numSides) * 3 + 4] = -y;
            vertices[(i + numSides) * 3 + 5] = z;
        }

        return vertices;
    }

    private short[] generateIndices(int numSides) {
        short[] indices = new short[numIndices];

        // Generar índices de las tapas
        for (int i = 0; i < numSides; i++) {
            int startIndex = i * 6;
            int topVertexIndex = i + 1;
            int bottomVertexIndex = i + numSides + 1;

            // Triángulo superior|ESdRTFGHM NBVCXddMK,L.
            indices[startIndex] = (short) topVertexIndex;
            indices[startIndex + 1] = (short) ((topVertexIndex % numSides) + 1);
            indices[startIndex + 2] = (short) 0;  // Vértice central de la tapa superior

            // Triángulo inferior
            indices[startIndex + 3] = (short) ((bottomVertexIndex % numSides) + numSides + 1);
            indices[startIndex + 4] = (short) ((topVertexIndex % numSides) + 1);
            indices[startIndex + 5] = (short) bottomVertexIndex;
        }

        // Generar índices de los lados
        for (int i = 0; i < numSides; i++) {
            int startIndex = numSides * 6 + i * 6;
            int topVertexIndex = i + 1;
            int bottomVertexIndex = i + numSides + 1;

            // Triángulo lateral superior
            indices[startIndex] = (short) topVertexIndex;
            indices[startIndex + 1] = (short) ((topVertexIndex % numSides) + 1);
            indices[startIndex + 2] = (short) (topVertexIndex + numSides);

            // Triángulo lateral inferior
            indices[startIndex + 3] = (short) ((topVertexIndex % numSides) + 1);
            indices[startIndex + 4] = (short) ((bottomVertexIndex % numSides) + numSides + 1);
            indices[startIndex + 5] = (short) bottomVertexIndex;
        }

        return indices;
    }

    public static void dibujar(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glColor4f(0, 0.5f, 0, 1); //VERDE

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, numIndices+2, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
}
}