package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Linea;

public class RenderLinea implements GLSurfaceView.Renderer {

    private float vIncremento= 1.0f;
    private Linea linea ;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.8f, 0.8f,0.8f,1.0f);
        linea = new Linea();
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
        gl.glTranslatef(0.0f, 0.0f,-4.0f); //la posiciòn donde se dibujarà
//        gl.glScalef(1,2,1);//1 no escalo, 2 escalo el doble, en este caso se escala en el eje y
//        vIncremento += 0.1;
//        gl.glRotatef(vIncremento, 0,0,1);//rota el valor de incremento  grados en el eje z
        linea.dibujar(gl);//dibuja una vez el punto
        gl.glTranslatef(0.0f, 0.0f,-1.0f);//se traslada en el eje z
        linea.dibujar(gl); //dibuja una segunda vez el punto


    }
}