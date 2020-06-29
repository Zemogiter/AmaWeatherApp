package com.example.amaweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.amaweatherapp.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        button = (Button) findViewById(R.id.buttonIMGW);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIMGW();
            }
        });
        button = (Button) findViewById(R.id.buttonAIRLY);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAIRLY();
            }
        });
        button = (Button) findViewById(R.id.buttonMAP);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMAP();
            }
        });
        button = (Button) findViewById(R.id.buttonSELECTCITY);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCITY();
            }
        });
        button = (Button) findViewById(R.id.buttonSELECTCITY2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCITY2();
            }
        });
        button = (Button) findViewById(R.id.buttonOPTIONS);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOPTIONS();
            }
        });
    }
    public void openIMGW(){
        Intent intent = new Intent(this, imgw_activity.class);
        startActivity(intent);
    }
    public void openAIRLY(){
        Intent intent = new Intent(this, AirLyLoginActivity.class);
        startActivity(intent);
    }
    public void openMAP(){
        Intent intent = new Intent(this, map.class);
        startActivity(intent);
    }
    public void openCITY(){
        Intent intent = new Intent(this, select_city.class);
        startActivity(intent);
    }
    public void openCITY2(){
        Intent intent = new Intent(this, select_city_2.class);
        startActivity(intent);
    }
    public void openOPTIONS(){
        Intent intent = new Intent(this, optionsPage.class);
        startActivity(intent);
    }
}