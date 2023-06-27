package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Elipse;
import ec.edu.uce.pa.geometrias.Linea;

public class RenderElipse implements GLSurfaceView.Renderer {

    private float vIncremento= 1.0f;
    private Elipse linea ;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.8f, 0.8f,0.8f,1.0f);
        linea = new Elipse();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        gl.glViewport(0,0,ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-4, 4, -4,4,3,30);// es la mas usada
        //gl.glOrthof(-5,5,-5,5,1,30);//en projecciòn ortogonal se usa depende a lo que se estè programando
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW); //matriz modelo
        gl.glLoadIdentity();//centro de cordenadas
        gl.glTranslatef(0.0f, 0.0f,-4.0f);
        linea.dibujar(gl);

    }
}