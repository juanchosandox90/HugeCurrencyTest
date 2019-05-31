package com.sandoval.hugecurrencytest.internal.di.modules;


import android.app.Application;

import com.google.gson.Gson;
import com.sandoval.hugecurrencytest.internal.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DataModule {

    private Application aplication;

    public DataModule(Application application) {
        this.aplication = application;
    }

    @Provides
    @PerApp
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @PerApp
    Realm provideReal() {
        return Realm.getDefaultInstance();
    }
}
