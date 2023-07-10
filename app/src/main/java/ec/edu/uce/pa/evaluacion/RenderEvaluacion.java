package ec.edu.uce.pa.evaluacion;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class RenderEvaluacion implements GLSurfaceView.Renderer {
    private float vIncremento;
    private CuboColores cubo;
    private Prisma prisma;
    private Cono cono;
    private GeoEsfera esfera;
    private Plano plano;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_CULL_FACE);

        esfera = new GeoEsfera(25,25,1f,1f);
        plano= new Plano(0.5f,0,0,new float[]{
                -0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f
        },new byte[]{
                0, 1, 2, 2, 3, 0
        });
        cubo= new CuboColores();
        prisma= new Prisma(1,2,3);
        cono = new Cono(1,2,7);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio*2, aspectRatio*2, 2f, 15);// es la mas usada

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);
        gl.glLoadIdentity();
        //pone figuras en prespectiva dentro del frustrum
        gl.glTranslatef(0, -1, -10);
        gl.glRotatef(20,1,0,0);
        gl.glScalef(1.8f,1.8f,1.8f);
        gl.glRotatef(vIncremento*0.5f,0,-1,0);

        //dibujar planos

        gl.glPushMatrix();
        gl.glTranslatef(0, -5, 0);
        gl.glRotatef(-90,1,0,0);
        gl.glScalef(6,6,6);
        plano.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0, 0, 0);
        gl.glRotatef(-90,0,1,0);
        gl.glScalef(5,3,0.001f);
        cubo.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0, 0, 0);
        gl.glRotatef(0,0,1,0);
        gl.glScalef(5,3,0.001f);
        cubo.dibujar(gl);
        gl.glPopMatrix();

        //dibujar figuras
        gl.glPushMatrix();
        gl.glTranslatef(2, 0, 2);
        gl.glRotatef(vIncremento,0,1,0);
        gl.glScalef(0.5f,0.5f,0.5f);
        cubo.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(-2, 0, 2);
        gl.glRotatef(vIncremento,0,1,0);
        gl.glScalef(0.5f,0.5f,0.5f);
        cono.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(2, 0, -2);
        gl.glRotatef(vIncremento,0,1,0);
        gl.glScalef(0.5f,0.5f,0.5f);
        esfera.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(-2, 0, -2);
        gl.glRotatef(vIncremento,0,1,0);
        gl.glScalef(0.5f,0.5f,0.5f);
        prisma.dibujar(gl);
        gl.glPopMatrix();

        vIncremento += 2f;

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);


    }

}