package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin extends AppCompatActivity {
    TextView text1;
    TextView text2;
    TextView text3;
    FirebaseAuth myFirebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        text1 = findViewById(R.id.tname);
        text2 = findViewById(R.id.temail);
        text3 = findViewById(R.id.serviceText);
        if (MainActivity.emlh == 92668751 ){
            text1.setText("Hello administrator");
            text2.setText("You have signed in as administrator");
        }
        final Button btn_logout= findViewById(R.id.btn_signout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, MainActivity.class));
            }
        });

        final Button service = findViewById(R.id.serviceButton);
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String service = text3.getText().toString();
                if(!service.isEmpty()) {
                    DatabaseReference myReference = database.getReference("clinic-d1/services");
                    myReference.setValue(service);
                } else {
                    text3.setError("You must enter all the information required");
                }

            }
        });
    }
}
