package com.sandoval.hugecurrencytest.ui.currency;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.sandoval.hugecurrencytest.R;
import com.sandoval.hugecurrencytest.ui.BaseActivity;

public class CurrencyCalculatorActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_calculator);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
