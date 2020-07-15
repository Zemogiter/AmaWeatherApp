package com.example.amaweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class select_city extends AppCompatActivity {
    private EditText mEditText;
    public static final String EXTRA_TEXT = "com.example.amaweatherapp.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        Button button = (Button) findViewById(R.id.cityButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCityView();
            }
        });
    }
    public void openCityView() {
        EditText cityField = (EditText) findViewById(R.id.cityField);
        String text = cityField.getText().toString();
        Intent intent = new Intent(this, cityView.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }
}