package com.example.vchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
/*
 ****************************
 * Develop by Yash Somani
 * **************************
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailBox, passwordBox, nameBox;
    Button loginBtn, signupBtn;

    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        emailBox = findViewById(R.id.emailBox);
        nameBox = findViewById(R.id.nameBox);
        passwordBox = findViewById(R.id.passwordBox);

        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.createBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass, name;
                email = emailBox.getText().toString();
                pass = passwordBox.getText().toString();
                name = nameBox.getText().toString();

                final User user = new User();
                user.setEmail(email);
                user.setPass(pass);
                user.setName(name);

                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            database.collection("Users")
                                    .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(signup.this, "register successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(signup.this, Login.class));
                                }
                            });
//                            Toast.makeText(SignupActivity.this, "Account is created.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(signup.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,Login.class));
            }
        });
    }
}