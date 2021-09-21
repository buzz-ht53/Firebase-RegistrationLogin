package com.buzz_ht.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button buttonlogin;
    EditText email,password;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonlogin = findViewById(R.id.buttonlogin);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonlogin = findViewById(R.id.buttonlogin);

        firebaseAuth = FirebaseAuth.getInstance();


        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EMAIL = email.getText().toString();
                String PASS= password.getText().toString();

                if(EMAIL.isEmpty()){
                    email.setError("Email cant be blank");
                    email.requestFocus();
                    return;

                }

                if(PASS.isEmpty()){
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(EMAIL,PASS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        }
                        }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });




    }

    public void goback(View view) {

        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
}