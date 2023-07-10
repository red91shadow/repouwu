package ec.edu.uce.pa.renders;

import static javax.microedition.khronos.opengles.GL10.GL_LIGHT0;
import static javax.microedition.khronos.opengles.GL10.GL_LIGHT1;
import static javax.microedition.khronos.opengles.GL10.GL_LIGHT2;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Astro;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderPlaneta implements GLSurfaceView.Renderer {

    private float vIncremento = 0.0f;
    private float vIncrementoScale = 0.0f;
    private Astro astro;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT1;
    private final static int LUZ2 = GL_LIGHT2;
    private float[] spotDir1, spotDir2;
    private float[] materialBlanco = {1.0f, 1.0f, 1.0f, 1.0f};
    private float[] materialVerde = {0.0f, 1.0f, 0.0f, 1.0f};
    private float[] materialRojo = {1.0f, 0.0f, 0.0f, 1.0f};
    private float[] materialAzul = {0.0f, 0.0f, 1.0f, 1.0f};
    private float[] materialAmarillo = {1.0f, 1.0f, 0.0f, 1.0f};

    private float[] materialBlancoMedio = {0.5f, 0.5f, 0.5f, 1.0f};
    private float[] materialVerdeMedia = {0.0f, 0.5f, 0.0f, 1.0f};
    private float[] materialRojoMedia = {0.5f, 0.0f, 0.0f, 1.0f};
    private float[] materialAzulMedia = {0.0f, 0.0f, 0.5f, 1.0f};
    private float[] materialAmarilloMedia = {0.5f, 0.5f, 0.0f, 1.0f};

    float[] posicionluz0 = {0.0f, 0.0f, -3.0f, 1.0f};
    float[] posicionluz1 = {0.0f, 0.0f, -3.0f, 1.0f};
    // float[] posicionluz2 = {0.0f, 0.0f, -3.0f, 1.0f};

    float[] color = {0.0f, 0.0f, 1.0f, 1.0f};
    float[] luzBlanca = {0.8f, 0.8f, 0.8f, 1.0f};
    float[] luzRojo = {1.0f, 0.0f, 0.0f, 1.0f};
    float[] luzVerde = {0.0f, 1.f, 0.0f, 1.0f};
    float[] luzAzul = {0.0f, 0.0f, 0.1f, 1.0f};
    float[] luzAmarillo = {1.0f, 1.0f, 0.0f, 1.0f};

    float[] luzBlancaMedio = {0.5f, 0.5f, 0.5f, 1.0f};
    float[] luzRojoMedia = {0.5f, 0.0f, 0.0f, 1.0f};

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        //Habilidatamos para trabajr en 3D
        gl10.glEnable(gl10.GL_DEPTH_TEST);

        //Valores con lo que vamos a empezar
        astro = new Astro(10, 10, 1.0f, 1.0f);

        gl10.glLightfv(LUZ0, gl10.GL_POSITION, Funciones.generarBuffer(posicionluz0));
        //gl10.glLightfv(LUZ0, gl10.GL_AMBIENT, Funciones.generarBuffer(luzBlanca));
        gl10.glLightfv(LUZ0, gl10.GL_SPECULAR, Funciones.generarBuffer(luzAmarillo));

        gl10.glEnable(gl10.GL_LIGHTING);
        gl10.glEnable(LUZ0);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        //relacion de aspecto, casteo porqeu el ancho y alto son int
        float relacionAspecto = (float) ancho / (float) alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-relacionAspecto, relacionAspecto, -relacionAspecto * 2, relacionAspecto * 2, 1, 300);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glPushMatrix();
        gl.glTranslatef(0, 0.0f, -6.0f);
        gl.glTranslatef(-3.0f, 0.0f, 0);
        gl.glTranslatef((float) ( 3-(vIncremento*0.1)),0, (float) -(vIncremento*0.055));
        vIncrementoScale +=0.5f;
        //Primer planeta
        gl.glPushMatrix();
        gl.glRotatef(vIncremento, 0, 1, 0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialAmarillo, 0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, materialAmarillo, 0);
        astro.dibujar(gl);
        gl.glPopMatrix();

        gl.glTranslatef(3.5f, 0.0f, 0.0f);
        vIncrementoScale +=0.5f;
//Segundo Planeta
        gl.glPushMatrix();

        gl.glRotatef(vIncremento, 0, 1, 0);
        gl.glScalef(vIncrementoScale, vIncrementoScale, vIncrementoScale);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialVerde, 0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, materialVerde, 0);
        //gl10.glMaterialfv(gl10.GL_FRONT_AND_BACK,gl10.GL_SPECULAR,materialVerde,0);
        astro.dibujar(gl);

        gl.glPopMatrix();
        //vIncrementoTras++;
        gl.glTranslatef(4.5f, 0f, 0.0f);
        vIncrementoScale +=0.5f;
        vIncrementoScale +=0.5f;
//Tercer planeta
        gl.glPushMatrix();
        gl.glRotatef(vIncremento, 0, 1, 0);
        gl.glScalef(vIncrementoScale, vIncrementoScale, vIncrementoScale);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialAzul, 0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, materialAzul, 0);
        astro.dibujar(gl);
        gl.glPopMatrix();

        //vIncrementoTras++;
        gl.glTranslatef(5.5f, 0f, 0.0f);
        vIncrementoScale +=0.5f;
        //4 Planeta
        gl.glPushMatrix();
        gl.glRotatef(vIncremento, 0, 1, 0);
        gl.glScalef(vIncrementoScale, vIncrementoScale, vIncrementoScale);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialRojo, 0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, materialRojo, 0);
        //gl10.glMaterialfv(gl10.GL_FRONT_AND_BACK,gl10.GL_SPECULAR,materialBlanco,0);
        astro.dibujar(gl);

        gl.glPopMatrix();

        gl.glTranslatef(6.5f, 0f, 0.0f);
        vIncrementoScale +=0.5f;
        //5 planeta
        gl.glPushMatrix();
        gl.glRotatef(vIncremento, 0, 1, 0);
        gl.glScalef(vIncrementoScale, vIncrementoScale, vIncrementoScale);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialAmarillo, 0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, materialAmarillo, 0);
        astro.dibujar(gl);
        gl.glPopMatrix();

        gl.glTranslatef(7.5f, 0f, 0.0f);
        vIncrementoScale +=0.5f;
        //6 planeta
        gl.glPushMatrix();
        gl.glRotatef(vIncremento, 0, 1, 0);
        gl.glScalef(vIncrementoScale, vIncrementoScale, vIncrementoScale);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialAmarillo, 0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, materialAmarillo, 0);
        //gl10.glMaterialfv(gl10.GL_FRONT_AND_BACK,gl10.GL_SPECULAR,materialBlanco,0);
        astro.dibujar(gl);
        gl.glPopMatrix();

        gl.glTranslatef(8.5f, 0f, 0.0f);
        vIncrementoScale +=0.5f;

        //7 planeta
        gl.glPushMatrix();
        gl.glRotatef(vIncremento, 0, 1, 0);
        gl.glScalef(vIncrementoScale, vIncrementoScale, vIncrementoScale);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialAmarillo, 0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, materialAmarillo, 0);
        //gl10.glMaterialfv(gl10.GL_FRONT_AND_BACK,gl10.GL_SPECULAR,materialBlanco,0);
        astro.dibujar(gl);

        gl.glPopMatrix();
        gl.glTranslatef(9.5f, 0f, 0.0f);
        vIncrementoScale +=0.5f;
        //8 planeta
        gl.glPushMatrix();
        gl.glRotatef(vIncremento, 0, 1, 0);
        gl.glScalef(vIncrementoScale, vIncrementoScale, vIncrementoScale);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialAmarillo, 0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, materialAmarillo, 0);
        //gl10.glMaterialfv(gl10.GL_FRONT_AND_BACK,gl10.GL_SPECULAR,materialBlanco,0);
        astro.dibujar(gl);

        gl.glPopMatrix();
        gl.glTranslatef(10.5f, 0f, 0.0f);
        vIncrementoScale +=0.5f;
        //9 planeta
        gl.glPushMatrix();
        gl.glRotatef(vIncremento, 0, 1, 0);
        gl.glScalef(vIncrementoScale, vIncrementoScale, vIncrementoScale);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialAmarillo, 0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, materialAmarillo, 0);
        //gl10.glMaterialfv(gl10.GL_FRONT_AND_BACK,gl10.GL_SPECULAR,materialBlanco,0);
        astro.dibujar(gl);

        gl.glPopMatrix();

        gl.glPopMatrix();

        vIncremento += 0.3f;
        vIncrementoScale=0;
    }
}
