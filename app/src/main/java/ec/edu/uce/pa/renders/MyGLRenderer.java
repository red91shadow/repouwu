package ec.edu.uce.pa.renders;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private GL10 gl;
    private GLSurfaceView mView;
    private float red, green, blue;
    private Random mRandom = new Random();

    public MyGLRenderer(GLSurfaceView view) {
        mView = view;
        red = mRandom.nextFloat();
        green = mRandom.nextFloat();
        blue = mRandom.nextFloat();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        this.gl = gl;
        gl.glClearColor(red, green, blue, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }

    public void changeBackgroundColor() {
        red = mRandom.nextFloat();
        green = mRandom.nextFloat();
        blue = mRandom.nextFloat();
        gl.glClearColor(red, green, blue, 1.0f);
        mView.requestRender();
    }
}



