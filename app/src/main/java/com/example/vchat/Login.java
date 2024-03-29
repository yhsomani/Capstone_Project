package com.example.vchat;
/*
 ****************************
 * Develop by Yash Somani
 * **************************
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText emailBox, passwordBox;
    Button loginBtn, signupBtn;
    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        auth=FirebaseAuth.getInstance();

        emailBox= findViewById(R.id.emailBox);
        passwordBox= findViewById(R.id.passwordBox);

        loginBtn= findViewById(R.id.loginBtn);
        signupBtn= findViewById(R.id.createBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
                String email,password;
                email=emailBox.getText().toString();
                password=passwordBox.getText().toString();
//                System.out.println("Email:"+email+"/n Pass"+password);
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, DashboardActivity.class));
                        }else {
                            Toast.makeText(Login.this,task.getException().getLocalizedMessage() ,Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, signup.class));
            }
        });
    }

}