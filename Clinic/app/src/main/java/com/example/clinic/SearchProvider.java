package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchProvider extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    static ProviderInformation pInfo;
    EditText providerName;
    TextView textView16;
    TextView textView17;
    String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_provider);
        myRef = FirebaseDatabase.getInstance().getReference("Users/list_of_providers");
        mAuth = FirebaseAuth.getInstance();

        providerName = findViewById(R.id.providerName);
        textView16 = findViewById(R.id.textView16);
        textView17 = findViewById(R.id.textView17);

        final Button providerSearch = findViewById(R.id.search);
        providerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target = providerName.getText().toString();
                if (target.equals("")){
                    Toast.makeText(SearchProvider.this, "Invalid Name Try again", Toast.LENGTH_SHORT).show();
                    return;
                }
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
        });

        final Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchProvider.this, WelcomePatientActivity.class));
            }
        });


    }
    private void showData(DataSnapshot dataSnapshot) {
        ArrayList<String> unList = new ArrayList<String>();
        ArrayList<String> deuxList = new ArrayList<String>();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            pInfo = new ProviderInformation();
            pInfo.setName(ds.child("name").getValue().toString());
            unList.add(pInfo.getName());
            for (DataSnapshot ds2:ds.child("ava").getChildren()){
                pInfo.setAvas(ds2.getValue().toString());
            }
            deuxList.add(pInfo.getAvas());
        }
        int count = 0;
        for (String elem: unList){
            if(elem.equals(target)){
                textView16.setText(target);
                textView17.setText(deuxList.get(count));
                break;
            }
            count++;
        }
    }
}
