package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;
    private ListView mListView;
    static UserInformation uInfo;
    TextView addServiceText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        addServiceText = findViewById(R.id.addService);

        mListView = (ListView) findViewById(R.id.listview);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        final FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        final Button btn_profile = findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, Profile.class));
            }
        });

        final Button btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatabaseReference myReference = mFirebaseDatabase.getReference("Users/" + userID);
                myReference.removeValue();
                user.delete();
                mAuth.signOut();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });
        final Button btn_logout = findViewById(R.id.btn_signout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });

        final Button addService = findViewById(R.id.ButtonAddService);
        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String addServiceTxt = addServiceText.getText().toString();
                if(!addServiceTxt.isEmpty()){
                    DatabaseReference myRef1 = mFirebaseDatabase.getReference("Users/"+ userID + "/services/" + addServiceTxt);

                    myRef1.setValue(addServiceTxt);
                    Toast.makeText(WelcomeActivity.this, addServiceTxt + " Service is added / updated", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(WelcomeActivity.this, "Must use existing service", Toast.LENGTH_SHORT).show();
                }

            }
        });

        final Button removeServicce = findViewById(R.id.ButtonRemoveService);
        removeServicce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String addServiceTxt = addServiceText.getText().toString();
                if(!addServiceTxt.isEmpty()){
                    DatabaseReference myRef2 = mFirebaseDatabase.getReference("Users/"+ userID + "/services/" + addServiceTxt);

                    myRef2.removeValue();
                    Toast.makeText(WelcomeActivity.this,  addServiceTxt + " is removed", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(WelcomeActivity.this, "Must use existing service", Toast.LENGTH_SHORT).show();
                }

            }
        });





        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            uInfo = new UserInformation();
            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getName());
            uInfo.setEmail(ds.child(userID).getValue(UserInformation.class).getEmail());
            uInfo.setRole(ds.child(userID).getValue(UserInformation.class).getRole());
            uInfo.setPhone(ds.child(userID).getValue(UserInformation.class).getPhone());
            uInfo.setSex(ds.child(userID).getValue(UserInformation.class).getSex());
            uInfo.setLicensed(ds.child(userID).getValue(UserInformation.class).getLicensed());
            uInfo.setCompany(ds.child(userID).getValue(UserInformation.class).getCompany());
            uInfo.setAddress(ds.child(userID).getValue(UserInformation.class).getAddress());
            uInfo.setAvailability(ds.child(userID).getValue(UserInformation.class).getAvailability());



            Log.d(TAG, "showData: name: " + uInfo.getName());
            Log.d(TAG, "showData: email: " + uInfo.getEmail());
            Log.d(TAG, "showData: role: " + uInfo.getRole());
            Log.d(TAG, "showData: phone_num: " + uInfo.getPhone());
            Log.d(TAG, "showData: generalDescription: " + uInfo.getSex());
            Log.d(TAG, "showData: Licensed: " + uInfo.getLicensed());
            Log.d(TAG, "showData: CompanyName: " + uInfo.getCompany());
            Log.d(TAG, "showData: address: " + uInfo.getAddress());
            Log.d(TAG, "showData: availabilities: "+ uInfo.getAvailability());


            ArrayList<String> array  = new ArrayList<>();
            array.add(uInfo.getName());
            array.add(uInfo.getEmail());
            array.add(uInfo.getRole());
            array.add(uInfo.getPhone());
            array.add(uInfo.getSex());
            array.add(uInfo.getLicensed());
            array.add(uInfo.getCompany());
            array.add(uInfo.getAddress());
            array.add("Availabilities: " + uInfo.getAvailability());


            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void toastMessage(String message ) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();


    }

    public static UserInformation uInfo(){
        return new UserInformation(uInfo);
    }


}