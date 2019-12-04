package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchProvider extends AppCompatActivity {

    TextView providerName;
    private  String userID;
    private FirebaseAuth mAuth;
    static ProviderInformation pInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_provider);

        providerName = findViewById(R.id.providerName);

        final Button providerSearch = findViewById(R.id.search);
        providerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void showData(DataSnapshot dataSnapshot) {
        ArrayList<String> unList = new ArrayList<String>();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            pInfo = new ProviderInformation();
            pInfo.setName(ds.child(userID).getValue(UserInformation.class).getName());
            unList.add(pInfo.getName());
        }
    }
}
