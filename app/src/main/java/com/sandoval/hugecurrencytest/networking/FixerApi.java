package com.sandoval.hugecurrencytest.networking;

import java.util.Currency;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FixerApi {

    String BASE_URL = "http://data.fixer.io/api/";

    @GET("latest?")
    Observable<List<Currency>> listExchangeRates(@Query("access_key=") String access_key);
}
