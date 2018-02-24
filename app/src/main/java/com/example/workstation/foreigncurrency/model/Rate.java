package com.example.workstation.foreigncurrency.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by workstation on 08/02/2018.
 */

public class Rate {
    public double rate;
    public String currency;
    public int imageID;
    public String currencySymbol;

    public Rate(double rate, String currency, String currencySymbol, int imageID) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        this.rate = Double.parseDouble(formatter.format(1/rate));
        this.currency = currency;
        this.imageID = imageID;
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencySymbol(){
        return currencySymbol;
    }
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
