package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class RenderColores implements GLSurfaceView.Renderer {

    private double vIncremento=1;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //rgb
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glClearColor(1,0,0,1);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glClearColor((float) Math.random(), (float) Math.random(), (float) Math.random(),0.1f);
        vIncremento+=0.001;
    }
}
