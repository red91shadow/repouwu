package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.CuboColores;

public class CamaraRender implements GLSurfaceView.Renderer {
    private float vIncremento;
    private float i = -5;
    private CuboColores cubo;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_CULL_FACE);
        gl.glCullFace(gl.GL_BACK);
        cubo = new CuboColores();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-aspectRatio, aspectRatio, -3, 3, 2f, 15);// es la mas usada



    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        GLU.gluLookAt(gl,
                5*(float) Math.cos(i), 5*(float) Math.sin(i), 0,
                0,0,0,
                0,0,1);
        cubo.dibujar(gl);
        gl.glTranslatef(0, 2, 0);
        gl.glScalef(0.3f,0.3f,0.3f);
        cubo.dibujar(gl);

        vIncremento += 0.5f;
        i += 0.1f;
    }

}
