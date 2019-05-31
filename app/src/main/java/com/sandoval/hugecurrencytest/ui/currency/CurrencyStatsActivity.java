package com.sandoval.hugecurrencytest.ui.currency;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.google.android.material.snackbar.Snackbar;
import com.sandoval.hugecurrencytest.R;
import com.sandoval.hugecurrencytest.adapter.CurrencyRecyclerAdapter;
import com.sandoval.hugecurrencytest.internal.di.components.AppComponent;
import com.sandoval.hugecurrencytest.internal.di.components.DaggerCurrencyComponent;
import com.sandoval.hugecurrencytest.internal.mvp.presenter.CurrencyStatsPresenter;
import com.sandoval.hugecurrencytest.internal.mvp.view.CurrencyStatsView;
import com.sandoval.hugecurrencytest.realm.Currency;
import com.sandoval.hugecurrencytest.ui.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyStatsActivity extends BaseActivity implements CurrencyStatsView {

    @Inject
    CurrencyStatsPresenter presenter;

    @BindView(R.id.pb)
    ProgressBar pb;

    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    @BindView(R.id.cv_chart)
    CardView cvChart;

    @BindView(R.id.rv_currency)
    RecyclerView rvCurrency;

    @BindView(R.id.lc_currency)
    LineChart lcCurrency;

    private CurrencyRecyclerAdapter adapter;
    private LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_stats);
        ButterKnife.bind(this);
        presenter.setView(this);
        setTitle(getString(R.string.app_name));
        presenter.initChart(lcCurrency);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadData(7);
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerCurrencyComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showLoading() {
        tvNoData.setVisibility(View.INVISIBLE);
        rvCurrency.setVisibility(View.INVISIBLE);
        cvChart.setVisibility(View.INVISIBLE);
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
        Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showContent() {
        pb.setVisibility(View.INVISIBLE);
        tvNoData.setVisibility(View.INVISIBLE);
        rvCurrency.setVisibility(View.VISIBLE);
        cvChart.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmpty() {
        pb.setVisibility(View.INVISIBLE);
        rvCurrency.setVisibility(View.INVISIBLE);
        cvChart.setVisibility(View.INVISIBLE);
        tvNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadChartData(LineData data) {
        lcCurrency.setData(data);
        lcCurrency.animateX(2000, Easing.EasingOption.EaseInOutQuart);
        lcCurrency.invalidate();
    }

    @Override
    public void populateList(List<Currency> data) {
        adapter = new CurrencyRecyclerAdapter(data, CurrencyRecyclerAdapter.SIMPLE_WRAP, true);
        rvCurrency.setLayoutManager(manager);
        rvCurrency.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnClickListener(new CurrencyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                presenter.selectCurrency(position);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
        presenter = null;
    }
}

