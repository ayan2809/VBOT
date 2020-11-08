package com.example.vbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterButton;
    TextView mLoginButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;


    public void login(View view)
    {
        //FirebaseAuth.getInstance().signOut();//WIll do the logout of the user
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName= findViewById(R.id.NameInput);
        mEmail=findViewById(R.id.EmailInput);
        mPassword=findViewById(R.id.PasswordInput);
        mPhone=findViewById(R.id.PhoneInput);

        mRegisterButton=findViewById(R.id.SignUpButton);
        mLoginButton=findViewById(R.id.HeaderText3);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        fStore=FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }



        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email= mEmail.getText().toString().trim();
                final String password= mPassword.getText().toString().trim();
                final String fullname= mFullName.getText().toString();
                final String phone= mPhone.getText().toString();

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

                //Registration of the user using Firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this,"User Created Successfully",Toast.LENGTH_SHORT).show();
                            userID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fStore.collection("users").document(userID);
                            Map<String,Object> user= new HashMap<>();
                            user.put("fName",fullname);
                            user.put("email",email);
                            user.put("phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG","onSuccess: user Profile is created for "+userID);
                                }
                            });

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else
                        {

                            Toast.makeText(Register.this,"Some Error has occured :" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });




    }
}