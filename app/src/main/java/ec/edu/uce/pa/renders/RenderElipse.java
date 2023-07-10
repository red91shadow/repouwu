package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Elipse;
import ec.edu.uce.pa.geometrias.Linea;

public class RenderElipse implements GLSurfaceView.Renderer {

    private float vIncremento= 1.0f;
    private Elipse linea ;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.8f, 0.8f,0.8f,1.0f);
        linea = new Elipse(0,0,1,2,50);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float aspectRatio = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio*2, aspectRatio*2, 2, 30);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW); //matriz modelo
        gl.glLoadIdentity();//centro de cordenadas
        gl.glTranslatef(0.0f, 0.0f,-8.0f);
        gl.glRotatef(vIncremento,0,0,1);
        linea.dibujar(gl);
        vIncremento++;

    }
}