package com.southsai.countertimesimple;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;


public class CounterTimeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_time);
        FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fm.beginTransaction().replace(R.id.container, new FragmentCounterTime()).commit();
        }
    }
}
