package com.sandoval.hugecurrencytest.internal.mvp.presenter;

import com.sandoval.hugecurrencytest.internal.MVP;
import com.sandoval.hugecurrencytest.internal.mvp.model.CurrencyModel;
import com.sandoval.hugecurrencytest.internal.mvp.view.ExchangeRatesView;
import com.sandoval.hugecurrencytest.realm.Currency;

import java.util.List;

import javax.inject.Inject;

public class ExchangeRatesPresenter extends MVP.Presenter<ExchangeRatesView> {


    private CurrencyModel currencyModel;

    @Inject
    public ExchangeRatesPresenter(CurrencyModel currencyModel) {
        this.currencyModel = currencyModel;
    }

    public void loadData() {
        getView().showLoading();
        currencyModel.getExchangeRatesData(new MVP.Model.OnDataLoaded<List<Currency>>() {
            @Override
            public void onSuccess(List<Currency> data) {
                getView().showContent();
                getView().populateList(data);
            }

            @Override
            public void onFail(String error) {
                getView().showEmpty();
                getView().showError(error);
            }
        });
    }

    @Override
    public void destroy() {

    }
}
