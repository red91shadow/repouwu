package ec.edu.uce.pa.renders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias.CuadradoTextura;

public class RenderCuadradoTextura implements GLSurfaceView.Renderer {
    private  float vIncremento=0;
    private CuadradoTextura cuadradoTextura ;
    private int[] arrayTexturas = new int[1];
    private Context context;
    public RenderCuadradoTextura (Context contexto){
        this.context = contexto;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.5f, 0.5f,0.5f,1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        cuadradoTextura = new CuadradoTextura();

        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glGenTextures(1,arrayTexturas,0);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.textura2, options);

        GLUtils.texImage2D(gl.GL_TEXTURE_2D,0,bitmap,0);

        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
        gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        bitmap.recycle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) ancho / (float) alto);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio * 2, aspectRatio * 2, 1, 30);
      //  gl.glTexEnvf(gl.GL_TEXTURE_ENV,gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);// es la mas usada
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT| gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f,-5.0f);//la posiciòn donde se dibujarà
        gl.glRotatef(vIncremento,0,1,0);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
        cuadradoTextura.dibujar(gl);

        vIncremento++;

    }
}