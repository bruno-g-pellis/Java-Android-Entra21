package com.brunogpellis.math_cerebrum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button btnEnterBhaskara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().hide();

        btnEnterBhaskara = findViewById(R.id.btn_enter_bhaskara);

        btnEnterBhaskara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent int_goBhaskara = new Intent(MainActivity2.this,Bhaskara.class);
                startActivity(int_goBhaskara);
            }
        });


    }
}