package com.sandoval.hugecurrencytest.ui.currency;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.sandoval.hugecurrencytest.R;
import com.sandoval.hugecurrencytest.adapter.CurrencyRecyclerAdapter;
import com.sandoval.hugecurrencytest.internal.di.components.AppComponent;
import com.sandoval.hugecurrencytest.internal.di.components.DaggerCurrencyComponent;
import com.sandoval.hugecurrencytest.internal.mvp.presenter.ExchangeRatesPresenter;
import com.sandoval.hugecurrencytest.internal.mvp.view.ExchangeRatesView;
import com.sandoval.hugecurrencytest.realm.Currency;
import com.sandoval.hugecurrencytest.ui.BaseActivity;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeRatesActivity extends BaseActivity implements ExchangeRatesView {

    @Inject
    ExchangeRatesPresenter presenter;

    @BindView(R.id.pb)
    ProgressBar pb;

    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    @BindView(R.id.rv_exchange__rates)
    RecyclerView rvExchangeRates;

    private CurrencyRecyclerAdapter adapter;
    @SuppressLint("WrongConstant")
    private LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchanges_rates);
        ButterKnife.bind(this);
        presenter.setView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setTitle(getString(R.string.exchange_rate_title) + LocalDate.now().toString());
        } else {
            setTitle(getString(R.string.exchange_rate_title_today));
        }
    }

    @Override
    public void populateList(List<Currency> data) {
        adapter = new CurrencyRecyclerAdapter(data, CurrencyRecyclerAdapter.DETAIL);
        rvExchangeRates.setLayoutManager(manager);
        rvExchangeRates.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.INVISIBLE);
        rvExchangeRates.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {
        Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showContent() {
        pb.setVisibility(View.INVISIBLE);
        tvNoData.setVisibility(View.INVISIBLE);
        rvExchangeRates.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmpty() {
        pb.setVisibility(View.INVISIBLE);
        tvNoData.setVisibility(View.VISIBLE);
        rvExchangeRates.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerCurrencyComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadData();
    }
}
