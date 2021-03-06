package com.example.amaweatherapp.airly;

import com.example.amaweatherapp.utils.Distance;

import org.json.JSONException;
import org.json.JSONObject;

public class Station {
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String distanceTo(Station s) {
        return "Odleglosc miedzy stacjami: " + Math.round(Distance.calculate(s.getLatitude(), this.getLatitude(), s.getLongitude(), this.getLongitude()) * 100) / 100 + "m" + "\n";
    }

    public static Double hasDoubleValue(JSONObject obj, String key) throws JSONException {
        String name = obj.getString("name");
        if (key.equals(name)) {
            return Double.valueOf(obj.get("value").toString());
        }else {
            return 0.0;
        }
    }
}
