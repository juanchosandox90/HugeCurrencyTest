package com.sandoval.hugecurrencytest.internal.di.modules;

import android.content.Context;

import com.sandoval.hugecurrencytest.internal.di.scope.PerApp;
import com.sandoval.hugecurrencytest.networking.FixerApi;
import com.sandoval.hugecurrencytest.support.NetworkManager;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sandoval.hugecurrencytest.networking.FixerApi.BASE_URL;

@Module
public class NetworkModule {

    private Context context;

    public NetworkModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerApp
    public NetworkManager networkManager() {
        return new NetworkManager(context);
    }

    @Provides
    @PerApp
    public FixerApi provideFixerApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FixerApi.class);
    }
}
