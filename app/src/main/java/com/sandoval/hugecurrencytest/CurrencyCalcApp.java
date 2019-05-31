package com.sandoval.hugecurrencytest;

import android.app.Application;

import com.sandoval.hugecurrencytest.internal.di.components.AppComponent;
import com.sandoval.hugecurrencytest.internal.di.components.DaggerAppComponent;
import com.sandoval.hugecurrencytest.internal.di.modules.DataModule;
import com.sandoval.hugecurrencytest.internal.di.modules.NetworkModule;

import net.danlew.android.joda.JodaTimeAndroid;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CurrencyCalcApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        initRealmConfiguration();
        JodaTimeAndroid.init(this);
    }

    /**
     * Initialise the injector and create the app graph
     */
    private void initInjector() {
        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule(this))
                .dataModule(new DataModule(this))
                .build();
    }

    /**
     * Initialise the realm configuration
     */
    private void initRealmConfiguration(){

        Realm.init(getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }


    /**
     *
     * @return the AppComponent instance
     */
    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
