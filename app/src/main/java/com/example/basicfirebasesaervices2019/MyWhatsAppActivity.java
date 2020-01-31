package com.example.basicfirebasesaervices2019;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MyWhatsAppActivity extends MainMenu {
    EditText textToSend;        TextView textSent, textReceived;
    FirebaseDatabase database;  DatabaseReference sendTextRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_whatsapp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // The Firebase DB connexion
        database = FirebaseDatabase.getInstance();
        sendTextRef = database.getReference("text");
        // The elements of the layout
        textToSend = findViewById(R.id.textToSend);
        textReceived = findViewById(R.id.textReceived);
        textSent = findViewById(R.id.textSent);
        // Read a message from the database
        sendTextRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                textReceived.append(value + "\n");
                playRingTone();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                textReceived.setText("Failed to read value.");
                playRingTone();
            }
        });
    }

    public void sendText(View view) {
        String aux = " > " + textToSend.getText().toString();
        // Send the text and the author
        sendTextRef.setValue(aux);
        // Add to the sent messages
        aux += "\n";
        textSent.append(aux);
        // Hide the keyboard after sending message
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void cleanText(View view) {
        textSent.setText("");
        textReceived.setText("");
    }

    public void cleanTextLine(View view) {
        textToSend.setText("");
    }

    public void playRingTone() {
        //fetch current Ringtone
        Uri currentRintoneUri =
                RingtoneManager.getActualDefaultRingtoneUri(this.getApplicationContext(),
                        RingtoneManager.TYPE_NOTIFICATION);
        Ringtone currentRingtone = RingtoneManager.getRingtone(this, currentRintoneUri);
        //play current Ringtone
        currentRingtone.play();
    }


}