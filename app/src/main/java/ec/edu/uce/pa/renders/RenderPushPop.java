package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cubo;
import ec.edu.uce.pa.geometrias.Triangulo;

public class RenderPushPop implements GLSurfaceView.Renderer {
    private float vIncremento;
    private float i = -5;
    private Triangulo triangulo;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_CULL_FACE);
        gl.glCullFace(gl.GL_BACK);
        triangulo = new Triangulo();
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

        gl.glTranslatef(0, 0, -2);
        gl.glScalef(0.5f,0.5f,0.5f);
        gl.glRotatef(vIncremento, 0, 0, 1);
        gl.glPushMatrix();
            gl.glTranslatef(0, -4, -1);
            gl.glRotatef(vIncremento*5, 0, 0, -1);
            gl.glScalef(0.3f, 0.3f, 0.3f);
            triangulo.dibujar(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslatef(0, 4, -1);
            gl.glRotatef(vIncremento*5, 0, 0, 1);
            gl.glScalef(0.3f, 0.3f, 0.3f);
            triangulo.dibujar(gl);
        gl.glPopMatrix();

        gl.glPopMatrix();
            gl.glRotatef(-vIncremento*2,0,0,-1);
            triangulo.dibujar(gl);


        vIncremento += 0.5f;
        i += 0.1f;


    }

}