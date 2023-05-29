package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class RenderColores implements GLSurfaceView.Renderer {

    private double vIncremento;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //rgb
        gl.glClearColor(1,0,0,1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        vIncremento+=0.001;
//        gl.glClearColor(1F, (float) vIncremento,0,1);

    }
}
