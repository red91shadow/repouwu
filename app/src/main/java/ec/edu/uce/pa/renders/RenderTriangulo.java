package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Linea;
import ec.edu.uce.pa.geometrias.Triangulo;

public class RenderTriangulo implements GLSurfaceView.Renderer {
    private  float vIncremento=0;

    private Triangulo triangulo ;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.5f, 0.5f,0.5f,1.0f);
        triangulo = new Triangulo();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio=  ((float)alto/(float)ancho);
        gl.glViewport(0,0,ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -6,6,3,30);// es la mas usada
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW); //matriz modelo
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f,-4.0f);//la posiciòn donde se dibujarà
        gl.glRotatef(vIncremento,0,0,1);
        triangulo.dibujar(gl);
        vIncremento++;

    }
}