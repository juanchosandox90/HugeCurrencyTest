package com.sandoval.hugecurrencytest.internal.mvp.view;

import com.sandoval.hugecurrencytest.internal.MVP;
import com.sandoval.hugecurrencytest.realm.Currency;

import java.util.List;

public interface ExchangeRatesView extends MVP.View {

    void populateList(List<Currency> data);
}
