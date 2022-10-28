package com.brunogpellis.calculadora_imc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    // DECLARAÇÃO DE VARIÁVEIS

    double weight, height, imc;
    String resultado;
    EditText etWeight, etHeight;
    Button btnCalculate, btnAboutIMC, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main2);

        //ASSOCIAÇÃO DA VARIÁVEL NA VIEW

        etWeight = findViewById(R.id.et_weight);
        etHeight = findViewById(R.id.et_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnAboutIMC = findViewById(R.id.btn_aboutIMC);
        btnBack = findViewById(R.id.btn_back);

    }


    public void voltar(View view) {

        this.finish();
    }

    public void calcular() {
        Intent it_calcular = new Intent(this, MainActivity3.class);

        weight = Double.parseDouble(etWeight.getText().toString());
        height = Double.parseDouble(etHeight.getText().toString());

        imc = weight / Math.pow(height, 2);


        if (imc < 18.5) {
            resultado = "IMC = " + String.format("%.2f", imc) +  ".\nBaixo peso.";
        } else if (imc >= 18.5 && imc < 25) {
            resultado = "IMC = " + String.format("%.2f", imc) + ".\nPeso normal";
        } else if (imc >= 25 && imc < 30) {
            resultado = "IMC = " + String.format("%.2f", imc) + ".\nExcesso de peso.";
        } else if (imc >= 30 && imc < 35) {
            resultado = "IMC = " + String.format("%.2f", imc) + ".\nObesidade de classe 1.";
        } else if (imc >= 35 && imc < 40) {
            resultado = "IMC = " + String.format("%.2f", imc) + ".\nObesidade de classe 2.";
        } else {
            resultado = "IMC = " + String.format("%.2f", imc) + ".\nObesidade de classe 3.";
        }

        it_calcular.putExtra("pe_resultado", resultado);

        startActivity(it_calcular);
    }


    public void operarCalcular(View view) {

        AlertDialog.Builder msg = new AlertDialog.Builder(this);

        if ("0".equals(etWeight.getText().toString()) || "0".equals(etHeight.getText().toString())
                || etWeight.getText().length() == 0 || etHeight.getText().length() == 0){
            msg.setMessage("Campo(s) zerado(s) e/ou vazio(s).");
            msg.setNegativeButton("OK", null);
            msg.show();
        } else {
            calcular();
        }
    }

}


