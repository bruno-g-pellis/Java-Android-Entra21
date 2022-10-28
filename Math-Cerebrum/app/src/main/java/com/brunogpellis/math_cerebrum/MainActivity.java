package com.brunogpellis.math_cerebrum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        btnEnter = findViewById(R.id.btn_start);

        ///////////////

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_goeMainAc2 = new Intent(MainActivity.this,MainActivity2.class);
                Toast.makeText(MainActivity.this, "Acessando Math Cerebrum", Toast.LENGTH_SHORT).show();
                startActivity(int_goeMainAc2);
            }
        });
    }

    ///////////////////

}