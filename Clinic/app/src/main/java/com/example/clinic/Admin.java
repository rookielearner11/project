package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }
}
