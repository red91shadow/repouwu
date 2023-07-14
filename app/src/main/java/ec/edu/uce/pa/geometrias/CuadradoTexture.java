package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class CuadradoTexture {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

    private FloatBuffer bufferTexturas;

    private final static int byteFlotante = 4;
    private final static int compPorVertices = 2;
    private final static int compPorColores = 4;

    private ByteBuffer bufferIndice;

    public CuadradoTexture() {
        float[] vertices = {

                1,1,  //0
                1,-1,  //1
                -1,-1,//2
                -1,1  //3
        };

        float[] texturas = {

                 /*-1,-1,
                 -1,0,
                 0,0,
                 0,-1*/
                1,0,
                1,1,
                0,1,
                0,0
        };
        float[] colores = {

                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f

        };
        byte[] indices = {

                0,1,2,
                0,2,3

        };

        bufferVertices = Funciones.generarBuffer(vertices);
        bufferColores = Funciones.generarBuffer(colores);
        bufferTexturas = Funciones.generarBuffer(texturas);

        bufferIndice = ByteBuffer.allocateDirect(indices.length);
        bufferIndice.order(ByteOrder.nativeOrder());
        bufferIndice.put(indices);
        bufferIndice.position(0);
    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertices, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glTexCoordPointer(2,gl.GL_FLOAT,0,bufferTexturas);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);

        //gl.glPointSize(40);
        //gl.glLineWidth(15);
        gl.glDrawElements(gl.GL_TRIANGLES, 6, gl.GL_UNSIGNED_BYTE, bufferIndice);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
    }
}