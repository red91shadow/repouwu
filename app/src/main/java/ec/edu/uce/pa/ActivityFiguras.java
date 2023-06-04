package ec.edu.uce.pa;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.renders.RenderCamion;
import ec.edu.uce.pa.renders.RenderLinea;
import ec.edu.uce.pa.renders.RenderPunto;
import ec.edu.uce.pa.renders.RenderesColores;

public class ActivityFiguras extends AppCompatActivity {
    private GLSurfaceView view;

    private GLSurfaceView.Renderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_figuras);

        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);
        renderer = null;

        Button btnDibujar = findViewById(R.id.button_pintar);
        btnDibujar.setOnClickListener(v -> {
            int opcionSel;
            RadioGroup rgOpcines = findViewById(R.id.RgPantalla);
            opcionSel = rgOpcines.getCheckedRadioButtonId();

            if (opcionSel > 0) {

                if (opcionSel == R.id.rb_pintarPantalla) {
                    renderer = new RenderesColores();
                } else if (opcionSel == R.id.rb_puntos) {
                    renderer = new RenderPunto();
                } else if (opcionSel == R.id.rb_lineas) {
                    renderer = new RenderLinea();
                } else if (opcionSel == R.id.rb_triangulos) {

                } else if (opcionSel == R.id.rb_poligonos) {

                } else if (opcionSel == R.id.rb_carrito) {
                    renderer = new RenderCamion();
                }
                view.setRenderer(renderer);
                setContentView(view);

            } else
                Toast.makeText(ActivityFiguras.this, "Seleccione una opcion", Toast.LENGTH_LONG).show();
        });

        Button btnSalir = findViewById(R.id.button_salir);
        btnSalir.setOnClickListener(e->System.exit(0));
    }

}
