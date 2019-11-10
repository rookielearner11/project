package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Admin extends AppCompatActivity {
    TextView text1;
    TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        text1 = findViewById(R.id.tname);
        text2 = findViewById(R.id.temail);
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
    }
}
