package com.brunogpellis.calculadora_imc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //DECLARAR VARIÁVEL
    TextView btnEntrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // REMOVE ACTION E STATUS BAR

        setContentView(R.layout.activity_main);

//        if (getSupportActionBar() != null) {
//          getSupportActionBar().hide();
//        }REMOVE APENAS A ACTION BAR



        //ASSOCIAÇÃO DA VARIÁVEL NA VIEW
        btnEntrar = findViewById(R.id.btn_entrar);
    }

    public void entrar(View view){
        Intent it_entrar = new Intent(this,MainActivity2.class);
        startActivity(it_entrar);
    }

    public void irSobreIMC(View view){
        Intent it_irSobreIMC = new Intent(this, MainActivity4.class);
        startActivity(it_irSobreIMC);
        Toast.makeText(this, "Acessando site.", Toast.LENGTH_LONG).show();
    }

}

