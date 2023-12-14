package com.example.goldzakatcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnHomeCalc, btnHomeAbout, btnHomeHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHomeCalc = (Button) findViewById(R.id.btnHomeCalc);
        btnHomeAbout = (Button) findViewById(R.id.btnHomeAbout);
        btnHomeHelp = (Button) findViewById(R.id.btnHomeHelp);
        btnHomeCalc.setOnClickListener(this);
        btnHomeAbout.setOnClickListener(this);
        btnHomeHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnHomeCalc) {
            Intent toCalcIntent = new Intent(this, CalcActivity.class);
            startActivity(toCalcIntent);
        } else if (v == btnHomeAbout) {
            Intent toAboutIntent = new Intent(this, AboutActivity.class);
            startActivity(toAboutIntent);
        } else if (v == btnHomeHelp) {
            Intent toHelpIntent = new Intent(this, HelpActivity.class);
            startActivity(toHelpIntent);
        }
    }
}