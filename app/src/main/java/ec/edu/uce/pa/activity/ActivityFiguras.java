package ec.edu.uce.pa.activity;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.*;
import ec.edu.uce.pa.renders.MyGLRenderer;
import ec.edu.uce.pa.renders.RenderLinea;
import ec.edu.uce.pa.renders.RenderPunto;

public class ActivityFiguras extends AppCompatActivity {
    private GLSurfaceView view;

    public static GLSurfaceView.Renderer getRenderer() {
        return renderer;
    }

    private static GLSurfaceView.Renderer renderer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_figuras);

        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);
        renderer=null;

        Button btnPintar = findViewById(R.id.button_pintar);
        btnPintar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int opcionSel;
                RadioGroup rgOpciones = (RadioGroup) findViewById(R.id.RdPantalla);
                opcionSel = rgOpciones.getCheckedRadioButtonId();

                if (opcionSel > 0) {

                    if (opcionSel==R.id.button_pintar) {
                        renderer= null;
                    }
                    else if (opcionSel==R.id.rd_puntos) {
                        renderer= new RenderPunto();
                    }
                    else if (opcionSel==R.id.rd_lineas) {
                        renderer= new RenderLinea();
                    }
                    else if (opcionSel==R.id.rd_Poligonos) {
                    }
                    else if (opcionSel==R.id.rd_triangulos) {
                    }
                    Intent intent = new Intent(ActivityFiguras.this, ColorPantallaActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(ActivityFiguras.this, "xd", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button btnSalir = findViewById(R.id.button_salir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}
