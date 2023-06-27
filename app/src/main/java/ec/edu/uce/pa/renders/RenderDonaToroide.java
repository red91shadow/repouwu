package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.ToroideDona;

public class RenderDonaToroide implements GLSurfaceView.Renderer {

    private float tras=0.0f;
    private float rotacion = 0.0f;
    private ToroideDona DonaToroide;

    public RenderDonaToroide() {
        DonaToroide = new ToroideDona(400,400,1.5f,0.5f,1.7f);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.1f, 0.1f, 0.1f, 0.1f);
        gl.glEnable(gl.GL_DEPTH_TEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int heigth) {
        gl.glViewport(0, 0, width, heigth);
        float relacionAspecto = (float) width / heigth;
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 20);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);
        tras +=0.01f;
        rotacion +=1.5f;
        gl.glTranslatef (0, (float)Math.sin(tras)*2, -10f);
        gl.glRotatef(rotacion,1,0,1);

        DonaToroide.dibujar(gl);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

    }
}