package ec.edu.uce.pa;


import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.renders.RenderCuboColores;

public class MainActivity extends AppCompatActivity {

    public static float rotarX =0;
    public static float rotarY =0;
    public static float rotarZ =0;
    public static float ejeX =0;
    public static float ejeY =0;
    public static float ejeZ =-3;
    public static float iRotacion=0,iRotacionC=0;
    public static boolean rotar=false;
    public static boolean ejeCamara = false;

    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new GLSurfaceView(this);
        //manualmente
        glSurfaceView.setRenderer(new RenderCuboColores());
        setContentView(glSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_W:
                ejeY +=0.1f;
                return true;
            case KeyEvent.KEYCODE_S:
                ejeY -=0.1f;
                return true;
            case KeyEvent.KEYCODE_A:
                ejeX -=0.1f;
                return true;
            case KeyEvent.KEYCODE_D:
                ejeX +=0.1f;
                return true;
            case KeyEvent.KEYCODE_Q:
                ejeZ -=0.1f;
                return true;
            case KeyEvent.KEYCODE_E:
                ejeZ +=0.1f;
                return true;

            case KeyEvent.KEYCODE_DPAD_UP:
                ejeCamara = false;
                rotar = true;
                rotarX = 1;
                rotarY = 0;
                rotarZ = 0;
                iRotacion-=2.5f;
                iRotacionC-=0.01f;
                return true;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                ejeCamara = false;
                rotar = true;
                rotarX = 1;
                rotarY = 0;
                rotarZ = 0;
                iRotacion+=2.5f;
                iRotacionC+=0.01f;
                return true;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                ejeCamara = true;
                rotar = true;
                rotarX = 0;
                rotarY = 1;
                rotarZ = 0;
                iRotacion-=2.5f;
                iRotacionC+=0.01f;
                return true;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                ejeCamara = true;
                rotar = true;
                rotarX = 0;
                rotarY = 1;
                rotarZ = 0;
                iRotacion+=2.5f;
                iRotacionC-=0.01f;
                return true;
            default:
                Toast.makeText(this,"Otra tecla" , Toast.LENGTH_SHORT).show();
                return true;
        }
    }
}
