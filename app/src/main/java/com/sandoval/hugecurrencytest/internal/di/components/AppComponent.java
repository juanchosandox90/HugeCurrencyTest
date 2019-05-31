package com.sandoval.hugecurrencytest.internal.di.components;

import com.google.gson.Gson;
import com.sandoval.hugecurrencytest.internal.di.modules.AppModule;
import com.sandoval.hugecurrencytest.internal.di.modules.DataModule;
import com.sandoval.hugecurrencytest.internal.di.modules.NetworkModule;
import com.sandoval.hugecurrencytest.internal.di.scope.PerApp;
import com.sandoval.hugecurrencytest.networking.FixerApi;
import com.sandoval.hugecurrencytest.support.NetworkManager;

import dagger.Component;
import io.realm.Realm;

@PerApp
@Component(modules = {AppModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent {
    Gson gson();

    Realm realm();

    FixerApi fixerApi();

    NetworkManager networkManage();
}
