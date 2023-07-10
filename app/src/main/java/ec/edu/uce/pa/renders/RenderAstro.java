package ec.edu.uce.pa.renders;

import static android.opengl.GLES10.GL_LIGHT0;
import static android.opengl.GLES10.GL_LIGHT2;
import static javax.microedition.khronos.opengles.GL10.GL_LIGHT1;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Astro;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderAstro implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT1;
    private final static int LUZ2 = GL_LIGHT2;

    private Astro astro;


    private float [] materialNegro = {0.3f,0.3f,0.3f,1.0f};
    private float [] materialVerde= {0.0f,1.0f,0.0f,1.0f};
    private float [] materialRojo = {1.0f,0.0f,0.0f,1.0f};
    private float [] materialAzul = {0.0f,0.0f,1.0f,1.0f};
    private float [] materialAmarillo= {1.0f,1.0f,0.0f,1.0f};

    private float [] materialBlancoMed = {0.50f,0.50f,0.50f,1.0f};
    private float [] materialVerdeMed= {0f,0.5f,0.0f,1.0f};
    private float [] materialRojoMed = {0.50f,0.0f,0.0f,1.0f};
    private float [] materialAzulMed = {0.0f,0.0f,0.50f,1.0f};
    private float [] materialAmarilloMed= {0.50f,0.50f,0.0f,1.0f};

    private float[] spotDir1 ;
    private float[] spotDir2 ;
    float[] posicionLuz0 = {
            0f,0f,0f,1.0f
    };
    float[] posicionLuz1 = {
            0, -1,0,700.0f
    };
    float[] posicionLuz2 = {
            0f, 0.0f,-3.0f,1f
    };
    float[] LuzBlanco = {
            0.8f,0.8f,0.8f,1.0f
    };
    float[] LuzRojo = {
            1.0f,0.0f,0.0f,1.0f
    };
    float[] LuzVerde = {
            0.0f,1.0f,0.0f,1.0f
    };
    float[] LuzAmarillo = {
            1.0f,1.0f,0.0f,1.0f
    };
    float[] LuzAzul = {
            0.0f,0.0f,1.0f,1.0f
    };
    private float [] LuzBlancoMed = {0.50f,0.50f,0.50f,1.0f};
    private float [] LuzVerdeMed= {.0f,0.50f,0.0f,1.0f};
    private float [] LuzRojoMed = {0.50f,0.0f,0.0f,1.0f};
    private float [] LuzAzulMed = {0.0f,0.0f,0.50f,1.0f};
    private float [] LuzAmarilloMed= {0.50f,0.50f,0.0f,1.0f};


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.2f,0.2f,0.2f,1.0f);
        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(gl.GL_DEPTH_TEST);
        astro = new Astro(50,50,1f,1f);
        spotDir1 = new float []{
                0,0,-1
        };
        spotDir2 = new float []{
                0,-1,0
        };

    }
    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0,0,ancho,alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio,aspectRatio,-aspectRatio*2,aspectRatio*2,1,30);

    }
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT|gl.GL_DEPTH_BUFFER_BIT );
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
       gl.glLightfv(LUZ0,gl.GL_AMBIENT, Funciones.generarBuffer(LuzBlanco));
        gl.glLightfv(LUZ0,gl.GL_DIFFUSE, Funciones.generarBuffer(LuzBlanco));
        gl.glLightfv(LUZ0,gl.GL_SPECULAR, Funciones.generarBuffer(LuzBlanco));
        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(LUZ0);

//        gl.glLightfv(LUZ1,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
//        gl.glLightfv(LUZ1,gl.GL_DIFFUSE, Funciones.generarBuffer(LuzVerdeMed));
//
//        gl.glLightfv(LUZ2,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
//        gl.glLightfv(LUZ2,gl.GL_DIFFUSE, Funciones.generarBuffer(LuzBlancoMed));



//        gl.glEnable(LUZ2);
//        gl.glEnable(LUZ1);

//        gl.glRotatef(30,1,0,0);
        gl.glTranslatef(0.0f,0f,-7.0f);
        gl.glPushMatrix();
        {
            gl.glRotatef(vIncremento*20,0,1,0);

            gl.glTranslatef(0,0,-3);
            gl.glScalef(0.5f,0.5f,0.5f);
            gl.glPushMatrix();
            {
                gl.glRotatef(vIncremento*40,0,-1,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialNegro,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_DIFFUSE, materialNegro,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_SPECULAR, materialNegro,0);
                gl.glMaterialf(gl.GL_FRONT_AND_BACK, gl.GL_SHININESS, 0);

                gl.glTranslatef(0,0,-2);
                gl.glScalef(0.5f,0.5f,0.5f);

                astro.dibujar(gl);

            }
            gl.glPopMatrix();
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT,materialVerdeMed,0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_DIFFUSE,materialVerdeMed,0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_SPECULAR,materialVerde,0);
            gl.glMaterialf(gl.GL_FRONT_AND_BACK, gl.GL_SHININESS, 1);

            astro.dibujar(gl);

        }
        gl.glPopMatrix();
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT,materialAmarillo,0);
        gl.glMaterialf(gl.GL_FRONT_AND_BACK, gl.GL_SHININESS,1);

        astro.dibujar(gl);

        vIncremento += 3f;
    }
}