package com.sandoval.hugecurrencytest.internal.mvp.view;

import com.github.mikephil.charting.data.LineData;
import com.sandoval.hugecurrencytest.internal.MVP;
import com.sandoval.hugecurrencytest.realm.Currency;

import java.util.List;

public interface CurrencyStatsView extends MVP.View {

    void populateList(List<Currency> data);

    void loadChartData(LineData data);

}
