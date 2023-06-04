package ec.edu.uce.pa.activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.ActivityFiguras;
import ec.edu.uce.pa.R;
import ec.edu.uce.pa.renders.RenderCamion;

public class ColorPantallaActivity extends AppCompatActivity {

    private GLSurfaceView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_figuras);
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);
        view.setRenderer(new RenderCamion());
        setContentView(view);
    }
    public static float f1,f2,f3,f4;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                f1 = (float) Math.random();
                f2 = (float) Math.random();
                f3 = (float) Math.random();
                f4 = (float) Math.random();
        }
        return super.onTouchEvent(event);
    }
    private void showToast(String Message){
        Toast.makeText(this, Message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ActivityFiguras.class);
        startActivity(intent);
        showToast("Regresemos");
    }
}