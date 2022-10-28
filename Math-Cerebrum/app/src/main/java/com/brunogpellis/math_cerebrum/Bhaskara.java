package com.brunogpellis.math_cerebrum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bhaskara extends AppCompatActivity {

   public EditText edtAx2,edtBx,edtC;
   public Button btnCalculateBhaskara, btn_back, btnClean;
   public TextView txvResultDelta,txvMessageDelta, txvResultX1,txvResultX2, txvSolution;
   public int valorA, valorB, valorC,delta;
   public Double x1, x2;
   public String mensagemDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhaskara);

        getSupportActionBar().hide();

        edtAx2 = findViewById(R.id.edt_ax2);
        edtBx = findViewById(R.id.edt_bx);
        edtC = findViewById(R.id.edt_c);

        btnCalculateBhaskara = findViewById(R.id.btn_calculate_bhaskara);
        btn_back = findViewById(R.id.btn_back);
        btnClean = findViewById(R.id.btn_clean);

        txvResultDelta = findViewById(R.id.txv_result_delta);
        txvMessageDelta = findViewById(R.id.txv_message_delta);
        txvResultX1 = findViewById(R.id.txv_result_x1);
        txvResultX2 = findViewById(R.id.txv_result_x2);
        txvSolution = findViewById(R.id.txv_solution);


        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAx2.setText(null);
                edtBx.setText(null);
                edtC.setText(null);
                txvResultDelta.setText(null);
                txvMessageDelta.setText(null);
                txvResultX1.setText(null);
                txvResultX2.setText(null);
                txvSolution.setText(null);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent int_backMainAc2 = new Intent(Bhaskara.this,MainActivity2.class);
                startActivity(int_backMainAc2);

            }
        });

        btnCalculateBhaskara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((edtAx2.getText().toString().equals("0")) || (edtAx2.getText().length()==0)) {
                    edtAx2.setError("Valor de A não pode ser nulo nem 0.");
                }

                if (edtBx.getText().length()==0){
                    edtBx.setError("Valor de B não pode ser nulo.");
                }

                if (edtC.getText().length()==0){
                    edtC.setError("Valor de C não pode ser nulo.");
                }

                if(((!edtAx2.getText().toString().equals("0")) || (edtAx2.getText().length() > 0)) &&
                (edtBx.getText().length() > 0) && (edtC.getText().length() > 0)) {

                    calcularDelta();
                    txvResultDelta.setText(String.valueOf(delta));

                    informarMensagemDelta();
                    txvMessageDelta.setText(mensagemDelta);

                    if (calcularDelta() > 0){
                        calcularX1();
                        calcularX2();
                        txvResultX1.setText(String.format("%.2f", x1));
                        txvResultX2.setText(String.format("%.2f", x2));
                        txvSolution.setText("Solução = {"+ String.format("%.2f", x1) + ","+ String.format("%.2f", x2)+"}");
                    } else if (calcularDelta() == 0) {
                        calcularX1();
                        calcularX2();
                        txvResultX1.setText(String.format("%.2f", x1));
                        txvResultX2.setText(String.format("%.2f", x2));
                        txvSolution.setText("Solução = {"+ String.format("%.2f", x1) +"}");
                    } else{
                        txvResultX1.setText(null);
                        txvResultX2.setText(null);
                        txvSolution.setText("Solução = { } = Ø");
                    }

                }
            }
        });


    }



    public int calcularDelta(){

        valorA = Integer.parseInt(edtAx2.getText().toString());
        valorB = Integer.parseInt(edtBx.getText().toString());
        valorC = Integer.parseInt(edtC.getText().toString());

        delta = ((int) Math.pow(valorB,2)) - (4 * valorA * valorC);

        return delta;

    }

    public void informarMensagemDelta(){

        if (delta > 0){
            mensagemDelta = "Δ > 0, a equação terá duas raízes reais e distintas.";
        } else if (delta == 0){
            mensagemDelta = "Δ = 0, a equação terá duas raízes iguais.";
        } else{
            mensagemDelta = "Δ < 0, a equação não terá raízes reais.";
        }
    }


    public double calcularX1(){

        x1 = (-valorB + Math.pow(delta,0.5))/(2 * valorA);

        return x1;
    }

    public double calcularX2(){

        x2 = (-valorB - Math.pow(delta,0.5))/(2 * valorA);

        return x2;
    }






}