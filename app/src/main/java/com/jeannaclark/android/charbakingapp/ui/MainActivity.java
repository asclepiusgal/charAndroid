package com.jeannaclark.android.charbakingapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.facebook.stetho.Stetho;
import com.jeannaclark.android.charbakingapp.R;
import com.squareup.leakcanary.LeakCanary;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private static final String DETAILFRAGMENT_TAG = "Detail Fragment Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        MainActivityFragment mainActivityFragment = (MainActivityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_main_fragment_container);
        if (null != mainActivityFragment) {
            mainActivityFragment.getRecipeData();
        }

        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);
        LeakCanary.install(getApplication());
    }
}
