package com.example.edresourcehub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class AddFile extends AppCompatActivity {

    EditText fileName;
    Button addFile;


    FirebaseDatabase database;
    DatabaseReference reference;
    Random random = new Random();
    long ID = random.nextLong();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_file);

        fileName = findViewById(R.id.editTextTextFileName);
        addFile = findViewById(R.id.buttonAddFile);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fileName.getText().toString();
                addTODataBase(name);

            }
        });

    }
    public void addTODataBase(String name){

        String id = String.valueOf(ID);
        reference.child("FileName").child(id).child("name").setValue(name);

    }
}