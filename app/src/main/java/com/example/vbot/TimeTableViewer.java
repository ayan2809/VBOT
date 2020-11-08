package com.example.vbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class TimeTableViewer extends AppCompatActivity {

    private Uri filepath;
    private StorageReference storageReference;

    private Button buttonView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_viewer);
        buttonView = findViewById(R.id.buttonView);
        storageReference = FirebaseStorage.getInstance().getReference().child("images/TimeTable.jpg");
        //buttonView.setOnClickListener((View.OnClickListener) this);
        }

        public void fileView()
        {



    try {


        final File localFile = File.createTempFile("TimeTable", "jpg");
        storageReference.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(TimeTableViewer.this,"Picture can be accessed successfully",Toast.LENGTH_SHORT
                        ).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        ((ImageView) findViewById(R.id.imageView2)).setImageBitmap(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                Toast.makeText(TimeTableViewer.this,"Error Occured",Toast.LENGTH_SHORT
                ).show();
                // ...
            }
        });
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
        }

    public void mainredirect3(View view)
    {
        //FirebaseAuth.getInstance().signOut();//WIll do the logout of the user
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }


    public void ViewTimeTable(View view)
    {
        if(view==buttonView)
        {

            fileView();
        }

    }


}