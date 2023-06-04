package ec.edu.uce.pa;

//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.TextView;
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//
//import ec.edu.uce.pa.activity.MyGLSurfaceView;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(new MyGLSurfaceView(this));
//    }
//
//}
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import ec.edu.uce.pa.renders.RenderLinea;
import ec.edu.uce.pa.renders.RenderPunto;

public class MainActivity extends Activity {

    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new RenderPunto());
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
