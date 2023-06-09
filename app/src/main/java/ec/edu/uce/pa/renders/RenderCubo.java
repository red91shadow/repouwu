package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cubo;
import ec.edu.uce.pa.geometrias.Triangulo;

public class RenderCubo implements GLSurfaceView.Renderer {
    private float vIncremento;
    private Cubo cubo;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        cubo = new Cubo();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -3, 3, 2f, 15);// es la mas usada
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -5.0f);
        gl.glRotatef(vIncremento, 1, 1, 0.5f);
        cubo.dibujar(gl);
        vIncremento += 1f;

    }
}