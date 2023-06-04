package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class RenderesColores implements GLSurfaceView.Renderer {
    private float vIncremento = 1;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        //RGBA
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        if (ancho < alto)
            gl.glClearColor(0.0f, 1.0f, 0.0f, 1);
        else
            gl.glClearColor(1.0f, 0.0f, 0.0f, 1);
    }

    @Override

    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glClearColor((float) Math.random(), (float) Math.random(), (float) Math.random(), 0.1f);
    }

}

