package ec.edu.uce.pa.activity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.renders.RenderColores;

public class ActivityFiguras extends AppCompatActivity {
    private GLSurfaceView view;
    private GLSurfaceView.Renderer renderer;

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
                RadioGroup rgOpciones = findViewById(R.id.RdPantalla);
                opcionSel = rgOpciones.getCheckedRadioButtonId();

                if (opcionSel > 0) {
                    switch (opcionSel) {
                        case R.id.rd_pintarPantalla: {
                            renderer= new RenderColores();
                            break;
                        }
                        case R.id.rd_puntos: {
                            break;
                        }
                        case R.id.rd_lineas: {
                            break;
                        }
                        case R.id.rd_Poligonos: {
                            break;
                        }
                        case R.id.rd_triangulos: {
                            break;
                        }

                    }

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
