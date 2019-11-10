package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class WelcomeActivity extends AppCompatActivity {
    //private FirebaseAuth mAuth;// ...
    // Initialize Firebase Auth

    FirebaseAuth mAuth;
    private DatabaseReference emlp = FirebaseDatabase.getInstance().getReference(String.valueOf(MainActivity.emlh));

    TextView text1;
    TextView text2;

    //public String uid;
    public String uname;
    public String email;
    public String role;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mAuth = FirebaseAuth.getInstance();
        final Button btn_logout= findViewById(R.id.btn_signout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });
        text1 = findViewById(R.id.tname);
        text2 = findViewById(R.id.temail);


        emlp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //// Code needs to be changed
//                uname = dataSnapshot.child("name").getValue().toString();
//                email = dataSnapshot.child("email").getValue().toString();
//                role = dataSnapshot.child("role").getValue().toString();
//                text1.setText("Hello "+uname);
//                text2.setText("You have signed in as " + role);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        //////       If you sign in as administrator:
        //////       Bottom lines of the code you should not add any operation that not belongs to administrator
        //////       Under those codes                     August's working zone!

    }








}
