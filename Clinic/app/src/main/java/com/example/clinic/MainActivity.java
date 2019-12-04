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
import android.os.StrictMode;
import android.sax.StartElementListener;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText email, pwd;
    Button signBtn, regBtn;
    FirebaseAuth myFirebaseAuth;
    static int emlh;
    String user_id;
    FirebaseDatabase database;
    DatabaseReference rrole;
    String role;


    private FirebaseAuth.AuthStateListener myListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFirebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
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
                    startActivity( new Intent(MainActivity.this, Admin.class));
                } else if (!pw.isEmpty() && !eml.isEmpty()){
                    myFirebaseAuth.signInWithEmailAndPassword(eml,pw).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Loggin Error", Toast.LENGTH_SHORT).show();
                            }else{
                                user_id = myFirebaseAuth.getCurrentUser().getUid();
                                rrole = database.getReference("Users/"+user_id+"/role");
                                readFromDatabaseR();
                                if (role == null){
                                    Toast.makeText(MainActivity.this,"You operated too fast. Please Try Again", Toast.LENGTH_SHORT).show();
                                }
                                else if (role.equals("Employee")){
                                    startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                                }else if (role.equals("Patient")){
                                    startActivity(new Intent(MainActivity.this, WelcomePatientActivity.class));
                                }

                            }
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String eml = email.getText().toString();
                final String pw = pwd.getText().toString();
                if (eml.equals("") || pw.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter your email and password first, then click sign up button :)", Toast.LENGTH_SHORT).show();
                    return;
                }

                myFirebaseAuth.createUserWithEmailAndPassword(eml,pw).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Error Occoured!", Toast.LENGTH_SHORT).show();
                        }else{
                            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                        }
                    }
                });
            }
        });

    }
    protected void onStart(){
        super.onStart();
        myFirebaseAuth.addAuthStateListener(myListener);
    }
    public void readFromDatabaseR(){
        rrole.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                role = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}