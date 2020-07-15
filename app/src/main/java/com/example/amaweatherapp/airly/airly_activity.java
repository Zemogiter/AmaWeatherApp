package com.example.amaweatherapp.airly;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.amaweatherapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class airly_activity extends AppCompatActivity {
    public Button b;
    public TextView t1, t2, t3;

    private LocationManager locationManager;
    private LocationListener listener;

    public Location mLastLocation;

    public Handler mHandler;
    public Handler mHandler2;
    public Handler mHandler3;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mHandler = new Handler();
        mHandler2 = new Handler();
        mHandler3 = new Handler();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.airly_activity);

        t1 = (TextView) findViewById(R.id.textView1);
        b = (Button) findViewById(R.id.button);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                runUpdaterService(location);

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.d("Response: ", "> Getting data from last location");
                    runUpdaterService(mLastLocation);
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 10)
            runServices();
    }

    void buttonListener() {
        b.setOnClickListener((View view) -> {
            Log.d("Response: ", "> Button Pressed");
            runServices();
        });
    }

    static ExecutorService updaterService = Executors.newSingleThreadExecutor();

    public class updaterThread implements Runnable {
        private Location userLocation;
        private StationAirly Airly = new StationAirly();
        private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        updaterThread(Location userLocation) {
            this.userLocation = userLocation;
        }

        @Override
        public void run() {
            try {
                this.Airly = (StationAirly) new StationAirly().FindStation(userLocation);
            } catch (Exception e) {
                e.printStackTrace();
            }
            StationAirly finalAirly = Airly;
            mHandler.post(() -> t1.setText(finalAirly.toString()));
            mHandler3.post(() -> {
                Date currentTime = new Date();
                t1.append("Ostatnia aktualizacja: " + dateFormat.format(currentTime));
            });
        }
    }

    void runServices() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation();
    }

    void runUpdaterService(Location location) {

        updaterService.execute(new updaterThread(location));
    }

}