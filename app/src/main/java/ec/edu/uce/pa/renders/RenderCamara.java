package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.MainActivity;
import ec.edu.uce.pa.geometrias.CuboColores;

public class RenderCamara implements GLSurfaceView.Renderer {
    private CuboColores prisma;
    private float radio = 0.5f;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        prisma = new CuboColores();
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
        gl.glTranslatef(MainActivity.ejeX, MainActivity.ejeY, MainActivity.ejeZ);

        if (MainActivity.ejeCamara) {
            if (MainActivity.rotar) {
                GLU.gluLookAt(gl,
                        radio * (float) Math.sin(MainActivity.iRotacionC), 0, radio * (float) Math.cos(MainActivity.iRotacionC),
                        0, 0, 0,
                        0, 1, 0
                );
                prisma.dibujar(gl);
            } else {
                prisma.dibujar(gl);
            }

        } else {
            if (MainActivity.rotar) {
                GLU.gluLookAt(gl,
                        0, radio * (float) Math.sin(MainActivity.iRotacionC), radio * (float) Math.cos(MainActivity.iRotacionC),
                        0, 0, 0,
                        0, 1, 0
                );
                prisma.dibujar(gl);
                System.out.println("1");
            } else {
                prisma.dibujar(gl);
            }
        }
    }
}