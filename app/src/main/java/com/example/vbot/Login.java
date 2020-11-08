package com.example.vbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginButton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    public void signup(View view)
    {
        //FirebaseAuth.getInstance().signOut();//WIll do the logout of the user
        startActivity(new Intent(getApplicationContext(),Register.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mEmail=findViewById(R.id.EmailInputLogin);
        mPassword=findViewById(R.id.PasswordInputLogin);
        mLoginButton=findViewById(R.id.SignInLoginButton);
        progressBar=findViewById(R.id.progressBar2);
        fAuth=FirebaseAuth.getInstance();

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= mEmail.getText().toString().trim();
                String password= mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty((password))){
                    mPassword.setError("Password is Required");
                    return;
                }

                if(password.length()<8)
                {
                    mPassword.setError("Password must be >= 8 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //Authentication of the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this,"User Logged In Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else
                        {
                            Toast.makeText(Login.this,"Some Error has occured :" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }
}