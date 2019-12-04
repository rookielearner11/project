package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    FirebaseAuth myFirebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    EditText phone;
    EditText add;
    EditText company;
    EditText db;
    EditText licensed;
    EditText ava;
    String user_id;
    TextView avaV;
    String avaShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final UserInformation uInfo = WelcomeActivity.uInfo();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        avaV = findViewById(R.id.avaV);
        DatabaseReference myRefA = database.getReference("Users/"+user_id);

        myRefA.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                UserInformation info = snapshot.getValue(UserInformation.class);
                DataSnapshot avaSnap = snapshot.child("zzz_availability");
                Iterable<DataSnapshot> avaChilds = new ArrayList<>();
                UserInformation k = new UserInformation();
                for (DataSnapshot ava : avaChilds){
                    String c = ava.getValue(UserInformation.class).toString();
                    k.setAvailability(c);
                }
                avaShow = k.getAvailability();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (avaShow == null){
            avaV.setText("Null");
        }else {
            avaV.setText(avaShow);
        }
        myFirebaseAuth = FirebaseAuth.getInstance();
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        phone = findViewById(R.id.phone);
        add = findViewById(R.id.add);
        company = findViewById(R.id.company);
        db = findViewById(R.id.gd);
        licensed = findViewById(R.id.licensed);
        ava = findViewById(R.id.ava);
        user_id = user.getUid();

        Button update = findViewById(R.id.update);
        Button back = findViewById(R.id.back);
        Button out = findViewById(R.id.out);
        Button addb = findViewById(R.id.addb);
        Button del = findViewById(R.id.del);

        phone.setText(uInfo.getPhone(),TextView.BufferType.EDITABLE);
        add.setText(uInfo.getAddress(),TextView.BufferType.EDITABLE);
        db.setText(uInfo.getSex(),TextView.BufferType.EDITABLE);
        licensed.setText(uInfo.getLicensed(),TextView.BufferType.EDITABLE);
        company.setText(uInfo.getCompany(),TextView.BufferType.EDITABLE);


        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String phoneN = phone.getText().toString();
                String address = add.getText().toString();
                String companyN = company.getText().toString();
                String sex = db.getText().toString();
                String lic = licensed.getText().toString();


                    if (phoneN.equals("")){
                    phone.setError("This information is mandatory");
                }else if (address.equals("")){
                    add.setError("This information is mandatory");
                }else if (companyN.equals("")){
                    company.setError("This information is mandatory");
                }else{
                    user_id = user.getUid();
                    DatabaseReference myRef1 = database.getReference().child("Users").child(user_id).child("phone");
                    myRef1.setValue(phoneN);

                    DatabaseReference myRef2 = database.getReference().child("Users").child(user_id).child("address");
                    myRef2.setValue(address);

                    DatabaseReference myRef3 = database.getReference().child("Users").child(user_id).child("company");
                    myRef3.setValue(companyN);

                    DatabaseReference myRef4 = database.getReference().child("Users").child(user_id).child("sex");
                    myRef4.setValue(sex);

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
                if (phoneN.equals(" ")){
                    phone.setError("This information is mandatory");
                }else if (address.equals(" ")){
                    add.setError("This information is mandatory");
                }else if (companyN.equals(" ")){
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
                if (phoneN.equals(" ")){
                    phone.setError("This information is mandatory");
                }else if (address.equals(" ")){
                    add.setError("This information is mandatory");
                }else if (companyN.equals(" ")){
                    company.setError("This information is mandatory");
                }else {
                    mAuth.signOut();
                    startActivity(new Intent(Profile.this, MainActivity.class));
                }
            }
        });

        addb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String avas = ava.getText().toString();
                DatabaseReference myRef6 = database.getReference("Users/"+user_id+"/zzz_availability/"+avas);
                DatabaseReference myRef7 = database.getReference("Users/list_of_providers/"+WelcomeActivity.employeeN+"/ava/"+avas);
                myRef6.setValue(avas);
                myRef7.setValue(avas);
                Toast.makeText(Profile.this, "Added! Please re-enter this page", Toast.LENGTH_SHORT).show();
            }
        });

        del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final String avas = ava.getText().toString();
                if(!avas.isEmpty()) {
                    DatabaseReference myReference = database.getReference("zzz_availability/" + avas);
                    myReference.removeValue();

                    Toast.makeText(Profile.this, "It is deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Profile.this, "You must enter a vaild time", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
