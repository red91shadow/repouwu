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
        view.setRenderer(ActivityFiguras.getRenderer());
        setContentView(view);
    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){

        }

        return super.onTouchEvent(event);
    }


}