package ec.edu.uce.pa.renders;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
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
        cuadradoTexture = new CuadradoTexture();
        trianguloTexture = new TrianguloTexture();
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);
        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glGenTextures(5, arrayTexturas, 0);

//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.screenshot_2023_05_14_202256);
//        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
//        GLUtils.texImage2D(gl.GL_TEXTURE_2D,0,bitmap,0);
//        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER,gl.GL_LINEAR);
//        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
//        bitmap.recycle();
        Funciones.bitmapimagen(gl,context,R.drawable.cara1,arrayTexturas,0);
        Funciones.bitmapimagen(gl,context, R.drawable.cara2,arrayTexturas,1);
        Funciones.bitmapimagen(gl,context,R.drawable.cara3,arrayTexturas,2);
        Funciones.bitmapimagen(gl,context,R.drawable.cara4,arrayTexturas,3);
        Funciones.bitmapimagen(gl,context,R.drawable.cara5,arrayTexturas,4);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float relacionAspecto = (float) ancho / (float) alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 50);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV,gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        //gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glTranslatef(0, 0, -3);
        gl.glRotatef(-vIncremento, 0, 1, 0);
//        cuadradoTexture.dibujar(gl);
        trianguloTexture.dibujarCara0(gl);
        gl.glTranslatef(0, 0, 0);
        trianguloTexture.dibujarCara1(gl);
        gl.glTranslatef(0, 0, 0);
        trianguloTexture.dibujarCara2(gl);
        gl.glTranslatef(0, 0, 0);
        trianguloTexture.dibujarCara3(gl);
        gl.glTranslatef(0, 0, 0);
        trianguloTexture.dibujarBase(gl);
        vIncremento += 0.5f;

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
