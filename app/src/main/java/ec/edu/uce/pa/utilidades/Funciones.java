package ec.edu.uce.pa.utilidades;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Funciones {

    private static int[] arrTexturas=null;
    public static FloatBuffer setFloatBuffer(float [] array){

        FloatBuffer fb;
        ByteBuffer bbBuffer = ByteBuffer.allocateDirect(array.length * 4);
        bbBuffer.order(ByteOrder.nativeOrder());
        fb = bbBuffer.asFloatBuffer();
        fb .put(array);
        fb .position(0);
        return fb;
    }

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
    public static float[] calculateEllipseCoordinates(int n, float radiusX, float radiusY, float altura) {
        float[] coordinates = new float[3 * n];
        float angle = (float) (2 * Math.PI / n);

        for (int i = 0; i < n; i++) {
            coordinates[3 * i] = (float) (radiusX * Math.cos(i * angle));
            coordinates[3 * i + 1] = (float) (radiusY * Math.sin(i * angle));
            coordinates[3 * i + 2] = altura;
        }
        return coordinates;
    }


}
