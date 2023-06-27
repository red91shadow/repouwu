package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.MainActivity;
import ec.edu.uce.pa.geometrias.Prisma;

public class RenderPrisma implements GLSurfaceView.Renderer {
    private float vIncremento;
    private Prisma prisma;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        prisma = new Prisma(0.5f,1,5);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -3.0f, 3.0f, 2.0f, 15.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0,0 ,-4 );
        gl.glRotatef(vIncremento*2,0,1,1);
        prisma.dibujar(gl);

        vIncremento += 0.5f;
    }
}