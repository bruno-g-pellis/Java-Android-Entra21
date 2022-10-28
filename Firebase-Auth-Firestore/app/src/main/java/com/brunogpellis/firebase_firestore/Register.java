package com.brunogpellis.firebase_firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText edtNameRegister, edtLastNameRegister, edtPasswordRegister;
    private EditText edtRepitePasswordRegister, edtEmailRegister;
    private CheckBox ckbShowPasswordRegister;
    private Button btnRegister;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        edtNameRegister = findViewById(R.id.edtNameRegister);
        edtLastNameRegister = findViewById(R.id.edtLastNameRegister);
        edtEmailRegister = findViewById(R.id.edtEmailRegister);
        edtPasswordRegister = findViewById(R.id.edtPasswordRegister);
        edtRepitePasswordRegister = findViewById(R.id.edtRepitePasswordRegister);
        ckbShowPasswordRegister = findViewById(R.id.ckbShowPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();


        ckbShowPasswordRegister.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edtPasswordRegister.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edtRepitePasswordRegister.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edtPasswordRegister.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edtRepitePasswordRegister.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtNameRegister.getText().toString();
                String lastName = edtLastNameRegister.getText().toString();
                String email = edtEmailRegister.getText().toString();
                String password = edtPasswordRegister.getText().toString();
                String repitePassword = edtRepitePasswordRegister.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName) ||
                        TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||
                        TextUtils.isEmpty(repitePassword)) {

                    Snackbar snackbar = Snackbar.make(v, "Preencha todos os campos.", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.BLACK);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();


                } else {

                    if (password.equals(repitePassword)) {
                        mAuth.createUserWithEmailAndPassword(email, repitePassword).
                                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {

                                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                                            Map<String, Object> user = new HashMap<>();
                                            user.put("Nome", name);
                                            user.put("Sobrenome", lastName);
                                            user.put("Email", email);

                                            db.collection("Usuários").
                                                    document(mAuth.getCurrentUser().getUid()).set(user);

                                            Intent intent = new Intent(Register.this, Login.class);
                                            Snackbar snackbar = Snackbar.make(v, "Cadasdro OK.", Snackbar.LENGTH_SHORT);
                                            snackbar.setBackgroundTint(Color.BLACK);
                                            snackbar.setTextColor(Color.WHITE);
                                            snackbar.show();
                                            startActivity(intent);
                                            finish();


                                        } else {
                                            String error;
                                            try {
                                                throw task.getException();
                                                // senha com menos de 6 caracteres
                                            } catch (FirebaseAuthWeakPasswordException e) {
                                                error = "Senha deve possuir no mínimo 6 caracteres.";

                                                // já tem essa conta no banco de dados.
                                            } catch (FirebaseAuthUserCollisionException e) {
                                                error = "Conta já cadastrada.";

                                                // email não corresponde ao padrão.
                                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                                error = "E-mail inválido.";

                                                //erro padrão
                                            } catch (Exception e) {
                                                error = "Erro ao cadastrar usuário.";
                                            }

                                            Snackbar snackbar = Snackbar.make(v, error, Snackbar.LENGTH_SHORT);
                                            snackbar.setBackgroundTint(Color.BLACK);
                                            snackbar.setTextColor(Color.WHITE);
                                            snackbar.show();


                                        }


                                    }
                                });
                    } else {
                        Snackbar snackbar = Snackbar.make(v, "Senhas devem ser iguais.", Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.BLACK);
                        snackbar.setTextColor(Color.WHITE);
                        snackbar.show();
                    }
                }

            }
        });


    }


}