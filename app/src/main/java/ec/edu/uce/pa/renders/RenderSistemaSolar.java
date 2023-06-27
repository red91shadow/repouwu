package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.MainActivity;
import ec.edu.uce.pa.geometrias.CuboColores;
import ec.edu.uce.pa.geometrias.GeoEsfera;
import ec.edu.uce.pa.geometrias.Triangulo;

public class RenderSistemaSolar implements GLSurfaceView.Renderer {
    private float vIncremento;
    private CuboColores esfera;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_CULL_FACE);
        gl.glCullFace(gl.GL_BACK);
        esfera = new CuboColores();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-aspectRatio, aspectRatio, -3, 3, 2f, 15);// es la mas usada

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0, 0, -3);
        gl.glPushMatrix();
        gl.glRotatef(vIncremento, 0, 0, 1);
            gl.glTranslatef(0, -2, -1);
            gl.glRotatef(vIncremento*5, 0, 0, -1);
            gl.glScalef(0.3f, 0.3f, 0.3f);
            esfera.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotatef(vIncremento*1.5f, 0, 0, 1);
        gl.glTranslatef(0, -4, -1);
        gl.glRotatef(vIncremento*3, 0, 0, -1);
        gl.glScalef(0.3f, 0.3f, 0.3f);
        esfera.dibujar(gl);
        gl.glPopMatrix();

        gl.glRotatef(vIncremento*2,0,0,-1);
        esfera.dibujar(gl);

        vIncremento += 0.5f;

    }

}