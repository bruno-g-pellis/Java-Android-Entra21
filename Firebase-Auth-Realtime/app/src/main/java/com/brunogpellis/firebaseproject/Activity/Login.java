package com.brunogpellis.firebaseproject.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.brunogpellis.firebaseproject.MainActivity;
import com.brunogpellis.firebaseproject.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;


    EditText edtEmail, edtPassword;
    CheckBox ckbShowPassword;
    Button btnEnter;
    SignInButton btnGoogle;
    TextView txvCreateAccount;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1062039314985-9t27g7l2loqs5gtv5ipo0nt6ptvdja7q.apps.googleusercontent.com").
                requestEmail().
                build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);


        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        ckbShowPassword = findViewById(R.id.ckb_showPassword);
        btnEnter = findViewById(R.id.btn_enter);
        btnGoogle = findViewById(R.id.btn_google);
        txvCreateAccount = findViewById(R.id.txv_createAccount);
        mAuth = FirebaseAuth.getInstance();


        txvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Login.this, Register.class);
                startActivity(intent2);
                finish();
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


        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = edtEmail.getText().toString();
                String loginPassword = edtPassword.getText().toString();

                if (TextUtils.isEmpty(loginEmail) || TextUtils.isEmpty(loginPassword)) {
                    Snackbar snackbar = Snackbar.make(v, "Preencha todos os campos.", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.BLUE);
                    snackbar.setTextColor(Color.WHITE);

                } else {
                    mAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        toOpenMainActivity();
                                    } else {
                                        String error;
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            error = "E-mail ou/e senha inválido(s).";
                                        } catch (Exception e) {
                                            error = "Erro ao efetuar o cadastro.";
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(Login.this, error, Toast.LENGTH_SHORT).show();


                                    }

                                }
                            });


                }


            }
        });


        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toSignInWithGoogle();


            }
        });


    }




    private void toSignInWithGoogle() {
        Intent intent = googleSignInClient.getSignInIntent();
//        startActivityForResult(intent,1);
        openActivity.launch(intent);
    }

    ActivityResultLauncher<Intent> openActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                    try {
                        GoogleSignInAccount conta = task.getResult(ApiException.class);
                        loginWithGoogle(conta.getIdToken());
                    } catch (ApiException exception) {
                        Toast.makeText(this, "Nenhum usuário Google logado no aparelho.", Toast.LENGTH_SHORT).show();
                        Log.d("Erro: ", exception.toString());
                    }
                }
            }
    );

    private void loginWithGoogle(String token) {
        AuthCredential credential = GoogleAuthProvider.getCredential(token, null);

        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    toOpenMainActivity();
                    Toast.makeText(Login.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Login.this, "Erro ao efetuar login!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
            try {
                GoogleSignInAccount conta = task.getResult(ApiException.class);
                loginWithGoogle(conta.getIdToken());
            } catch (ApiException exception) {
                Toast.makeText(this, "Nenhum usuário Google logado no aparelho.", Toast.LENGTH_SHORT).show();
                Log.d("Erro: ", exception.toString());
            }

        }
    }


    private void toOpenMainActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        //intent.putExtra("email", edtEmail.getText().toString());
        startActivity(intent);
        finish();
    }


}