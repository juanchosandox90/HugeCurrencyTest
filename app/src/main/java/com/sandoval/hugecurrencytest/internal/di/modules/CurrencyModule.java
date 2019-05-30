package com.sandoval.hugecurrencytest.internal.di.modules;

import com.sandoval.hugecurrencytest.internal.di.scope.PerCurrency;
import com.sandoval.hugecurrencytest.networking.FixerApi;
import com.sandoval.hugecurrencytest.support.NetworkManager;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class CurrencyModule {

/*    @Provides
    @PerCurrency
    CurrencyModel provideCurrencyModel(FixerApi fixerApi, Realm realm, NetworkManager networkManager){
        return new CurrencyModel(fixerApi, realm, networkManager);
    }*/
}

