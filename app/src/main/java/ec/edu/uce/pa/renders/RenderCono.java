package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cono;

public class RenderCono implements GLSurfaceView.Renderer {
    private float vIncremento;
    private Cono cilindro;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        cilindro = new Cono(1,2,50);
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
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -5.0f);
        gl.glScalef(0.5f,0.5f,0.5f);
        gl.glRotatef(vIncremento,0,1,1);
        vIncremento++;
        cilindro.dibujar(gl);
    }
}
