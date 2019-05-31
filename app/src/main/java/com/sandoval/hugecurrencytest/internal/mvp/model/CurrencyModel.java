package com.sandoval.hugecurrencytest.internal.mvp.model;


import android.util.Log;

import com.sandoval.hugecurrencytest.internal.MVP;
import com.sandoval.hugecurrencytest.networking.FixerApi;
import com.sandoval.hugecurrencytest.realm.Currency;
import com.sandoval.hugecurrencytest.support.DateTimeManager;
import com.sandoval.hugecurrencytest.support.NetworkManager;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CurrencyModel implements MVP.Model {

    private static final String TAG = CurrencyModel.class.getSimpleName();

    private FixerApi fixerApi;
    private Realm realm;
    List<Currency> currencyCached = new ArrayList<>();
    private NetworkManager networkManager;

    public CurrencyModel(FixerApi fixerApi, Realm realm, NetworkManager networkManager) {
        this.fixerApi = fixerApi;
        this.realm = realm;
        this.networkManager = networkManager;
    }

    /**
     * Get exchange rates for today
     *
     * @param onDataLoaded
     */
    public void getExchangeRatesData(final OnDataLoaded<List<Currency>> onDataLoaded) {
        getExchangeRatesData(onDataLoaded, 1);
    }

    /**
     * * Get exchange rates for a specific date
     *
     * @param onDataLoaded
     * @param history
     */
    public void getExchangeRatesData(final OnDataLoaded<List<Currency>> onDataLoaded, int history) {
        if (networkManager.isConnected()) {
            for (LocalDate date = LocalDate.now(); date.isAfter(LocalDate.now().minusDays(history));
                 date = date.minusDays(1)) {

                String dateQuery = DateTimeManager.parseFromDate(date, DateTimeManager.HNB_DATE);
                final LocalDate finalDate = date;
                fixerApi.listExchangeRates(dateQuery)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Currency>>() {
                                       @Override
                                       public void onCompleted() {}

                                       @Override
                                       public void onError(Throwable e) {
                                           Log.d(TAG, "onError() called with: " + "e = [" + e + "]");
                                           currencyCached.clear();
                                           currencyCached = checkIfCached(finalDate.toDate());
                                           if (currencyCached.isEmpty()) {
                                               onDataLoaded.onFail(e.toString());
                                           } else {
                                               onDataLoaded.onSuccess(currencyCached);
                                           }
                                       }

                                       @Override
                                       public void onNext(List<Currency> currencies) {
                                           Log.d(TAG, "onNext() called with: " + "currencies = ["
                                                   + currencies + "]");
                                           cache(currencies, finalDate.toDate());
                                           onDataLoaded.onSuccess(currencies);
                                       }
                                   }
                        );
            }
        } else {
            for (LocalDate date = LocalDate.now(); date.isAfter(LocalDate.now().minusDays(history));
                 date = date.minusDays(1)) {

                currencyCached.clear();
                currencyCached = checkIfCached(date.toDate());
                if (currencyCached.isEmpty()) {
                    onDataLoaded.onFail("No cached data and not Internet");
                    break;
                } else {
                    onDataLoaded.onSuccess(currencyCached);
                }
            }
        }
    }

    private RealmResults<Currency> checkIfCached(Date date) {
        return realm.where(Currency.class)
                .equalTo("downloadDate", date)
                .findAll();
    }

    @Override
    public void cache(List<?> data, Date date) {
        List<Currency> currencies = new ArrayList<>((Collection<? extends Currency>) data);
        realm.beginTransaction();
        for (Currency currency : currencies) {
            currency.setDownloadDate(date);
            realm.copyToRealm(currency);
        }
        realm.commitTransaction();
    }

    @Override
    public void clearCache() {
        try {
            realm.delete(Currency.class);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}