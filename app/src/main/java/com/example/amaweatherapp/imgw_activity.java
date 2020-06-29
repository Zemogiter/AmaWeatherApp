package com.example.amaweatherapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class imgw_activity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgw_activity);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://danepubliczne.imgw.pl/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Stacja>> call = jsonPlaceHolderApi.getStacje();

        call.enqueue(new Callback<List<Stacja>>() {
            @Override
            public void onResponse(Call<List<Stacja>> call, Response<List<Stacja>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Stacja> synop = response.body();
                for (Stacja post : synop) {
                    String content = "";
                    content += "ID Stacji: " + Stacja.getId_stacji() + "\n";
                    content += "Miasto: " + Stacja.getStacja() + "\n";
                    content += "Data pomiaru: " + Stacja.getData_pomiaru() + "\n";
                    content += "Godzina pomiaru: " + Stacja.getGodzina_pomiaru() + "\n\n";

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
