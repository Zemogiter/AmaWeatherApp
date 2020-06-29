package com.example.amaweatherapp;

public class Stacja {

    private static int id_stacji;
    private static String stacja;
    private static String data_pomiaru;
    private static int godzina_pomiaru;
    private static int temperatura;
    private static int predkosc_wiatru;
    private static int kierunek_wiatru;
    private static int wilgodnosc_wzgledna;
    private static int suma_opadu;
    private static int cisnienie;

    public static int getId_stacji() {
        return id_stacji;
    }

    public static String getStacja() {
        return stacja;
    }

    public static String getData_pomiaru() {
        return data_pomiaru;
    }

    public static int getGodzina_pomiaru() {
        return godzina_pomiaru;
    }

    public static int getTemperatura() {
        return temperatura;
    }

    public static int getPredkosc_wiatru() {
        return predkosc_wiatru;
    }

    public static int getKierunek_wiatru() {
        return kierunek_wiatru;
    }

    public static int getWilgodnosc_wzgledna() {
        return wilgodnosc_wzgledna;
    }

    public static int getSuma_opadu() {
        return suma_opadu;
    }

    public static int getCisnienie() {
        return cisnienie;
    }
}
