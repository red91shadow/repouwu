package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class ToroideDona {

    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

     private int franjas;   //LATITUD   HORIZONTAL
     private int cortes; // LONGITUD VERTICAL

    //LATITUD
    private float radioR;
    private float radior; // ANGULO ROTACION
    private float atachamiento;

    private void creaPlaneta(){
        float [] vertices ;

        float colores [];

        float colorIncremento = 0f;

        float red = 0.0f;
        float green = 1.0f;
        float blue = 1.0f;

        int iVertice = 0;
        int iColor = 0;

        colorIncremento += 1.0 / (float)franjas;

        vertices = new float[3 * ((cortes * 2 + 2) * franjas)];
        colores = new float[4 * ((cortes * 2 + 2) * franjas)];

        int i, j;


        // LATITUDES
        for(i = 0; i < franjas; i++)  {
            //DE 0 A 360
            //Phi   --> LATITUD
            //Theta --> LONGITUD

            //ANGULO DEL 1RO CIRCULO
            float phi0 = (2*(float)Math.PI) * ((i + 0) * (1.0f/(franjas)) - 0.5f);
            float cosPhi0 = (float)Math.cos(phi0);
            float sinPhi0 = (float)Math.sin(phi0);

            //ANGULO DEL 2RO CIRCULO
            float phi1 = (2*(float)Math.PI) * ((i + 1 ) * (1.0f/(franjas)) - 0.5f);
            float cosPhi1 = (float)Math.cos(phi1);
            float sinPhi1 = (float)Math.sin(phi1);

            float cosTheta, sinTheta;

            //LONGITUDES
            for(j = 0; j < cortes; j++) {
                float theta = (float)(-2.0f * Math.PI * j * (1.0/(cortes -1)));  //se usa una escal solo para normalizar, se da una especio de escala
                cosTheta = (float)Math.cos(theta);
                sinTheta = (float)Math.sin(theta);

                //DUPLAS PARA EL TOROIDE
                // TRIANG... 1
                vertices[iVertice+0] = (radioR + (radior *cosTheta)) * cosPhi0; //x
                vertices[iVertice+1] = (radioR +(radior *cosTheta))* sinPhi0; //y
                vertices[iVertice+2] = radior *sinTheta; //z
                // Triangulo 2
                vertices[iVertice+3] = (radioR + (radior *cosTheta)) * cosPhi1;  //x'
                vertices[iVertice+4] = (radioR +(radior *cosTheta))* sinPhi1; //y'
                vertices[iVertice+5] = radior *sinTheta;; //z'

                colores[iColor+0] = red;
                colores[iColor+1] = green;
                colores[iColor+2] = blue;
                colores[iColor+3] = 1.0f;

                colores[iColor+4] = red;
                colores[iColor+5] = green;
                colores[iColor+6] = blue;
                colores[iColor+7] = 1.0f;

                iColor += 2*4; //RGB Y ALPHA

                iVertice += 2*3; //VERTICES XYZ
            }

            red -= colorIncremento;
            green -= colorIncremento;
            blue += colorIncremento;

            //TRIANGULOS DEGENERADOS PARA EL TOROIDE
            vertices[iVertice+0] = vertices[iVertice+3];
            vertices[iVertice+3] = vertices[iVertice-3];
            vertices[iVertice+1] = vertices[iVertice+4];
            vertices[iVertice+4] = vertices[iVertice-2];
            vertices[iVertice+2] = vertices[iVertice+5];
            vertices[iVertice+5] = vertices[iVertice-1];
        }

        ByteBuffer bbVertices = ByteBuffer.allocateDirect(vertices.length * 4) ;
        bbVertices.order(ByteOrder.nativeOrder());
        bufferVertices = bbVertices.asFloatBuffer();
        bufferVertices.put(vertices);
        bufferVertices.position(0);

        ByteBuffer bbColores = ByteBuffer.allocateDirect(colores.length * 4) ;
        bbColores.order(ByteOrder.nativeOrder());
        bufferColores = bbColores.asFloatBuffer();
        bufferColores.put(colores);
        bufferColores.position(0);
    }

    //LATITUD, LONGITUD, RADIO, ANGULO, ATACHAMIENTO
    public ToroideDona(int vfranjas, int vcortes, float vradioR, float vradior, float vatach){
        this.franjas = vfranjas;
        this.cortes = vcortes;
        this.radioR = vradioR;
        this.radior = vradior;
        this.atachamiento = vatach;
        creaPlaneta();
    }

        public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(3,gl.GL_FLOAT,0,bufferVertices);
        gl.glColorPointer(4,gl.GL_FLOAT,0,bufferColores);
        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP,0,2*franjas*cortes);
        gl.glFrontFace(gl.GL_CCW);
    }
}
