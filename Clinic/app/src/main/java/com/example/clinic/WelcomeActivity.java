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


public class WelcomeActivity extends AppCompatActivity {


    TextView textview;
    private Button logout;
    public String uname;
    public String email;
    public String role;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String eml2 = MainActivity.eml.replace(".","SMARTSCOTT");
    DatabaseReference rname = database.getReference("users/"+eml2+"/name");
    DatabaseReference rrole = database.getReference("users/"+eml2+"/role");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        logout = findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });



        textview = findViewById(R.id.textView8);
        textview.setText("Welcome " + uname + ", "+
                "\n You signed in as "+ role +".");

    }
    public void readFromDatabaseU(){
        rname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                uname = dataSnapshot.getValue(String.class);
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
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("andy", "Failed to read value.", error.toException());
            }
        });
    }

}
