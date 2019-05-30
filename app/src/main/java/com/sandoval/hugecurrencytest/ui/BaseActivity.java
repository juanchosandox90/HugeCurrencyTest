package com.sandoval.hugecurrencytest.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;
import com.sandoval.hugecurrencytest.CurrencyCalcApp;
import com.sandoval.hugecurrencytest.R;
import com.sandoval.hugecurrencytest.internal.di.components.AppComponent;
import com.sandoval.hugecurrencytest.ui.currency.CurrencyCalculatorActivity;

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //For mark the icon with the view for future purposes
        //  markCurrentNavItem();
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        drawerLayout = (DrawerLayout) getLayoutInflater()
                .inflate(R.layout.activity_base, null);
        FrameLayout contentView = (FrameLayout) drawerLayout.findViewById(R.id.activity_content);
        navigationView = (NavigationView) drawerLayout.findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, null, R.string.app_name, R.string.app_name);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getLayoutInflater().inflate(layoutResID, contentView, true);
        super.setContentView(drawerLayout);
        setupComponent(CurrencyCalcApp.getAppComponent());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.currency_calc:
                if(this instanceof CurrencyCalculatorActivity) break;
                startActivity(new Intent(this, CurrencyCalculatorActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                break;
            case R.id.exchange_rate:
                break;
            case R.id.currency_compare:
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }

    protected abstract void setupComponent(AppComponent appComponent);

}
