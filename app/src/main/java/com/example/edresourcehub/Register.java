package com.example.edresourcehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText email,password,fullName;
    Button register,login;
    RadioGroup rdGroup;
    RadioButton rdButton;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.editTextTextEmail);
        password = findViewById(R.id.editTextTextPassword);
        fullName = findViewById(R.id.editTextTextFullName);
        register = findViewById(R.id.buttonRegister);
        login = findViewById(R.id.buttonLogin);
        rdGroup = findViewById(R.id.rdGroup);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                String name = fullName.getText().toString();
                int user = rdGroup.getCheckedRadioButtonId();
                rdButton = findViewById(user);
                String users = rdButton.getText().toString();
                if(!e.equals("")&&!p.equals("")&&!name.equals("")){

                    signUp(e,p,users,name);
                }
                else{
                    Toast.makeText(Register.this,"Enter all the details ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void signUp(String email, String pass,String users,String name){
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    reference.child("Users").child(auth.getUid()).child("UserName").setValue(name);
                    reference.child("Users").child(auth.getUid()).child("UserEmail").setValue(email);
                    Intent intent;
                    if(users.equals("Register as Student")){
                        intent = new Intent(Register.this,MainActivity.class);
                        reference.child("Users").child(auth.getUid()).child("isStudent").setValue("1");
                        startActivity(intent);
                    }else if(users.equals("Register as Teacher")){
                       intent = new Intent(Register.this,TeacherMainActivity.class);
                        reference.child("Users").child(auth.getUid()).child("isStudent").setValue("0");
                        startActivity(intent);
                    }


                }else{
                    Toast.makeText(Register.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}