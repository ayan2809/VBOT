package com.example.vbot;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();//WIll do the logout of the user
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
    public void privacy(View view)
    {
        //FirebaseAuth.getInstance().signOut();//WIll do the logout of the user
        startActivity(new Intent(getApplicationContext(),PrivacyPolicy.class));
        finish();
    }
    public void TTopen(View view)
    {
        startActivity(new Intent(getApplicationContext(),TImeTableUpload.class));
        finish();
    }
    public void TTopen2(View view)
    {
        startActivity(new Intent(getApplicationContext(),TimeTableViewer.class));
        finish();
    }
    public void ChatmateOpener(View view)
    {
        startActivity(new Intent(getApplicationContext(),Chatmate.class));
        finish();
    }
    public void CNUploader(View view)
    {
        startActivity(new Intent(getApplicationContext(),ClassNotesUpload.class));
        finish();
    }

}