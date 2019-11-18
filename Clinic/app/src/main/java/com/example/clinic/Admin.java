package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Admin extends AppCompatActivity {
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;

    FirebaseAuth myFirebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        text1 = findViewById(R.id.tname);
        text2 = findViewById(R.id.temail);
        text3 = findViewById(R.id.serviceText);
        text4 = findViewById(R.id.serviceWorker);
        text5 = findViewById(R.id.serviceRole);

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

        final Button serviceAdd = findViewById(R.id.serviceButton);
        serviceAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String service = text3.getText().toString();
                final String serviceWorker = text4.getText().toString();
                final String serviceRole = text5.getText().toString();

                if(!service.isEmpty() && !serviceWorker.isEmpty() && !serviceRole.isEmpty()) {
                    DatabaseReference myRef1 = database.getReference("services/" + service);
                    DatabaseReference myRef2 = database.getReference("services/" + service + "/Name");
                    DatabaseReference myRef3 = database.getReference("services/" + service + "/Role");

                    myRef1.setValue(service);
                    myRef2.setValue(serviceWorker);
                    myRef3.setValue(serviceRole);

                    Toast.makeText(Admin.this, "Service is added / updated", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Admin.this, "All values must be filled", Toast.LENGTH_SHORT).show();
                }

            }
        });

        final Button deleteService = findViewById(R.id.deleteService);
        deleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String service = text3.getText().toString();
                if(!service.isEmpty()) {
                    DatabaseReference myReference = database.getReference("services/" + service);
                    myReference.removeValue();

                    Toast.makeText(Admin.this, "Service is deleted", Toast.LENGTH_SHORT).show();
                } else {
                    text3.setError("You must enter all the information required");
                }

            }
        });

    }
}