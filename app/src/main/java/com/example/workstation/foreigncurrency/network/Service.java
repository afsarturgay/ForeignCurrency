package com.example.workstation.foreigncurrency.network;

import com.example.workstation.foreigncurrency.model.CurrencyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by workstation on 07/02/2018.
 */

public interface Service {
    @GET("latest?base=TRY")
    Call<CurrencyResponse> getLatestCurrency();
}
