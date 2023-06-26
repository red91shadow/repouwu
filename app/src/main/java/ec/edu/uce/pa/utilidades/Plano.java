package ec.edu.uce.pa.utilidades;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Plano {
    private FloatBuffer bufferVertices, bufferColores;
    private static int byteFlotante = 4;
    private final static int comPorVertice =3, comPorColores = 4;
    private ByteBuffer bufferIndicesPlano;
    private int nVerticesGeneral, nColoresG, nIndicesG;
    private float[] coloresU, auxC;
    private byte[] auxI;

    public float[] coloresUnico(float R,float G, float B,int n){
        this.coloresU = new float[n*comPorColores];
        for(int i = 0; i<this.coloresU.length; i++){
            coloresU[i] = R;
            coloresU[i+1] = G;
            coloresU[i+2]  = B;
            coloresU[i+3]  = 1.0f;
            i +=3;
        }
        return coloresU;
    }
    public Plano(float R, float G, float B, float[] vertices, byte[] indices){
        nVerticesGeneral = vertices.length;

        ByteBuffer buffer = ByteBuffer.allocateDirect(nVerticesGeneral*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);
        bufferVertices.position(0);

        auxC = coloresUnico(R,G,B,nVerticesGeneral/comPorVertice);
        nColoresG = auxC.length;
        buffer = ByteBuffer.allocateDirect(nColoresG*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(auxC);

        auxI =indices;
        nIndicesG = auxI.length;
        bufferIndicesPlano = ByteBuffer.allocateDirect(nIndicesG);
        bufferIndicesPlano.order(ByteOrder.nativeOrder());
        bufferIndicesPlano.put(auxI);
        bufferIndicesPlano.position(0);
    }

    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(comPorVertice,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);
        bufferColores.position(0);
        gl.glColorPointer(comPorColores,gl.GL_FLOAT,0,bufferColores);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN,nIndicesG,gl.GL_UNSIGNED_BYTE,bufferIndicesPlano);
        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
