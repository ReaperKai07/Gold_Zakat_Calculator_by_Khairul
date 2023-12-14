package com.example.goldzakatcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar settingsToolbar;
    Button btnToCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        settingsToolbar = findViewById(R.id.help_toolbar);
        setSupportActionBar(settingsToolbar);
        getSupportActionBar().setTitle("Help");

        btnToCalc = (Button) findViewById(R.id.btnToCalc);
        btnToCalc.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            super.onBackPressed();
        }

        if (item.getItemId() == R.id.item_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Please use my application");
            startActivity(Intent.createChooser(shareIntent, null));
            return true;
        }  else if (item.getItemId() == R.id.item_about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
        } else if (item.getItemId() == R.id.item_help) {
            Intent settingsIntent = new Intent(this, HelpActivity.class);
            startActivity(settingsIntent);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Intent calcIntent = new Intent(this, CalcActivity.class);
        startActivity(calcIntent);
    }
}