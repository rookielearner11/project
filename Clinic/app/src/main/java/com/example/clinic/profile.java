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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
public class Profile extends AppCompatActivity {
    FirebaseAuth myFirebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    EditText phone;
    EditText add;
    EditText company;
    EditText gd;
    EditText licensed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myFirebaseAuth = FirebaseAuth.getInstance();
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();


        phone = findViewById(R.id.phone);
        add = findViewById(R.id.add);
        company = findViewById(R.id.company);
        gd = findViewById(R.id.gd);
        licensed = findViewById(R.id.licensed);

        Button update = findViewById(R.id.update);
        Button back = findViewById(R.id.back);
        Button out = findViewById(R.id.out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String phoneN = phone.getText().toString();
                String address = add.getText().toString();
                String companyN = company.getText().toString();
                String generalD = gd.getText().toString();
                String lic = licensed.getText().toString();


                    if (phoneN.equals("")){
                    phone.setError("This information is mandatory");
                }else if (address.equals("")){
                    add.setError("This information is mandatory");
                }else if (companyN.equals("")){
                    company.setError("This information is mandatory");
                }else{
                    String user_id = myFirebaseAuth.getCurrentUser().getUid();
                    DatabaseReference myRef1 = database.getReference().child("Users").child(user_id).child("phone");
                    myRef1.setValue(phoneN);

                    DatabaseReference myRef2 = database.getReference().child("Users").child(user_id).child("address");
                    myRef2.setValue(address);

                    DatabaseReference myRef3 = database.getReference().child("Users").child(user_id).child("companyName");
                    myRef3.setValue(companyN);

                    DatabaseReference myRef4 = database.getReference().child("Users").child(user_id).child("GeneralDescription");
                    myRef4.setValue(generalD);

                    DatabaseReference myRef5 = database.getReference().child("Users").child(user_id).child("licensed");
                    myRef5.setValue(lic);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String phoneN = phone.getText().toString();
                String address = add.getText().toString();
                String companyN = company.getText().toString();
                if (phoneN.equals("")){
                    phone.setError("This information is mandatory");
                }else if (address.equals("")){
                    add.setError("This information is mandatory");
                }else if (companyN.equals("")){
                    company.setError("This information is mandatory");
                }else {
                    startActivity(new Intent(Profile.this, WelcomeActivity.class));
                }
            }
        });

        out.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String phoneN = phone.getText().toString();
                String address = add.getText().toString();
                String companyN = company.getText().toString();
                if (phoneN.equals("")){
                    phone.setError("This information is mandatory");
                }else if (address.equals("")){
                    add.setError("This information is mandatory");
                }else if (companyN.equals("")){
                    company.setError("This information is mandatory");
                }else {
                    mAuth.signOut();
                    startActivity(new Intent(Profile.this, MainActivity.class));
                }
            }
        });
    }
}
