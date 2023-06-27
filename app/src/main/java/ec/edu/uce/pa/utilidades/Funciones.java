package ec.edu.uce.pa.utilidades;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Funciones {


    public static FloatBuffer generarBuffer(float[] arrayVertices) {
        int byteFlotante = 4;
        FloatBuffer result;
        ByteBuffer buffer = ByteBuffer.allocateDirect(arrayVertices.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        result = buffer.asFloatBuffer();
        result.put(arrayVertices);
        result.position(0);
        return result;
    }

    public static float[] calculatePolygonCoordinates(int n, float radius, float altura) {
        float[] coordinates = new float[3 * n];
        float angle = (float) (2 * Math.PI / n);

        for (int i = 0; i < n; i++) {
            coordinates[3 * i] = (float) (radius * Math.cos(i * angle));
            coordinates[3 * i + 1] = (float) (radius * Math.sin(i * angle));
            coordinates[3 * i + 2] = altura;
        }
        return coordinates;
    }

}
