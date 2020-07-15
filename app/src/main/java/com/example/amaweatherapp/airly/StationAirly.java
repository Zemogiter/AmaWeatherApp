package com.example.amaweatherapp.airly;

import android.location.Location;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amaweatherapp.utils.JsonTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class StationAirly extends AppCompatActivity{
    private static int stationId;
    private static Double distanceTo;
    private static Double pm1;
    private static Double pm10;
    private static Double pm25;
    private static Double pressure;
    private static Double humidity;
    private static Double temperature;
    private static Double airQualityIndex;

    private static String country;
    private static String city;
    private static String street;
    private static String number;
    private static String displayAddress1;
    private static String displayAddress2;

    private static String measurementTime;

    public StationAirly() {
        distanceTo = Double.MAX_VALUE;
    }

    public Object FindStation(Location userLocation) throws ExecutionException, InterruptedException, JSONException {
        double userLatitude = userLocation.getLatitude();
        double userLongitude = userLocation.getLongitude();

        String result = new JsonTask().execute("https://airapi.airly.eu/v2/installations/nearest?lat=" + userLatitude + "&lng=" + userLongitude + "&maxDistanceKM=50&maxResults=-1&apikey=g27tKAi3DTSt9VIWcIzbnTMC00MGWKS8").get();
        JSONArray stationsTable = new JSONArray(result);

        if (result != null) {
            for (int i = 0; i < stationsTable.length(); ++i) {
                JSONObject stationsTableJSONObject = stationsTable.getJSONObject(i);
                JSONObject location = stationsTableJSONObject.getJSONObject("location");
                Double testLatitude = location.getDouble("latitude");
                Double testLongitude = location.getDouble("longitude");
                boolean isAirly = stationsTableJSONObject.getBoolean("airly");
                int sensorId = stationsTableJSONObject.getInt("id");

            }
            Update();
        }
        return this;
    }

    void Update() throws ExecutionException, InterruptedException, JSONException {
        String result = new JsonTask().execute("https://airapi.airly.eu/v2/measurements/installation/" + "?apikey=g27tKAi3DTSt9VIWcIzbnTMC00MGWKS8" + "&installationId=" + getStationId()).get();
        JSONArray objj = new JSONObject(result).getJSONObject("current").getJSONArray("values");

        for (int i = 0; i < objj.length(); ++i) {
            JSONObject measurement = objj.getJSONObject(i);
            if (("PM1").equals(measurement.get("name")))
                setPm1( com.example.amaweatherapp.airly.Station.hasDoubleValue(measurement, "PM1"));
            if (("PM25").equals(measurement.get("name")))
                setPm25( com.example.amaweatherapp.airly.Station.hasDoubleValue(measurement, "PM25"));
            if (("PM10").equals(measurement.get("name")))
                setPm10( com.example.amaweatherapp.airly.Station.hasDoubleValue(measurement, "PM10"));
            if (("PRESSURE").equals(measurement.get("name")))
                setPressure( com.example.amaweatherapp.airly.Station.hasDoubleValue(measurement, "PRESSURE"));
            if (("HUMIDITY").equals(measurement.get("name")))
                setHumidity( com.example.amaweatherapp.airly.Station.hasDoubleValue(measurement, "HUMIDITY"));
            if (("TEMPERATURE").equals(measurement.get("name")))
                setTemperature( com.example.amaweatherapp.airly.Station.hasDoubleValue(measurement, "TEMPERATURE"));
        }

        Double index = Double.valueOf(new JSONObject(result).getJSONObject("current").getJSONArray("indexes").getJSONObject(0).get("value").toString());
        setAirQualityIndex(index);

        roundPm();
        roundHumidity();
        roundTemperature();
        roundAirQualityIndex();

        roundDistanceTo();
    }

    private void roundPm() {
        pm1 = Math.round(pm1 * 100.0) / 100.0;
        pm10 = Math.round(pm10 * 100.0) / 100.0;
        pm25 = Math.round(pm25 * 100.0) / 100.0;
    }

    void roundHumidity() {
        humidity = Math.round(humidity * 100.0) / 100.0;
    }

    void roundTemperature() {
        temperature = Math.round(temperature * 10.0) / 10.0;
    }

    void roundDistanceTo() {
        distanceTo = (double) Math.round(distanceTo) * 100 / 100;
    }

    void roundAirQualityIndex() {
        airQualityIndex = Math.round(airQualityIndex * 100.0) / 100.0;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Lokalizacja: " + getCity());
        builder.append("\n" + "Adres: " + getStreet() + " " + getNumber());
        builder.append("\n" + "AQI: " + getAirQualityIndex());
        builder.append("\n" + "PM1: " + getPm1() + "µg/m³");
        builder.append("\n" + "PM2.5: " + getPm25() + "µg/m³");
        builder.append("\n" + "PM10: " + getPm10() + "µg/m³");
        builder.append("\n" + "Ciśnienie: " + getPressure() + "hPa");
        builder.append("\n" + "Wilgotność: " + getHumidity() + "%");
        builder.append("\n" + "Temperatura: " + getTemperature() + "°C");
        builder.append("\n" + "Odleglość: " + getDistanceTo() + "m");

        return builder.toString();
    }

    public static int getStationId() {
        return stationId;
    }

    public static void setStationId(int stationId) {
        StationAirly.stationId = stationId;
    }

    public static Double getDistanceTo() {
        return distanceTo;
    }

    public static void setDistanceTo(Double distanceTo) {
        StationAirly.distanceTo = distanceTo;
    }

    public static Double getPm1() {
        return pm1;
    }

    public static void setPm1(Double pm1) {
        StationAirly.pm1 = pm1;
    }

    public static Double getPm10() {
        return pm10;
    }

    public static void setPm10(Double pm10) {
        StationAirly.pm10 = pm10;
    }

    public static Double getPm25() {
        return pm25;
    }

    public static void setPm25(Double pm25) {
        StationAirly.pm25 = pm25;
    }

    public static Double getPressure() {
        return pressure;
    }

    public static void setPressure(Double pressure) {
        StationAirly.pressure = pressure;
    }

    public static Double getHumidity() {
        return humidity;
    }

    public static void setHumidity(Double humidity) {
        StationAirly.humidity = humidity;
    }

    public static Double getTemperature() {
        return temperature;
    }

    public static void setTemperature(Double temperature) {
        StationAirly.temperature = temperature;
    }

    public static Double getAirQualityIndex() {
        return airQualityIndex;
    }

    public static void setAirQualityIndex(Double airQualityIndex) {
        StationAirly.airQualityIndex = airQualityIndex;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        StationAirly.country = country;
    }

    public static String getDisplayAddress2() {
        return displayAddress2;
    }

    public static void setDisplayAddress2(String displayAddress2) {
        StationAirly.displayAddress2 = displayAddress2;
    }

    public static String getDisplayAddress1() {
        return displayAddress1;
    }

    public static void setDisplayAddress1(String displayAddress1) {
        StationAirly.displayAddress1 = displayAddress1;
    }

    public static String getNumber() {
        return number;
    }

    public static void setNumber(String number) {
        StationAirly.number = number;
    }

    public static String getStreet() {
        return street;
    }

    public static void setStreet(String street) {
        StationAirly.street = street;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        StationAirly.city = city;
    }

    public static String getMeasurementTime() {
        return measurementTime;
    }

    public static void setMeasurementTime(String measurementTime) {
        StationAirly.measurementTime = measurementTime;
    }
}