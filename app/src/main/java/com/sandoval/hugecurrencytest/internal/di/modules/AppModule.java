package com.sandoval.hugecurrencytest.internal.di.modules;

import android.app.Application;

import com.sandoval.hugecurrencytest.internal.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    Application providesApplication() {
        return mApplication;
    }

}
