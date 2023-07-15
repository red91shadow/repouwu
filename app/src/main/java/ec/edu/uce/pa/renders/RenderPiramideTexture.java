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
import ec.edu.uce.pa.geometrias.CuadradoTexture;
import ec.edu.uce.pa.geometrias.TrianguloTexture;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderPiramideTexture implements GLSurfaceView.Renderer {
    private float vIncremento = 0.0f;
    private int [] arrayTexturas = new int[5];
    private Context context;
    public RenderPiramideTexture(Context contexto){
        this.context = contexto;
    }
    private CuadradoTexture cuadradoTexture;
    private TrianguloTexture trianguloTexture;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glGenTextures(5, arrayTexturas, 0);
        cuadradoTexture = new CuadradoTexture();
        trianguloTexture = new TrianguloTexture();
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float relacionAspecto = (float) ancho / (float) alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-relacionAspecto, relacionAspecto, -relacionAspecto * 2, relacionAspecto * 2, 1, 30);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV,gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        Bitmap bitmap;
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0, 1.5f, -3);
        gl.glRotatef(-vIncremento, 0, 2, 0);

        gl.glPushMatrix();
        Funciones.bitmapimagen(gl,context,R.drawable.cara1,arrayTexturas,0);
        trianguloTexture.dibujarCara0(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        Funciones.bitmapimagen(gl,context, R.drawable.cara2,arrayTexturas,1);
        trianguloTexture.dibujarCara1(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
//        Funciones.bitmapimagen(gl,context,R.drawable.cara3,arrayTexturas,2);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara3);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[2]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D,0,bitmap,0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER,gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        trianguloTexture.dibujarCara2(gl);
        bitmap.recycle();
        gl.glPopMatrix();

        gl.glPushMatrix();
        Funciones.bitmapimagen(gl,context,R.drawable.cara4,arrayTexturas,3);
        trianguloTexture.dibujarCara3(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        Funciones.bitmapimagen(gl,context,R.drawable.cara5,arrayTexturas,4);
        gl.glTranslatef(0,-3,0);
        gl.glRotatef(-90, 1, 0, 0);
        cuadradoTexture.dibujar(gl);
        gl.glPopMatrix();

        vIncremento += 2f;
    }
}
