package com.sandoval.hugecurrencytest.internal.mvp.presenter;

import com.sandoval.hugecurrencytest.internal.MVP;
import com.sandoval.hugecurrencytest.internal.mvp.model.CurrencyModel;
import com.sandoval.hugecurrencytest.internal.mvp.view.CurrencyCalculatorView;
import com.sandoval.hugecurrencytest.realm.Currency;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CurrencyCalculatorPresenter extends MVP.Presenter<CurrencyCalculatorView> {

    private CurrencyModel currencyModel;
    private List<Currency> currencies = new ArrayList<>();
    private int posOfCurrency = 10;
    private DecimalFormat df = new DecimalFormat("#.00");
    private boolean fromUsd = true;

    @Inject
    public CurrencyCalculatorPresenter(CurrencyModel currencyModel) {
        this.currencyModel = currencyModel;
    }

    public void loadData() {
        getView().showLoading();
        currencyModel.getExchangeRatesData(new MVP.Model.OnDataLoaded<List<Currency>>() {
            @Override
            public void onSuccess(List<Currency> data) {
                currencies.clear();
                currencies.addAll(data);
                getView().showContent();
                getView().populateList(data);
                getView().changeCurrency(currencies.get(posOfCurrency).getCurrencyCode());
            }

            @Override
            public void onFail(String error) {
                getView().showEmpty();
                getView().showError(error);
            }
        });
    }

    public void swapCurrencies() {
        fromUsd = !fromUsd;
        getView().swapCurrencies();
    }

    public void selectCurrency(int position) {
        posOfCurrency = position;
        getView().changeCurrency(currencies.get(posOfCurrency).getCurrencyCode());
    }

    /**
     *
     * @param money
     * Need to doble check the convertion, i thinnk theres a bug with the calculation :'(
     */
    public void convert(Double money) {
        if (currencies.isEmpty()) return;
        if (fromUsd) {
            getView().showConverted(Double.parseDouble(df.format((money /
                    Double.valueOf(currencies.get(posOfCurrency).getMedianRate()) *
                    currencies.get(posOfCurrency).getUnitValue()))));
        } else {
            getView().showConverted(Double.parseDouble(df.format((money *
                    Double.valueOf(currencies.get(posOfCurrency).getMedianRate()) /
                    currencies.get(posOfCurrency).getUnitValue()))));
        }
    }

    public boolean isConverted() {
        return fromUsd;
    }

    @Override
    public void destroy() {
        currencies.clear();
        posOfCurrency = 0;
        fromUsd = true;
    }
}
