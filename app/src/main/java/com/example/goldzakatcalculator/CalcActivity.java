package com.example.goldzakatcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {

    EditText goldWeight, goldValue;
    RadioButton goldKeep, goldWear;
    Button result, reset;
    ImageButton rate;
    TextView resultOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);


        Toolbar calcToolbar = (Toolbar) findViewById(R.id.calc_toolbar);
        setSupportActionBar(calcToolbar);
        getSupportActionBar().setTitle("Calculator");

        goldWeight = (EditText) findViewById(R.id.goldWeight);
        goldValue = (EditText) findViewById(R.id.goldValue);
        goldKeep = (RadioButton) findViewById(R.id.goldKeep);
        goldWear = (RadioButton) findViewById(R.id.goldWear);
        resultOutput = (TextView) findViewById(R.id.resultOutput);
        result = (Button) findViewById(R.id.result);
        reset = (Button) findViewById(R.id.reset);
        rate = (ImageButton) findViewById(R.id.rate);

        result.setOnClickListener(this);
        reset.setOnClickListener(this);
        rate.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    @Override
    public void onClick(View v) {
        if(v == rate) {
            String rateId = "https://www.goodreturns.in/gold-rates/malaysia.html";
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rateId));
            startActivity(rateIntent);
        }

        if(v == reset) {
            goldWeight.setText("");
            goldValue.setText("");
            goldKeep.setChecked(false);
            goldWear.setChecked(false);
        }

        if(v == result) {
            if (goldWeight.getText().toString().isEmpty() || goldValue.getText().toString().isEmpty() || (!goldKeep.isChecked() && !goldWear.isChecked())) {
                resultOutput.setText("Please complete the form to proceed");
                return;
            }

            double minus = 0, uruf, payable;
            double weight = Double.parseDouble(goldWeight.getText().toString());
            double value = Double.parseDouble(goldValue.getText().toString());

            if (goldKeep.isChecked()) {

                minus = 85;
            } else  {

                minus = 200;
            }

            uruf = weight - minus;

            if (uruf <= 0) {
                uruf = 0;
            }

            payable = uruf * value;

            double total = payable * 0.025;

            //resultOutput.setText("RM " + String.format("%.2f", total));

            Intent resultIntent = new Intent(this, DisplayActivity.class);
            Bundle extras = new Bundle();
            extras.putDouble("weight", weight);
            extras.putDouble("value", value);
            extras.putDouble("uruf", uruf);
            extras.putDouble("minus", minus);
            extras.putDouble("payable", payable);
            extras.putDouble("total", total);
            resultIntent.putExtras(extras);
            startActivity(resultIntent);
        }
    }
}