package com.sandoval.hugecurrencytest.networking;

import com.sandoval.hugecurrencytest.realm.Currency;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FixerApi {

    /*
    Having problems to fetch the fixer.io and painting the data still dont know why
    fetching with another API to check everything works fine.
     */
    /*
    String BASE_URL = "http://data.fixer.io/api/";

    @GET("latest?")
    Observable<Currency> listExchangeRates(@Query("access_key=") String access_key);*/

    String BASE_URL = "http://hnbex.eu/api/v1/";

    @GET("rates/daily/")
    Observable<List<Currency>> listExchangeRates(@Query("date") String date);


}
