package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Camion;
import ec.edu.uce.pa.geometrias.Linea;
import ec.edu.uce.pa.geometrias.Punto;

public class RenderCamion implements GLSurfaceView.Renderer {

    private float vIncremento;
    private Linea linea;
    private Camion camion;
    private Punto punto;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        punto = new Punto();
        linea = new Linea();
        camion = new Camion();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto/(float) ancho);
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -5, 5, 3f, 30);

        //gl.glOrthof(-5, 5, -5, 5, 1, 30);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef((float) Math.sin(vIncremento),0,0);

        gl.glTranslatef(0.0f, 0.0f, -6.0f);

        camion.dibujar(gl);
        linea.dibujar(gl);

        gl.glTranslatef(1f, -0.5f, 0.0f);
        punto.dibujar(gl);
        gl.glTranslatef(-3.0f, 0.0f, 0.0f);
        punto.dibujar(gl);

        vIncremento += 0.01f;
    }
}