package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangulo {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

    private ByteBuffer bufferIndices;
    private final static int byteFlotante =4; //son 4 por que en  un flotante hay 4 bytes
    private final static int compPorVertice=2;
    private final static int compPorColores=4;
    public Triangulo() {
        float[] vertices = {
                -3.0f,6.0f,   //0
                3.0f,6.0f,  //1
                4.0f,4.0f,
                4.0f,0.0f,
                -4.0f,0.0f,
                -4.0f,4.0f,
        };
        float[] colores = {
                1.0f, 0.0f, 0.0f, 1.0f,//rojo se le asigna al vertice 0
                0.0f, 1.0f, 0.0f, 1.0f,//verde se le asigna al vertice 1
                0.0f, 1.0f, 0.5f, 1.0f,//verde se le asigna al vertice 1
                0.0f, 1.0f, 0.0f, 1.0f,//verde se le asigna al vertice 1
                0.5f, 1.0f, 0.0f, 1.0f,//verde se le asigna al vertice 1
                0.0f, 0.0f, 1.0f, 1.0f,//azul se le asigna al vertice 2
        };
        byte[] indices = {
                0,1,2,3,4,5,0,5,2,5,2,3
        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        buffer = ByteBuffer.allocateDirect(colores.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);

        bufferIndices= ByteBuffer.allocateDirect(indices.length*4);
        bufferIndices.order(ByteOrder.nativeOrder());
        bufferIndices.put(indices);
        bufferIndices.position(0);

    }

    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLES,12,gl.GL_UNSIGNED_BYTE,bufferIndices);
        gl.glLineWidth(300);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
