package ec.edu.uce.pa.utilidades;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES10;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;

public class Funciones {

    private static Bitmap bitmap;
    private static int[] arrTexturas=null;

    public static Bitmap bitmapimagen(GL10 gl, Context res, int idImagen, int[] arrayTexturas,int pos){
        bitmap = BitmapFactory.decodeResource(res.getResources(), idImagen);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[pos]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D,0,bitmap,0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER,gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        bitmap.recycle();
        return bitmap;
    }

    public static FloatBuffer setFloatBuffer(float [] array){

        FloatBuffer fb;
        ByteBuffer bbBuffer = ByteBuffer.allocateDirect(array.length * 4);
        bbBuffer.order(ByteOrder.nativeOrder());
        fb = bbBuffer.asFloatBuffer();
        fb .put(array);
        fb .position(0);
        return fb;
    }

    public static FloatBuffer generarBuffer(float[] arrayVertices) {
        int byteFlotante = 4;
        FloatBuffer result;
        ByteBuffer buffer = ByteBuffer.allocateDirect(arrayVertices.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        result = buffer.asFloatBuffer();
        result.put(arrayVertices);
        result.position(0);
        return result;
    }

    public static float[] calculatePolygonCoordinates(int n, float radius, float altura) {
        float[] coordinates = new float[3 * n];
        float angle = (float) (2 * Math.PI / n);

        for (int i = 0; i < n; i++) {
            coordinates[3 * i] = (float) (radius * Math.cos(i * angle));
            coordinates[3 * i + 1] = (float) (radius * Math.sin(i * angle));
            coordinates[3 * i + 2] = altura;
        }
        return coordinates;
    }
    public static float[] calculateEllipseCoordinates(int n, float radiusX, float radiusY, float altura) {
        float[] coordinates = new float[3 * n];
        float angle = (float) (2 * Math.PI / n);

        for (int i = 0; i < n; i++) {
            coordinates[3 * i] = (float) (radiusX * Math.cos(i * angle));
            coordinates[3 * i + 1] = (float) (radiusY * Math.sin(i * angle));
            coordinates[3 * i + 2] = altura;
        }
        return coordinates;
    }


    public static void bitmapimagen(GL10 gl, Context context, int screenshot_2023_05_14_202256, int[] arrayTexturas) {

    }
}
