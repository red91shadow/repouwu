package ec.edu.uce.pa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import ec.edu.uce.pa.R;

public class ColorPantallaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);
        view.setRenderer(new ec.edu.uce.pa.renders.RenderColores());
        setContentView(view);
    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                red = (float)Math.random();
                green = (float)Math.random();
                blue = (float)Math.random();
                alfa = (float)Math.random();

        }

        return super.onTouchEvent(event);
    }


}