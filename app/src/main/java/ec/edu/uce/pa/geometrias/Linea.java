package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Linea {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante = 4; //son 4 por que en  un flotante hay 4 bytes
    private final static int compPorVertice = 2;
    private final static int compPorColor = 4;

    public Linea() {
        float[] vertices = {
                -4.0f, 4.0f,   //0
                -3.0f, 3.0f,  //1
                -3.0f, 3.0f,  //2
                -2.0f, 3.0f,   //3
                -2.0f, 3.0f,     //4
                -1.0f, 4.0f,   //5
                -1.0f, 4.0f,   //6
                0.0f, 3.0f,   //7
                0.0f, 3.0f,  //8
                1.0f, 3.0f,   //9
                // SEGUNDA FIGURA
                -2.0f, 0.0f,   //10
                -1.0f, 0.0f,     //11
                -1.0f, 0.0f,     //12
                0.0f, -1.0f,     //13
                0.0f, -1.0f,     //14
                -1.0f, -2.0f,       //15
                -1.0f, -2.0f,        //16
                -2.0f, -2.0f,    //17
                -2.0f, -2.0f,    //18
                -3.0f, -1.0f,       //19
                -3.0f, -1.0f,    //20
                -2.0f, 0.0f      //21
        };


        float[] colores = {
                1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 0.0f, 1.0f,
                1.0f, 1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 0.0f, 1.0f,
                1.0f, 1.0f, 0.0f, 1.0f,
        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        buffer = ByteBuffer.allocateDirect(colores.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);
    }

    public void dibujar(GL10 gl) {

        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(1);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferVertices.position(0);
        gl.glColorPointer(compPorColor, gl.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glLineWidth(10);
        gl.glDrawArrays(gl.GL_LINES, 0, 4);

//        gl.glColor4f(2,4,2,4);
//        gl.glDrawArrays(gl.GL_POINTS, 1, 1);
//
//        gl.glColor4f(4,0,0,4);
//        gl.glDrawArrays(gl.GL_POINTS, 2, 1);
//
//        gl.glColor4f(0,0,4,4);
//        gl.glDrawArrays(gl.GL_POINTS, 3, 1);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

    }
}

