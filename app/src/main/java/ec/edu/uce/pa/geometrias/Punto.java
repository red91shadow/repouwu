package ec.edu.uce.pa.geometrias;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Punto {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante = 4;
    private final static int compPorVertice = 2;
    private final static int compPorColores = 4;
    private static int numPuntos = 20;

    public Punto() {
        float[] vertices = generateCircleVertices(0.5f, numPuntos);
        float[] colores = generateRandomColors(numPuntos);

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

    private float[] generateCircleVertices(float radius, int numPoints) {
        // Create an array to store the vertices (coordinates) of the circle
        float[] vertices = new float[numPoints * 2];

        // Calculate the angle increment between each point on the circle
        double angleIncrement = 2 * Math.PI / numPoints;

        // Start with an initial angle of 0
        double angle = 0;

        // Loop through each point on the circle
        for (int i = 0; i < numPoints; i++) {
            // Calculate the x-coordinate of the current point using the cosine of the angle
            vertices[i * 2] = (float) (radius * Math.cos(angle));

            // Calculate the y-coordinate of the current point using the sine of the angle
            vertices[i * 2 + 1] = (float) (radius * Math.sin(angle));

            // Increment the angle for the next point
            angle += angleIncrement;
        }

        // Return the generated vertices
        return vertices;
    }

    private float[] generateRandomColors(int numPoints) {
        float[] colors = new float[numPoints * compPorColores];

        for (int i = 0; i < numPoints; i++) {
            colors[i * compPorColores] = (float) Math.random();    // Red component
            colors[i * compPorColores + 1] = (float) Math.random();  // Green component
            colors[i * compPorColores + 2] = (float) Math.random();  // Blue component
            colors[i * compPorColores + 3] = 1.0f;                   // Alpha component
        }

        return colors;
    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glPointSize(25);
        gl.glDrawArrays(gl.GL_POINTS, 0, numPuntos);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
