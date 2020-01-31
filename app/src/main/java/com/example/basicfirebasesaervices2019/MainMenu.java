package com.example.basicfirebasesaervices2019;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity (intent);
            return true;
        }
       if (id == R.id.database) {
            Intent intent = new Intent(this, DatabaseActivity.class);
            startActivity (intent);
            return true;
       }
        if (id == R.id.myWhatsApp) {
            Intent intent = new Intent(this, MyWhatsAppActivity.class);
            startActivity (intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
