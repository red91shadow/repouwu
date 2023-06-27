package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

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
        carasLaterales = new Plano[numSegmentos];

////////////////////////////////////////////////////////////////
        //crea los vertices de las caras superior e inferior
        ArrayList<Float> verticesTop = new ArrayList<>();
        ArrayList<Float> verticesBottom = new ArrayList<>();

        float[] temptop = Funciones.calculatePolygonCoordinates(numSegmentos, radio, 0);
        Funciones.generarBuffer(temptop);
//        for (float f :
//                temptop) {
//            verticesTop.add(f);
//        }

        float[] tempbottom = Funciones.calculatePolygonCoordinates(numSegmentos, radio, altura);
        Funciones.generarBuffer(tempbottom);
//        for (float f :
//                tempbottom) {
//            verticesBottom.add(f);
//        }
////////////////////////////////////////////////////////////
        //transforma los arraylist en matrices float
//        float[] verTop = new float[verticesTop.size()];
//        for (int i = 0; i < verticesTop.size(); i++) {
//            verTop[i] = verticesTop.get(i);
//        }
//        float[] verBottom = new float[verticesBottom.size()];
//        for (int i = 0; i < verticesBottom.size(); i++) {
//            verBottom[i] = verticesBottom.get(i);
//        }

//        Funciones.generarBuffer(verTop);
//        Funciones.generarBuffer(verBottom);

//////////////////////////////////////////////////////////////////
        byte[] indices = new byte[(numSegmentos - 2) * 3];
        byte tempo1 = 1;
        for (int i = 0; i < indices.length; i += 3) {
            indices[i] = 0;
            indices[i + 1] = tempo1++;
            indices[i + 2] = tempo1;
        }
////////////////////////////////////////////////////
        top = new Plano(0.5f, 0, 0, temptop, indices);
        bottom = new Plano(0.8f, 0, 1, tempbottom, indices);
////////////////////////////////////////////////////
            float[] tempLat = new float[12];
        for (int i = 0; i < carasLaterales.length; i++) {
            for (int j = 0; j < 6; j++) {
                //todo arreglar el indice
                tempLat[j] = temptop[i +j];
                tempLat[j + 6] = tempbottom[i + j];
            }
            System.out.println(Arrays.toString(tempLat));
            carasLaterales[i] = new Plano(0.6f, 0, 0, tempLat, new byte[]{0, 2, 3, 1});
        }

    }

    public void dibujarCilindro(GL10 gl) {
        top.dibujar(gl);
        bottom.dibujar(gl);
        for (Plano plano :
                carasLaterales) {
            plano.dibujar(gl);
        }
    }
}
