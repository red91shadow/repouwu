package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Linea;

public class RenderLinea implements GLSurfaceView.Renderer {

    private float vIncremento = 0.1f;
    private Linea linea;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        linea = new Linea();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        gl.glViewport(800, 800, ancho / 2, alto / 2);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-5, 5, -5, 5, 3, 30);

        //gl.glOrthof(-5, 5, -5, 5, 1, 30);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -2.0f);
        gl.glRotatef(vIncremento, 0, 0, 1);

        linea.dibujar(gl);

        gl.glTranslatef(0.0f, 0.0f, -0.0f);
        linea.dibujar(gl);

        vIncremento += 0.1f;

    }
}
