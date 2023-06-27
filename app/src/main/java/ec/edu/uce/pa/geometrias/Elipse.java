package ec.edu.uce.pa.geometrias;
import java.util.ArrayList;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Elipse {
    private float centerX;
    private float centerY;
    private float radiusX;
    private float radiusY;
    private static int numSegments;


    private static FloatBuffer vertexBuffer;

    public Elipse(float centerX, float centerY, float radiusX, float radiusY, int numSegments) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.numSegments = numSegments;

        ArrayList<Float> vertices = new ArrayList<>();
        float angleStep = (float)(2.0 * Math.PI / numSegments);

        for (int i = 0; i < numSegments; i++) {
            float angle = i * angleStep;
            float x = centerX + radiusX * (float)Math.cos(angle);
            float y = centerY + radiusY * (float)Math.sin(angle);
            vertices.add(x);
            vertices.add(y);
        }

        // Convertir los vértices a un arreglo de tipo float
        float[] vertexArray = new float[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            vertexArray[i] = vertices.get(i);
        }

        // Crear el buffer de vértices
        ByteBuffer bb = ByteBuffer.allocateDirect(vertexArray.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertexArray);
        vertexBuffer.position(0);
    }

    public static void dibujar(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glColor4f(0, 0.5f, 0, 1); //VERDE
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glLineWidth(10);
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, numSegments);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

    }
}