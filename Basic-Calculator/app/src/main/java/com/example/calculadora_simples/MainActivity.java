package com.example.calculadora_simples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double valor1, valor2, resultado;
    Button btn_somar, btn_subtrair, btn_multiplicar, btn_dividir;
    EditText ed_Valor1, ed_Valor2;
    TextView tv_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn_somar = findViewById(R.id.btn_somar);
        btn_subtrair = findViewById(R.id.btn_subtrair);
        btn_multiplicar = findViewById(R.id.btn_multiplicar);
        btn_dividir = findViewById(R.id.btn_dividir);

        tv_resultado = findViewById(R.id.tv_resultado);

        ed_Valor1 = findViewById(R.id.et_valor1);
        ed_Valor2 = findViewById(R.id.et_valor2);

    }

    public void somar() {
        valor1 = Double.parseDouble(ed_Valor1.getText().toString());
        valor2 = Double.parseDouble(ed_Valor2.getText().toString());
        resultado = valor1 + valor2;
        tv_resultado.setText(String.valueOf(resultado));
    }


    public void subtrair() {
        valor1 = Double.parseDouble(ed_Valor1.getText().toString());
        valor2 = Double.parseDouble(ed_Valor2.getText().toString());
        resultado = valor1 - valor2;
        tv_resultado.setText(String.valueOf(resultado));
    }


    public void multiplicar() {
        valor1 = Double.parseDouble(ed_Valor1.getText().toString());
        valor2 = Double.parseDouble(ed_Valor2.getText().toString());
        resultado = valor1 * valor2;
        tv_resultado.setText(String.valueOf(resultado));
    }


    public void dividir() {
        valor1 = Double.parseDouble(ed_Valor1.getText().toString());
        valor2 = Double.parseDouble(ed_Valor2.getText().toString());
        resultado = valor1 / valor2;
        tv_resultado.setText(String.valueOf(resultado));
    }

    public void operar(View view){

        if (view.getId() == R.id.btn_somar) {
            somar();
        } else if (view.getId() == R.id.btn_subtrair){
            subtrair();
        } else if (view.getId() == R.id.btn_multiplicar){
            multiplicar();
        } else{
            dividir();
        }
    }

}


 /* ----------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_somar = findViewById(R.id.btn_somar);
        btn_subtrair = findViewById(R.id.btn_subtrair);
        btn_multiplicar = findViewById(R.id.btn_multiplicar);
        btn_dividir = findViewById(R.id.btn_dividir);

        tv_resultado = findViewById(R.id.tv_resultado);

        ed_Valor1 = findViewById(R.id.et_valor1);
        ed_Valor2 = findViewById(R.id.et_valor2);

    }

    public void somar(View view) {
        valor1 = Double.parseDouble(ed_Valor1.getText().toString());
        valor2 = Double.parseDouble(ed_Valor2.getText().toString());
        resultado = valor1 + valor2;
        tv_resultado.setText(String.valueOf(resultado));
    }


    public void subtrair(View view) {
        valor1 = Double.parseDouble(ed_Valor1.getText().toString());
        valor2 = Double.parseDouble(ed_Valor2.getText().toString());
        resultado = valor1 - valor2;
        tv_resultado.setText(String.valueOf(resultado));
    }


    public void multiplicar(View view) {
        valor1 = Double.parseDouble(ed_Valor1.getText().toString());
        valor2 = Double.parseDouble(ed_Valor2.getText().toString());
        resultado = valor1 * valor2;
        tv_resultado.setText(String.valueOf(resultado));
    }


    public void dividir(View view) {
        valor1 = Double.parseDouble(ed_Valor1.getText().toString());
        valor2 = Double.parseDouble(ed_Valor2.getText().toString());
        resultado = valor1 / valor2;
        tv_resultado.setText(String.valueOf(resultado));
    }

}

    ----------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_somar = (Button)findViewById(R.id.btn_somar);
        Button btn_subtrair = (Button)findViewById(R.id.btn_subtrair);
        Button btn_multiplicar = (Button)findViewById(R.id.btn_multiplicar);
        Button btn_dividir = (Button)findViewById(R.id.btn_dividir);

        TextView tv_resultado = (TextView)findViewById(R.id.tv_resultado);

        EditText ed_Valor1 = (EditText)findViewById(R.id.et_valor1);
        EditText ed_Valor2 = (EditText)findViewById(R.id.et_valor2);

        btn_somar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               valor1 = Double.parseDouble(ed_Valor1.getText().toString());
               valor2 = Double.parseDouble(ed_Valor2.getText().toString());
               resultado = valor1 + valor2;
               tv_resultado.setText(String.valueOf(resultado));
            }
        });

        btn_subtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor1 = Double.parseDouble(String.valueOf(ed_Valor1.getText()));
                valor2 = Double.parseDouble(ed_Valor2.getText().toString());
                resultado = valor1 - valor2;
                tv_resultado.setText(String.valueOf(resultado));
                
            }
        });

        btn_multiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor1 = Double.parseDouble(ed_Valor1.getText().toString());
                valor2 = Double.parseDouble(ed_Valor2.getText().toString());
                resultado = valor1 * valor2;
                tv_resultado.setText(String.valueOf(resultado));
            }
        });

        btn_dividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor1 = Double.parseDouble(ed_Valor1.getText().toString());
                valor2 = Double.parseDouble(ed_Valor2.getText().toString());
                resultado = valor1 / valor2;
                tv_resultado.setText(String.valueOf(resultado));
            }
        });

    }
}

---------------------------------------------------------------- */