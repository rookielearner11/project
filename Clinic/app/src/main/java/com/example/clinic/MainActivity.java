/**
 * This class is to use Firebase to implement the function that users can creat accounts
 * There is one and only one admin account (username: admin, pwd:5T5ptQ)
 * Employee and patients can be created infinitly
 * @author Heng(Scott) Zhang (a Universite d'Ottawa, numero etudient: 300067988)
 * Date Started: October 16, 2019
 */

package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthException;

public class MainActivity extends AppCompatActivity {

    EditText email, pwd;
    Button signBtn, regBtn;
    FirebaseAuth myFirebaseAuth;
    static int emlh;
    private FirebaseAuth.AuthStateListener myListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);

        myListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                Toast.makeText(MainActivity.this, "Please log in", Toast.LENGTH_SHORT).show();
            }
        };
        signBtn = findViewById(R.id.signBtn);
        regBtn = findViewById(R.id.regBtn);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eml = email.getText().toString();
                emlh = eml.hashCode();
                String pw = pwd.getText().toString();
                if (pw.isEmpty()|| eml.isEmpty()) {
                    pwd.setError("You must enter all the information required");
                    email.setError("You must enter all the information required");
                } else if (eml.equals("admin") && pw.equals("5T5ptQ") ){
                    startActivity( new Intent(MainActivity.this, WelcomeActivity.class));
                } else if (!pw.isEmpty() && !eml.isEmpty()){
                    myFirebaseAuth.signInWithEmailAndPassword(eml,pw).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Loggin Error", Toast.LENGTH_SHORT).show();
                            }else{
                                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                            }
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this, "Loggin Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }
    protected void onStart(){
        super.onStart();
        myFirebaseAuth.addAuthStateListener(myListener);
    }
}
