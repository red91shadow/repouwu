package ec.edu.uce.pa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ec.edu.uce.pa.R;

public class MainPage1 extends AppCompatActivity {

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