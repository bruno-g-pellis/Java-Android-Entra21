package com.brunogpellis.calculadora_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

public class MainActivity4 extends AppCompatActivity {

    WebView wvAboutIMC;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main4);

        url="https://www.gov.br/saude/pt-br/assuntos/saude-brasil/eu-quero-ter-peso-saudavel/noticias/2017/imc-voce-sabe-calcular-seu-peso-adequado";

        wvAboutIMC = findViewById(R.id.wv_aboutIMC);
        wvAboutIMC.loadUrl(url);

    }
}