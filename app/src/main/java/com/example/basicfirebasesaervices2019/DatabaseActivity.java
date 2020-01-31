package com.example.basicfirebasesaervices2019;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseActivity extends MainMenu {

    EditText versionNumber;
    TextView answer, numberN;
    FirebaseDatabase database;
    DatabaseReference myRef, myRefToN ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // The objects
        versionNumber = findViewById(R.id.numberVersion);
        answer =        findViewById(R.id.showNumberVersion);
        // Inc and dec values
        numberN = findViewById(R.id.textN);
        // The connexion to the RTDB
        database =  FirebaseDatabase.getInstance();
        myRef = database.getReference("db_version");
        myRefToN = database.getReference("N");
        // Reading values
        readNumberN();

    }

    public void updateVersionNumber(View view) {
        //  Write a message to the database
        myRef.setValue(versionNumber.getText().toString());
    }

    public void readVersionNumber(View view) {
        // Read a message from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                answer.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                answer.setText("Failed to read value.");
            }
        });
    }

    public void incrementN(View view) {
        readNumberN();
        Long nAux = Long.parseLong(numberN.getText().toString());
        nAux++;
        writeNumberN(nAux);
    }

    public void decrementN(View view) {
        readNumberN();
        Long nAux = Long.parseLong(numberN.getText().toString());
        nAux--;
        writeNumberN(nAux);
    }

    public void readNumberN() { // Put the N value into numberN textView
        // Read a message from the database
        myRefToN = database.getReference("N");
        myRefToN.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long value = dataSnapshot.getValue(Long.class);
                numberN.setText(String.valueOf(value));
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                numberN.setText("Failed to read value.");
            }
        });
    }

    public void writeNumberN(Long N) { // Put the numberN textView value into N
        // Read a message from the database
        myRefToN = database.getReference("N");
        myRefToN.setValue(N);
    }
}