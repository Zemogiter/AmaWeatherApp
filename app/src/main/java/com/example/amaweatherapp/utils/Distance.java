package com.example.amaweatherapp.utils;

import android.location.Location;

public class Distance extends Location {
    public Distance(String provider) {
        super(provider);
    }

    public static Double calculate(Location location1, Double lat2, Double lon2) {
        return calculate(location1.getLatitude(), lat2, location1.getLongitude(), lon2);
    }

    public static Double calculate(Double lat1, Double lat2, Double lon1, Double lon2) {
        Location location1 = new Location("");
        location1.setLatitude(lat1);
        location1.setLongitude(lon1);
        Location location2 = new Location("");
        location2.setLatitude(lat2);
        location2.setLongitude(lon2);
        return (double) location1.distanceTo(location2);
    }
}
