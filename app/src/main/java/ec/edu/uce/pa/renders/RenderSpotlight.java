package ec.edu.uce.pa.renders;

import static android.opengl.GLES10.GL_LIGHT0;
import static android.opengl.GLES10.GL_LIGHT2;

import static javax.microedition.khronos.opengles.GL10.GL_LIGHT1;

import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cono;
import ec.edu.uce.pa.geometrias.PlanoIluminacion;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderSpotlight implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT1;
    private final static int LUZ2 = GL_LIGHT2;
    private PlanoIluminacion planoIluminacion;
    private PlanoIluminacion planoIluminacion2;
    private PlanoIluminacion planoIluminacion3;

    private float[] spotDir1;
    private float[] spotDir2;
    float[] posicionLuz0 = {
            -0.8f, 0.8f, -3.0f, 1.0f
    };
    float[] posicionLuz1 = {
            0, 0, -3.0f, 1.0f
    };
    float[] posicionLuz2 = {0f, -0.5f, -3.0f, 1f};
    float[] LuzBlanco = {
            0.8f, 0.8f, 0.8f, 1.0f
    };
    float[] LuzRojo = {
            1.0f, 0.0f, 0.0f, 1.0f
    };
    float[] LuzVerde = {
            0.0f, 1.0f, 0.0f, 1.0f
    };
    float[] LuzAmarillo = {
            1.0f, 0.5f, 0.0f, 1.0f
    };

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_LIGHTING);
        planoIluminacion = new PlanoIluminacion();
        planoIluminacion2 = new PlanoIluminacion();
        planoIluminacion3 = new PlanoIluminacion();

        spotDir1 = new float[]{
                0, 0, -1
        };
        spotDir2 = new float[]{
                0, -1, 0
        };

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) ancho / (float) alto);
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio * 2, aspectRatio * 2, 1, 15);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glLightfv(LUZ0, gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ0, gl.GL_DIFFUSE, Funciones.generarBuffer(LuzBlanco));
        // gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));


        gl.glLightfv(LUZ1, gl.GL_POSITION, Funciones.generarBuffer(posicionLuz1));
        gl.glLightfv(LUZ1, gl.GL_DIFFUSE, Funciones.generarBuffer(LuzAmarillo));
        // gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));


        gl.glLightfv(LUZ2, gl.GL_POSITION, Funciones.generarBuffer(posicionLuz2));
        gl.glLightfv(LUZ2, gl.GL_DIFFUSE, Funciones.generarBuffer(LuzVerde));
        // gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ2, gl.GL_SPOT_DIRECTION, FloatBuffer.wrap(spotDir2));
        gl.glLightf(LUZ2, gl.GL_SPOT_CUTOFF, 100);
        gl.glLightf(LUZ2, gl.GL_SPOT_EXPONENT, (float) (5 /*+ 2 * Math.sin(vIncremento))*/));

        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(LUZ0);
        gl.glEnable(LUZ1);
        gl.glEnable(LUZ2);

        gl.glTranslatef(0.0f, 0.5f+2*(float) Math.cos(vIncremento), -3.0f);
        gl.glPushMatrix();
        {
            spotDir1 = new float[]{0, 0, -1};
            gl.glLightfv(LUZ1, gl.GL_SPOT_DIRECTION, FloatBuffer.wrap(spotDir1));
            gl.glLightf(LUZ1, gl.GL_SPOT_CUTOFF, 25);
            gl.glLightf(LUZ1, gl.GL_SPOT_EXPONENT, 1);
            gl.glRotatef(90, 1, 0, 0);
            gl.glScalef(2,2,2);
            planoIluminacion.dibujar(gl);

        }
        gl.glPopMatrix();
        gl.glScalef(2,2,2);

        planoIluminacion.dibujar(gl);

        vIncremento += 0.05F;
    }
}