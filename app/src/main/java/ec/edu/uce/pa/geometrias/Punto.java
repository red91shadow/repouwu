package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Punto {
    private FloatBuffer bufferVetices;
    private final static int byteFlotante = 4;

    public Punto() {
        float[] vertices = {
                4.0f, 4.0f,
                4.0f, -4.0f,
                -4.0f, -4.0f,
                -4.0f, 4.0f,
        };

        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVetices= buffer.asFloatBuffer();
        bufferVetices.put(vertices);
        bufferVetices.position(0);

    }

    public void dibujar(GL10 gl){

    }

}
