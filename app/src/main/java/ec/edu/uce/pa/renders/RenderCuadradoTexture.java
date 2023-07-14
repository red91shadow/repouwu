package ec.edu.uce.pa.renders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias.CuadradoTexture;
import ec.edu.uce.pa.geometrias.CuboColores;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderCuadradoTexture implements GLSurfaceView.Renderer {

    private float vIncremento = 0.0f;
    private int [] arrayTexturas = new int[1];
    private Context context;
    public RenderCuadradoTexture(Context contexto){
        this.context = contexto;
    }
    private CuadradoTexture cuadradoTexture;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        cuadradoTexture = new CuadradoTexture();
        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glGenTextures(1, arrayTexturas, 0);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.screenshot_2023_05_14_202256);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D,0,bitmap,0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER,gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        bitmap.recycle();
        Funciones.bitmapimagen(gl,context,R.drawable.screenshot_2023_05_14_202256,arrayTexturas);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float relacionAspecto = (float) ancho / (float) alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 50);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV,gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
        gl.glMatrixMode(gl.GL_MODELVIEW);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glTranslatef(0, 0, -3);
        gl.glRotatef(-vIncremento, 0, 1, 0);
        cuadradoTexture.dibujar(gl);
        vIncremento += 0.5f;
    }
}
