package ec.edu.uce.pa;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import ec.edu.uce.pa.renders.RenderCamion;
import ec.edu.uce.pa.renders.RenderCubo;
import ec.edu.uce.pa.renders.RenderLinea;
import ec.edu.uce.pa.renders.RenderTriangulo;

public class MainActivity extends Activity {

    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new RenderCubo());
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
}
