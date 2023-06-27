package ec.edu.uce.pa.geometrias;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class Cono {

    private static FloatBuffer bufferVertices;
    private static FloatBuffer bufferColores;
    private final static int comPorVertices = 3;
    private final static int comPorColor = 4;


    private static int segmentos;

    private ArrayList<Float> verticesAL = new ArrayList<>();
    ArrayList<Float> coloresAL = new ArrayList<>();



    public Cono(float RADIUS, float height, int segmentos){

        this.segmentos = segmentos;

        float posInicialx =0;
        float posInicialy =0;
        float posInicialz =0;

        //ALGORITMO RUEDA---------------------------------------------------------------
        //DATOS DE VERTICES
        float x = 0;
        float z= 0;
        float y= 0; //Altura del cono

        float tmpx = 0, tmpy = 0, tmpz = 0;

        boolean aux = false;
        byte paso = 0;

        // 1 PUNTO CENTRAL  (VERTICE)
        verticesAL.add(0f); verticesAL.add(height);verticesAL.add(0f);
        // Cicunferencia
        for (int i = 0; i <= segmentos; i++) {
            if(paso ==2) aux =true;
            if(aux){
                verticesAL.add(0f); verticesAL.add(height);verticesAL.add(0f);
                verticesAL.add(tmpx);  verticesAL.add(0f);verticesAL.add(tmpz);
                paso =1;
                aux =false;
            }
            paso ++;

            float theta = (float) ((2.0f * Math.PI * i )/ segmentos);
            x = RADIUS * (float) Math.cos(theta);
            y = 0;
            z = RADIUS * (float) Math.sin(theta);

            tmpx = x; tmpy = y; tmpz =z;
            verticesAL.add(x + posInicialx);  verticesAL.add(0f);verticesAL.add(z + posInicialz);
        }

        //Asignar ArrayList a vertices []
        float [] vertices = new float[verticesAL.size()];
        for (int i=0;i<verticesAL.size();i++) {
            vertices[i] =  verticesAL.get(i);

        }

        bufferVertices = Funciones.generarBuffer(vertices);
        //bufferColores = Funciones.generarBuffer(colores);

    }

    public static void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

//        bufferColores.position(0);
//        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
//        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glColor4f(1,0,0,1);//ROJO
        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0, segmentos*3 );



        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

    }
}