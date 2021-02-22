package com.example.currencyconv;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface interfaceRetrofit {
    @GET("v6/205bd35a5b5aed3124aa0872/latest/{currency}")
    //@GET("https://free.currconv.com/api/v7/convert")
    Call<JsonObject> getExchangeCurrency(@Path("currency") String currency);
   //Call<JsonObject> getExchangeCurrency(@Query("q") String currency);
}
