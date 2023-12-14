package com.example.goldzakatcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    TextView tvWeight, tvType, tvValue, tvUruf, tvTotalValue, tvPayable, resultOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tvWeight = findViewById(R.id.tvWeight);
        tvType = findViewById(R.id.tvType);
        tvValue = findViewById(R.id.tvValue);
        tvUruf = findViewById(R.id.tvUruf);
        tvTotalValue = findViewById(R.id.tvTotalValue);
        tvPayable = findViewById(R.id.tvPayable);
        resultOutput = findViewById(R.id.resultOutput);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.display_toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Calculation Result");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extras = getIntent().getExtras();

        double weight = extras.getDouble("weight");
        double minus = extras.getDouble("minus");
        double value = extras.getDouble("value");
        double uruf = extras.getDouble("uruf");
        double payable = extras.getDouble("payable");
        double total = extras.getDouble("total");

        tvWeight.setText(String.format("%.2f g", weight));

        if(value == 85){
            tvType.setText("Keep");
        } else {
            tvType.setText("Wear");
        }

        tvValue.setText(String.format("RM %.2f", value));

        tvUruf.setText(String.format(String.valueOf(uruf)));

        double TotalValue = weight * value;
        tvTotalValue.setText(String.format("RM %.2f", TotalValue));

        tvPayable.setText(String.format("RM %.2f", payable));

        resultOutput.setText(String.format("= RM %.2f", total));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
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
}