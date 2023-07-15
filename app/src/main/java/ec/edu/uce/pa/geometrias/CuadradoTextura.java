package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class CuadradoTextura {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private FloatBuffer bufferTextura;

    private ByteBuffer bufferIndices;
    private final static int byteFlotante =4; //son 4 por que en  un flotante hay 4 bytes
    private final static int compPorVertice=2;
    private final static int compPorColores=4;
    public CuadradoTextura() {
        float[] vertices = {
                1,1,   //0
                1,-1,  //1
                -1,-1,  //2
                -1,1    //3
        };
        float[] texturas ={
                -1,-1,
                -1,0,
                0,0,
                0,-1
        };

//        float[] colores = {
//                0.0f, 1.0f, 0.0f, 1.0f,
//                1.0f, 0.0f, 0.0f, 1.0f,
//                0.0f, 1.0f, 0.0f, 1.0f,
//                0.0f, 1.0f, 0.5f, 1.0f,
//                0.5f, 1.0f, 0.0f, 1.0f,
//                0.0f, 0.0f, 1.0f, 1.0f,
//        };
        byte[] indices = {
                0,1,2,
                0,2,3
        };

        bufferVertices = Funciones.generarBuffer(vertices);
//        bufferColores = Funciones.generarBuffer(colores);
        bufferTextura= Funciones.generarBuffer(texturas);


        bufferIndices= ByteBuffer.allocateDirect(indices.length);
        bufferIndices.order(ByteOrder.nativeOrder());
        bufferIndices.put(indices);
        bufferIndices.position(0);

    }

    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        gl.glTexCoordPointer(2,gl.GL_FLOAT,0,bufferTextura);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE,bufferIndices);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
    }
}
