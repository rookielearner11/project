package com.example.clinic;

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
    private DatabaseReference rname = FirebaseDatabase.getInstance().getReference("name");
    private DatabaseReference remail = FirebaseDatabase.getInstance().getReference("email");
    private DatabaseReference rrole = FirebaseDatabase.getInstance().getReference("role");
    //private DatabaseReference rname = FirebaseDatabase.getInstance().getReference("name");

    TextView text1;

    TextView text2;
    TextView text3;

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
        text3 = findViewById(R.id.trole);
        readFromDatabaseU();
        readFromDatabaseE();
        readFromDatabaseR();






    }

    public void readFromDatabaseU(){
        rname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                uname = dataSnapshot.getValue(String.class);
                Log.d("andy", "Value is: " + uname);
                text1.setText(uname);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("andy", "Failed to read value.", error.toException());
            }
        });
    }
    public void readFromDatabaseE(){
        remail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                email = dataSnapshot.getValue(String.class);
                Log.d("andy", "Value is: " + email);
                text2.setText(email);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("andy", "Failed to read value.", error.toException());
            }
        });
    }
    public void readFromDatabaseR(){
        rrole.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                role = dataSnapshot.getValue(String.class);
                Log.d("andy", "Value is: " + role);
                text3.setText(role);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("andy", "Failed to read value.", error.toException());
            }
        });
    }








}
