package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class RegisterActivity extends AppCompatActivity {
    EditText emailR, pwd1R,pwd2R,nameR,roleR;
    Button btn;
    FirebaseAuth myFirebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myFirebaseAuth = FirebaseAuth.getInstance();
        nameR = findViewById(R.id.name);
        emailR = findViewById(R.id.email);
        pwd1R = findViewById(R.id.pwd1);
        pwd2R = findViewById(R.id.pwd2);
        roleR = findViewById(R.id.role);
        btn = findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = nameR.getText().toString();
                final String email = emailR.getText().toString();
                final String pwd1 = pwd1R.getText().toString();
                final String pwd2 = pwd2R.getText().toString();
                final String role = roleR.getText().toString();
                if(! pwd1.equals(pwd2)){
                    pwd1R.setError("Passwords you entered are not matched");
                    pwd2R.setError("Passwords you entered are not matched");
                } else if (name.isEmpty() || pwd1.isEmpty() || pwd2.isEmpty() || email.isEmpty() || role.isEmpty()){
                    nameR.setError("You must enter all the information required");
                    pwd1R.setError("You must enter all the information required");
                    pwd2R.setError("You must enter all the information required");
                    emailR.setError("You must enter all the information required");
                    roleR.setError("You must enter all the information required");
                } else{
                    myFirebaseAuth.createUserWithEmailAndPassword(email,pwd1).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Error Occoured!", Toast.LENGTH_SHORT).show();
                            }else{
                                String eml = email.replace(".","SMARTSCOTT");
                                DatabaseReference myRef1 = database.getReference("users/"+eml+"/email");
                                myRef1.setValue(email);

                                DatabaseReference myRef2 = database.getReference("users/"+eml+"/password");
                                myRef2.setValue(pwd1);

                                DatabaseReference myRef3 = database.getReference("users/"+eml+"/role");
                                myRef3.setValue(role);

                                DatabaseReference myRef4 = database.getReference("users/"+eml+"/name");
                                myRef4.setValue(name);
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });

    }
}
