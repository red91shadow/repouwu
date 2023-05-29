package ec.edu.uce.pa.activity;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import ec.edu.uce.pa.renders.MyGLRenderer;

public class MyGLSurfaceView extends GLSurfaceView {

    private MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        // Crea un objeto OpenGL ES 2.0
        setEGLContextClientVersion(2);

        // Asigna el renderizador
        mRenderer = new MyGLRenderer(this);
        setRenderer(mRenderer);

        // Solicita actualizaciones continuas de la vista
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mRenderer.changeBackgroundColor();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
