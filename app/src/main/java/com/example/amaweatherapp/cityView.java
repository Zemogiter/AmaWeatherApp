package com.example.amaweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class cityView extends AppCompatActivity {
    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_view);

        Intent intent = getIntent();
        String text = intent.getStringExtra(select_city.EXTRA_TEXT);

        TextView cityFieldDescription = (TextView) findViewById(R.id.cityFieldDescription);

        cityFieldDescription.setText(text);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://danepubliczne.imgw.pl/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getStacje();
    }

    private void getStacje() {
        Call<List<Stacja>> call = jsonPlaceHolderApi.getStacje("api/data/synop");

        call.enqueue(new Callback<List<Stacja>>() {
            @Override
            public void onResponse(Call<List<Stacja>> call, Response<List<Stacja>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Stacja> synop = response.body();
                assert synop != null;
                for (Stacja stacja : synop) {
                    String content = "";
                    content += "ID Stacji: " + Stacja.getId_stacji() + "\n";
                    content += "Miasto: " + Stacja.getStacja() + "\n";
                    content += "Data pomiaru: " + Stacja.getData_pomiaru() + "\n";
                    content += "Godzina pomiaru: " + Stacja.getGodzina_pomiaru() + "\n";
                    content += "Temperatura: " + Stacja.getTemperatura() + "\n";
                    content += "Prędkość wiatru: " + Stacja.getPredkosc_wiatru() + "\n";
                    content += "Kierunek wiatru: " + Stacja.getKierunek_wiatru() + "\n";
                    content += "Wilgotność względna: " + Stacja.getWilgodnosc_wzgledna() + "\n";
                    content += "Suma opadu: " + Stacja.getSuma_opadu() + "\n";
                    content += "Ciśnienie w hektopaskalach: " + Stacja.getCisnienie() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Stacja>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

}
