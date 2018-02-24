package com.example.workstation.foreigncurrency.model;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

public class Rates{
    // Avustralya Dolari
    @SerializedName("AUD")
    public Double aUD;
    // Kanada Dolari
    @SerializedName("CAD")
    public Double cAD;
    // isvicre frangi
    @SerializedName("CHF")
    public Double cHF;
    //ingiliz sterlini
    @SerializedName("GBP")
    public Double gBP;
    // euro
    @SerializedName("EUR")
    public Double eUR;
    // Turk lirasi
    @SerializedName("TRY")
    public Double tRY;
    @SerializedName("USD")
    public Double uSD;

    public Double getaUD() {
        return aUD;
    }

    public Double getcAD() {
        return cAD;
    }

    public Double getcHF() {
        return cHF;
    }

    public Double getgBP() {
        return gBP;
    }

    public Double getjPY() {
        return eUR;
    }

    public Double gettRY() {
        return tRY;
    }

    public Double getuSD() {
        return uSD;
    }
}
