package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email, pwd;
    Button signBtn, regBtn;
    FirebaseAuth myFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        signBtn = findViewById(R.id.signBtn);
        regBtn = findViewById(R.id.regBtn);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eml = email.getText().toString();
                String pw = pwd.getText().toString();
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

    }
}
