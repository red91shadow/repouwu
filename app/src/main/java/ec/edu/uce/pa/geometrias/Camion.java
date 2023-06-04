package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;


public class Camion {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante = 4; //son 4 por que en  un flotante hay 4 bytes
    private final static int compPorVertice = 2;
    private final static int compPorColores = 4;

    public Camion() {
        float[] verices = {
                //lado 1
                -4.0f, 4.0f, //1
                -3.5f, 4.0f, //2

                -3.0f, 4.0f, //3
                -2.5f, 4.0f, //4

                -2.0f, 4.0f, //5
                -1.5f, 4.0f, //6

                -1.0f, 4.0f, //7
                -0.5f, 4.0f, //8

                //lado 2
                0.0f, 4.0f, //1
                0.0f, 3.5f, //2

                0.0f, 3.0f, //3
                0.0f, 2.5f, //4

                0.0f, 2.0f, //5
                0.0f, 1.5f, //6

                0.0f, 1.0f, //7
                0.0f, 0.5f, //8


                // lado 3
                -0.5f, 0.5f, //1
                -1.0f, 0.5f, //2

                -1.5f, 0.5f, //3
                -2.0f, 0.5f, //4

                -2.5f, 0.5f, //5
                -3.0f, 0.5f, //6

                -3.5f, 0.5f, //7
                -4.0f, 0.5f, //8

                //lado 4

                -4.5f, 0.5f, //1
                -4.5f, 1.0f, //2

                -4.5f, 1.5f, //3
                -4.5f, 2.0f, //4

                -4.5f, 2.5f, //5
                -4.5f, 3.0f, //6

                -4.5f, 3.5f, //7
                -4.5f, 4.0f, //8

                //lado 1

                3.0f, 2.0f, //1
                3.0f, 1.5f, //2

                3.0f, 1.0f, //3
                3.0f, 0.5f, //4

                // lado 2
                2.5f, 0.5f, //1
                2.0f, 0.5f, //2

                1.5f, 0.5f, //3
                1.0f, 0.5f, //4

                0.5f, 0.5f, //5
                0.0f, 0.5f, //6

                //lado 3
                0.0f, 0.5f, //1
                0.0f, 1.0f, //2

                0.0f, 1.5f, //3
                0.0f, 2.0f, //4

                //  lado 4

                0.5f, 2.0f, //1
                1.0f, 2.0f, //2

                1.5f, 2.0f, //3
                2.0f, 2.0f, //4

                2.5f, 2.0f, //5
                3.0f, 2.0f, //6

        };

        float[] colores = {
                //Primer Rectangulo
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f, //10
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f, //10
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f, //10
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,

                /////
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,


        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(verices.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(verices);
        bufferVertices.position(0);

        buffer = ByteBuffer.allocateDirect(colores.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);
        bufferColores.position(0);
    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glLineWidth(7);//para el tamaño del punto
        gl.glDrawArrays(gl.GL_LINES, 0, 54);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
