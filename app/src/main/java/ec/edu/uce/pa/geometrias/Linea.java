package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Linea {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante =4; //son 4 por que en  un flotante hay 4 bytes
    private final static int compPorVertice=2;
    private final static int compPorColores=4;
    public Linea() {
        float[] verices = {
                4.0f,4.0f,   //0
                4.0f,-4.0f,  //1
                -4.0f,-4.0f,  //2
                -4.0f,4.0f,  //3
                4.0f,4.0f,   //0
        };
        float[] colores = {
                1.0f, 0.0f, 0.0f, 1.0f,//rojo se le asigna al vertice 0
                0.0f, 1.0f, 0.0f, 1.0f,//verde se le asigna al vertice 1
                0.0f, 0.0f, 1.0f, 1.0f,//azul se le asigna al vertice 2
                1.0f, 0.0f, 1.0f, 1.0f,//amarillo se le asigna al vertice 3
                1.0f, 0.0f, 1.0f, 1.0f,//amarillo se le asigna al vertice 3
        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(verices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(verices);
        bufferVertices.position(0);

        buffer = ByteBuffer.allocateDirect(colores.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);
        bufferColores.position(0);
    }

    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        //para el tamaño del punto
        gl.glLineWidth(30000);
        gl.glDrawArrays(gl.GL_LINE_STRIP,0,5);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
