package com.brunogpellis.firebase_firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

import javax.xml.datatype.Duration;

public class Login extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnEnter;
    private CheckBox ckbShowPassword;
    private TextView txtCreateAccount;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnEnter = findViewById(R.id.btnEnter);
        ckbShowPassword = findViewById(R.id.ckbShowPassword);
        txtCreateAccount = findViewById(R.id.txtCreateAccount);
        mAuth = FirebaseAuth.getInstance();


        txtCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
            finish();
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();


                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Snackbar snackbar = Snackbar.make(v, "Preencha todos os campos.", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.BLACK);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();

                } else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                enterMain();
                                Snackbar snackbar = Snackbar.make(v, "Login OK.", Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.BLACK);
                                snackbar.setTextColor(Color.WHITE);
                                snackbar.show();

                            } else {
                                String error;
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    error = "E-mail ou/e senha inválido(s).";
                                    Snackbar snackbar = Snackbar.make(v, error, Snackbar.LENGTH_SHORT);
                                    snackbar.setBackgroundTint(Color.BLACK);
                                    snackbar.setTextColor(Color.WHITE);
                                    snackbar.show();

                                } catch (Exception e) {
                                    error = "Erro ao efetuar o cadastro.";
                                    Snackbar snackbar = Snackbar.make(v, error, Snackbar.LENGTH_SHORT);
                                    snackbar.setBackgroundTint(Color.BLACK);
                                    snackbar.setTextColor(Color.WHITE);
                                    snackbar.show();
                                    e.printStackTrace();
                                }

                            }


                        }
                    });
                }


            }
        });

        ckbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


    }

    public void enterMain() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}