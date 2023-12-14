package com.example.goldzakatcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar aboutToolbar;
    Button toGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutToolbar = findViewById(R.id.about_toolbar);
        setSupportActionBar(aboutToolbar);
        getSupportActionBar().setTitle("Developer's Page");

        toGithub = (Button) findViewById(R.id.toGithub);
        toGithub.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
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
        String linkId = "https://www.youtube.com/";
        Intent linkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkId));
        startActivity(linkIntent);
    }
}