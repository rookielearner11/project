package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onSubmit(View view){
        EditText emlI = (EditText) findViewById(R.id.emlI);
        EditText nameI = (EditText) findViewById(R.id.nameI);
        EditText pwdI = (EditText) findViewById(R.id.pwdI);
        EditText pwdI2 = (EditText) findViewById(R.id.pwdI2);
    }
}
