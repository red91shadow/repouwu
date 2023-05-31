package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;
import android.widget.Toast;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Punto;

public class RenderPuntos implements GLSurfaceView.Renderer {

    private float vIncremento=0.0f;
    Punto punto;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //rgb
        gl.glClearColor(0.5f,0.5f,0.5f,1.0f);
        punto = new Punto();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {

//        gl.glViewport(400,400,ancho/2,alto/2);
        gl.glViewport(0,0,ancho,alto);
        gl.glMatrixMode(GL10.GL_PROJECTION);
//        gl.glFrustumf(-5,5,-5,5,3,30);
        gl.glOrthof(-5,5,-5,5,1,30);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0,0,-2);
        gl.glRotatef(vIncremento,0,0,1);
        punto.dibujar(gl);
        gl.glTranslatef(0,0,-2);
        gl.glRotatef(vIncremento,0,0,1);
        punto.dibujar(gl);
        vIncremento+= 0.1f;
    }
}
