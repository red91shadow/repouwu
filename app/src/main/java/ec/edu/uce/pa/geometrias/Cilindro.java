package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;
import ec.edu.uce.pa.utilidades.Plano;

public class Cilindro {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private int numSegmentos;
    private float altura;
    private float radio;

    Plano[] carasLaterales;
    Plano top;
    Plano bottom;

    public Cilindro(int numSegmentos, float altura, float radio) {
        this.numSegmentos = numSegmentos;
        this.altura = altura;
        this.radio = radio;

        ArrayList<Float> verticesTop = new ArrayList<>();
        ArrayList<Float> verticesBottom = new ArrayList<>();

        float[] temp=Funciones.calculatePolygonCoordinates(numSegmentos,radio,0);

        for (float f:
             temp) {
            verticesTop.add(f);
        }
        temp=Funciones.calculatePolygonCoordinates(numSegmentos,radio,altura);

        for (float f:
                temp) {
            verticesBottom.add(f);
        }


        float[] verTop = new float[verticesTop.size()];
        for (int i = 0; i < verticesTop.size(); i++) {
            verTop[i]=verticesTop.get(i);
        }
        float[] verBottom = new float[verticesBottom.size()];
        for (int i = 0; i < verticesBottom.size(); i++) {
            verBottom[i] = verticesBottom.get(i);
        }

        Funciones.generarBuffer(verTop);
        Funciones.generarBuffer(verBottom);

//////////////////////////////////////////////////////////////////
        float[] coloresTop = new float[verTop.length * 4 / 3];
        for (int i = 0; i < coloresTop.length; i += 4) {
            coloresTop[i] = 1.0f;
            coloresTop[i + 1] = 1.0f;
            coloresTop[i + 2] = 0.0f;
            coloresTop[i + 3] = 1.0f;
        }
        ByteBuffer buffer;
        buffer = ByteBuffer.allocateDirect(coloresTop.length * 4);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(coloresTop);
        bufferColores.position(0);

        float[] coloresBottom = new float[verBottom.length * 4 / 3];
        for (int i = 0; i < coloresBottom.length; i += 4) {
            coloresBottom[i] = 1.0f;
            coloresBottom[i + 1] = 1.0f;
            coloresBottom[i + 2] = 0.0f;
            coloresBottom[i + 3] = 1.0f;
        }

        buffer = ByteBuffer.allocateDirect(coloresBottom.length * 4);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(coloresBottom);
        bufferColores.position(0);
////////////////////////////////////////////////////

    }

    public void dibujar(GL10 gl) {
    }
}
