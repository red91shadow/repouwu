package ec.edu.uce.pa.renders;

import static android.opengl.GLES10.GL_LIGHT0;

import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.PlanoIluminacion;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderIluminacion implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT0;
    private PlanoIluminacion planoIluminacion;
    private PlanoIluminacion planoIluminacion2;
    private PlanoIluminacion planoIluminacion3;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.5f,0.5f,0.5f,1.0f);
        planoIluminacion = new PlanoIluminacion();
        planoIluminacion2 = new PlanoIluminacion();
        planoIluminacion3 = new PlanoIluminacion();
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(gl.GL_DEPTH_TEST);
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
        vIncremento += 0.05F;

        float[] posicionAmbiente = {
//                1,
//                -0.8f,
//                -1.5f,
//                (float) (1.0f+Math.sin(vIncremento))
                0,0,0,0
        };
        float[] posicion2 = {
                0,1
                /*(float) ( Math.cos(vIncremento))*/,
                -2.0f,0.7f
        };
        float[] posicion3 = {
                0.9f, 0f,-2.0f,1f
        };
        float[] color = {
                1.0f,0.2f,0.2f,1.0f
        };
        float[] colorAmbient = {
                0.2f,0.2f,0.2f, 1.0f
        };
        float[] colorDifuso = {
            0.3f,0.8f,0.3f, 1.0f
        };

        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(colorAmbient));
        gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionAmbiente));

//        gl.glLightfv(LUZ0,gl.GL_AMBIENT, Funciones.generarBuffer(colorAmbient));
//        gl.glLightfv(LUZ0,gl.GL_DIFFUSE, Funciones.generarBuffer(color));
//        gl.glLightfv(LUZ0,gl.GL_SPECULAR, Funciones.generarBuffer(colorDifuso));

        gl.glEnable(LUZ0);
        gl.glTranslatef(0.0f,0f,-2.0f);
//        gl.glRotatef(vIncremento,0,1,0);
        planoIluminacion.dibujar(gl);

        gl.glPushMatrix();
        {
            gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(color));
            gl.glLightfv(LUZ1,gl.GL_POSITION, FloatBuffer.wrap(posicion2));

            gl.glRotatef(90,1,0,0);
            planoIluminacion2.dibujar(gl);

        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(colorDifuso));
            gl.glLightfv(LUZ1,gl.GL_POSITION, Funciones.generarBuffer(posicion3));

            gl.glRotatef(90,0f,0,1);
            planoIluminacion3.dibujar(gl);

        }
        gl.glPopMatrix();

    }
}