package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        EditText add = findViewById(R.id.add);
        EditText company = findViewById(R.id.company);
        EditText gd = findViewById(R.id.gd);
        EditText licensed = findViewById(R.id.licensed);
        Button update = findViewById(R.id.update);
        Button back = findViewById(R.id.back);
        Button out = findViewById(R.id.out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });
    }
}
