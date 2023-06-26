package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cilindro;

public class RenderCilindro implements GLSurfaceView.Renderer {
    private float vIncremento;
    private Cilindro cilindro;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        cilindro = new Cilindro(10, 2f, 2f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float aspectRatio = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-aspectRatio, aspectRatio, -3, 3, 2, 30);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 1.0f;
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -10.0f);
        cilindro.dibujar(gl);
    }
}
