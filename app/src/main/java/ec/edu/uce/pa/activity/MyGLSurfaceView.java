package ec.edu.uce.pa.activity;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.renders.MyGLRenderer;

public class MyGLSurfaceView extends GLSurfaceView {

    private MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        // Crea un objeto OpenGL ES 2.0
        setEGLContextClientVersion(2);

        // Asigna el renderizador
        mRenderer = new MyGLRenderer(this);
        setRenderer(mRenderer);

        // Solicita actualizaciones continuas de la vista
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mRenderer.changeBackgroundColor();
            return true;
        }
        return super.onTouchEvent(event);
    }

    public static class MainPage1 extends AppCompatActivity {

        private Button buttNextPg2;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            buttNextPg2 = (Button) findViewById(R.id.buttonNext);
            buttNextPg2.setOnClickListener(v -> openPg2());

        }
        public void onClickName (View view){
            TextView txtProg = findViewById(R.id.textViewProg);
            txtProg.setText("Programación Avanzada 2");

        }
        public void openPg2(){
    //        Intent intent = new Intent(this, MainActivity2.class);
            Intent intent = new Intent(this, ColorPantallaActivity.class);
            startActivity(intent);
            //todo metodo onSuperChange
        }

    }
}
