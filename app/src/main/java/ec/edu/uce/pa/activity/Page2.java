package ec.edu.uce.pa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import ec.edu.uce.pa.MainActivity;
import ec.edu.uce.pa.R;

public class Page2 extends AppCompatActivity {

    private Button buttBackPg1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        buttBackPg1 = (Button) findViewById(R.id.buttonBack);
        buttBackPg1.setOnClickListener(v -> openHome());
    }
        public void openHome(){
          Intent intent = new Intent(this, MainActivity.class);
          startActivity(intent);
        }

}