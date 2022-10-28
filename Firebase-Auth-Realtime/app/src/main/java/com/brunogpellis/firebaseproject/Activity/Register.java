package com.brunogpellis.firebaseproject.Activity;

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

import com.brunogpellis.firebaseproject.R;
import com.brunogpellis.firebaseproject.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText edtEmailRegister, edtPasswordRegister, edtRepitePasswordRegister;
    EditText edtNameRegister, edtLastNameRegister;
    CheckBox ckbShowPasswordRegister;
    Button btnRegister;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();


        edtNameRegister = findViewById(R.id.edt_nameRegister);
        edtLastNameRegister = findViewById(R.id.edt_lastNameRegister);
        edtEmailRegister = findViewById(R.id.edt_emailRegister);
        edtPasswordRegister = findViewById(R.id.edt_passwordRegister);
        edtRepitePasswordRegister = findViewById(R.id.edt_repitePasswordRegister);
        ckbShowPasswordRegister = findViewById(R.id.ckb_showPasswordRegister);
        btnRegister = findViewById(R.id.btn_register);
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

                User user = new User();

                user.setNameUser(edtNameRegister.getText().toString());
                user.setLastNameUser(edtLastNameRegister.getText().toString());
                user.setEmailUser(edtEmailRegister.getText().toString());

                String registerPassword = edtPasswordRegister.getText().toString();
                String repitePassword = edtRepitePasswordRegister.getText().toString();


                if (TextUtils.isEmpty(user.getNameUser()) || TextUtils.isEmpty(user.getLastNameUser()) ||
                        TextUtils.isEmpty(user.getEmailUser()) || TextUtils.isEmpty(registerPassword)
                        || TextUtils.isEmpty(registerPassword)) {
                    Snackbar snackbar = Snackbar.make(v, "Preencha todos os campos.", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.BLUE);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else {
                    if (registerPassword.equals(repitePassword)) {
                        mAuth.createUserWithEmailAndPassword(user.getEmailUser(), repitePassword)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            user.setIdUser(mAuth.getUid());
                                            user.saveUser();
//                                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//                                            reference.child("users").push().setValue(user);

                                            Intent intent = new Intent(Register.this, Login.class);
                                            Toast.makeText(Register.this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                            finish();
                                        } else {

                                            String error;
                                           try{
                                               throw task.getException();
                                           } catch (FirebaseAuthWeakPasswordException e ){
                                               error = "Senha deve conter no mínimo 6 caracteres.";
                                           } catch (FirebaseAuthInvalidCredentialsException e){
                                               error = "E-mail inválido.";
                                           } catch (FirebaseAuthUserCollisionException e){
                                               error = "Usuário já existe.";
                                           }catch (Exception e){
                                               error = "Erro ao efetuar o cadastro.";
                                               e.printStackTrace();
                                           }
                                            Toast.makeText(Register.this, error, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(Register.this, "Senhas devem ser iguais!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }
}