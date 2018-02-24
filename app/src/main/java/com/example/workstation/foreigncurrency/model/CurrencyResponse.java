package com.example.workstation.foreigncurrency.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by workstation on 07/02/2018.
 */

public class CurrencyResponse {
    @SerializedName("base")
    public String base;
    @SerializedName("date")
    public String date;
    @SerializedName("rates")
    public Rates rates;
}
