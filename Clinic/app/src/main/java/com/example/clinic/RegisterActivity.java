package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText emailR, pwd1R,pwd2R,nameR,roleR;
    Button btn;
    FirebaseAuth myFirebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myFirebaseAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                return;
            }
        };
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        nameR = findViewById(R.id.name);
        emailR = findViewById(R.id.email);
        pwd1R = findViewById(R.id.pwd1);
        pwd2R = findViewById(R.id.pwd2);
        roleR = findViewById(R.id.role);
        btn = findViewById(R.id.btn_delete);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameR.getText().toString();
                final String email = emailR.getText().toString();
                final String pwd1 = pwd1R.getText().toString();
                final String pwd2 = pwd2R.getText().toString();
                final String role = roleR.getText().toString();

                if(!pwd1.equals(pwd2)){
                    pwd1R.setError("Passwords you entered are not matched");
                    pwd2R.setError("Passwords you entered are not matched");
                } else if (name.isEmpty() || pwd1.isEmpty() || pwd2.isEmpty() || email.isEmpty() || role.isEmpty()){
                    nameR.setError("You must enter all the information required");
                    pwd1R.setError("You must enter all the information required");
                    pwd2R.setError("You must enter all the information required");
                    emailR.setError("You must enter all the information required");
                    roleR.setError("You must enter all the information required");
                } else{
                    String user_id = myFirebaseAuth.getCurrentUser().getUid();
                    //DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
//                    myFirebaseAuth.createUserWithEmailAndPassword(email,pwd1).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (!task.isSuccessful()){
//                                Toast.makeText(RegisterActivity.this, "Error Occoured!", Toast.LENGTH_SHORT).show();
//                            }else if (role.equals("Employee")){
                                DatabaseReference myRef1 = database.getReference().child("Users").child(user_id).child("email");
                                myRef1.setValue(email);

                                DatabaseReference myRef2 = database.getReference().child("Users").child(user_id).child("password");
                                myRef2.setValue(pwd1);

                                DatabaseReference myRef3 = database.getReference().child("Users").child(user_id).child("role");
                                myRef3.setValue(role);

                                DatabaseReference myRef4 = database.getReference().child("Users").child(user_id).child("name");
                                myRef4.setValue(name);
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            //}else{
//                                DatabaseReference myRef1 = database.getReference("acc/patient/"+email.hashCode()+"/email");
//                                myRef1.setValue(email);
//
//                                DatabaseReference myRef2 = database.getReference("acc/patient/"+email.hashCode()+"/password");
//                                myRef2.setValue(pwd1);
//
//                                DatabaseReference myRef3 = database.getReference("acc/employee/"+email.hashCode()+"/role");
//                                myRef3.setValue(role);
//
//                                DatabaseReference myRef4 = database.getReference("acc/patient/"+email.hashCode()+"/name");
//                                myRef4.setValue(name);
//                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//                            }
                        //}
                    //});
                }
            }
        });

    }
}
