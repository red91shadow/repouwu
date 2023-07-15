package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class TrianguloTexture {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferTexturas0;
    private FloatBuffer bufferTexturas1;
    private FloatBuffer bufferTexturas2;
    private FloatBuffer bufferTexturas3;
    private final static int compPorVertices = 3;
    private ByteBuffer bufferIndicePiramide;

    public TrianguloTexture() {
        float[] vertices = {
                0,1,0,
                1,-1,-1,
                1,-1,1,
                -1,-1, 1,
                -1,-1,-1
        };

        byte[] indicesPiramide = {
                0,1,2,
                0,2,3,
                0,3,4,
                0,4,1
        };

        float[] texturas0 = {

                0.5f,0,
                0.66f,1,
                0.33f,1
        };
        float[] texturas1 = {

                0.5f,0.0f,
                0.33f,1.0f,
                0.0f,1.0f
        };
        float[] texturas2 = {

                0.5f,0.0f,
                0.33f,1.0f,
                0.0f,1.0f
        };
        float[] texturas3 = {
                0.0f,0.0f,
                0.0f,1.0f,
                1.0f,1.0f
        };
        float[] texturas4 = {

                0.5f,0.0f,
                0.33f,1.0f,
                0.0f,1.0f
        };

        bufferVertices = Funciones.generarBuffer(vertices);
        bufferTexturas0 = Funciones.generarBuffer(texturas0);
        bufferTexturas1 = Funciones.generarBuffer(texturas1);
        bufferTexturas2 = Funciones.generarBuffer(texturas2);
        bufferTexturas3 = Funciones.generarBuffer(texturas3);

        bufferIndicePiramide = ByteBuffer.allocateDirect(indicesPiramide.length);
        bufferIndicePiramide.order(ByteOrder.nativeOrder());
        bufferIndicePiramide.put(indicesPiramide);
        bufferIndicePiramide.position(0);

    }

    public void dibujarCara0(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);

        bufferVertices.position(0);
        bufferIndicePiramide.position(0);
        gl.glVertexPointer(compPorVertices, gl.GL_FLOAT, 0, bufferVertices);
        gl.glTexCoordPointer(2,gl.GL_FLOAT,0,bufferTexturas0);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 3, gl.GL_UNSIGNED_BYTE, bufferIndicePiramide);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);

    }
    public void dibujarCara1(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);

        bufferVertices.position(0);
        bufferIndicePiramide.position(3);
        gl.glVertexPointer(compPorVertices, gl.GL_FLOAT, 0, bufferVertices);
        gl.glTexCoordPointer(2,gl.GL_FLOAT,0,bufferTexturas1);

        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 3, gl.GL_UNSIGNED_BYTE, bufferIndicePiramide);
        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
    }
    public void dibujarCara2(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);
        bufferVertices.position(0);
        bufferIndicePiramide.position(6);
        gl.glVertexPointer(compPorVertices, gl.GL_FLOAT, 0, bufferVertices);
        gl.glTexCoordPointer(2,gl.GL_FLOAT,0,bufferTexturas2);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 3, gl.GL_UNSIGNED_BYTE, bufferIndicePiramide);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
    }
    public void dibujarCara3(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);
        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertices, gl.GL_FLOAT, 0, bufferVertices);

        gl.glTexCoordPointer(2,gl.GL_FLOAT,0,bufferTexturas3);
        bufferIndicePiramide.position(9);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 3, gl.GL_UNSIGNED_BYTE, bufferIndicePiramide);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
    }
}