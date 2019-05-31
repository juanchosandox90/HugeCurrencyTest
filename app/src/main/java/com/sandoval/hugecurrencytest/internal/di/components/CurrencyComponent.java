package com.sandoval.hugecurrencytest.internal.di.components;

import com.sandoval.hugecurrencytest.internal.di.modules.CurrencyModule;
import com.sandoval.hugecurrencytest.internal.di.scope.PerCurrency;
import com.sandoval.hugecurrencytest.internal.mvp.model.CurrencyModel;
import com.sandoval.hugecurrencytest.ui.currency.CurrencyCalculatorActivity;
import com.sandoval.hugecurrencytest.ui.currency.CurrencyStatsActivity;
import com.sandoval.hugecurrencytest.ui.currency.ExchangeRatesActivity;

import dagger.Component;

@PerCurrency
@Component(dependencies = AppComponent.class, modules = CurrencyModule.class)
public interface CurrencyComponent {
    void inject(CurrencyCalculatorActivity currencyCalculatorActivity);
    void inject(ExchangeRatesActivity exchangeRatesActivity);
    void inject(CurrencyStatsActivity currencyStatsActivity);
    CurrencyModel currencyModel();
}
