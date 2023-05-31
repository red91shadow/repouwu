package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Linea {
    private FloatBuffer bufferVetices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante = 4;

    private final static  int compPorVertices=2;
    private final static  int compPorColor=4;

    public Linea() {
        float[] vertices = {
                4.0f, 4.0f,     //0
                4.0f, -4.0f,    //1
                -4.0f, -4.0f,   //2
                -4.0f, 4.0f,    //3
        };
        float[] colores = {
                4.0f, 0.0f, 0.0f, 0.0f,    //0
                -4.0f,0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 4.0f, 0.0f,    //2
                0.0f, 0.0f, 0.0f, 4.0f,    //3
        };

        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVetices= buffer.asFloatBuffer();
        bufferVetices.put(vertices);
        bufferVetices.position(0);

        buffer=ByteBuffer.allocateDirect(colores.length*byteFlotante);

        buffer.order(ByteOrder.nativeOrder());
        bufferColores=buffer.asFloatBuffer();
        bufferColores.put(colores);


    }

    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(compPorVertices, gl.GL_FLOAT,0,bufferVetices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        gl.glColorPointer(compPorColor,GL10.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);



        gl.glLineWidth(10);
//        gl.glColor4f(4,4,4,4);
//        gl.glDrawArrays(GL10.GL_POINTS,0,-4);

//        gl.glColor4f(4,4,4,4);
//        gl.glDrawArrays(GL10.GL_POINTS,1,-4);
//
//        gl.glColor4f(4,4,4,4);
//        gl.glDrawArrays(GL10.GL_POINTS,2,-4);
//
//        gl.glColor4f(4,4,4,4);
//        gl.glDrawArrays(GL10.GL_POINTS,3,-4);




        gl.glFrontFace(GL10.GL_CCW);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

}
