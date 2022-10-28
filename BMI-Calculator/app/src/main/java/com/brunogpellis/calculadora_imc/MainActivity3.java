package com.brunogpellis.calculadora_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity3 extends AppCompatActivity {

    TextView tvYourIMC;
    Button btnBack, btnAboutIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main3);

        tvYourIMC = findViewById(R.id.tv_yourIMC);
        btnAboutIMC = findViewById(R.id.btn_aboutIMC);
        btnBack = findViewById(R.id.btn_back);

        Intent it_recebeCalcular = getIntent();

        tvYourIMC.setText(it_recebeCalcular.getStringExtra("pe_resultado"));

    }

    public void voltar(View view){
        this.finish();
    }

    public void irSobreIMC(View view){
        Intent it_irSobreIMC = new Intent(this, MainActivity4.class);
        startActivity(it_irSobreIMC);
        Toast.makeText(this, "Acessando site.", Toast.LENGTH_LONG).show();
    }
}