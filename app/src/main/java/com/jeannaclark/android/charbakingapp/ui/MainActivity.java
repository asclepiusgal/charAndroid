package com.jeannaclark.android.charbakingapp.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.jeannaclark.android.charbakingapp.R;
import com.jeannaclark.android.charbakingapp.model.Recipe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // TODO: develop tablet UI XML + twoPane heterogeneous fragments
    // TODO: add onSavedInstanceState
    // TODO: add UI + battery performance data to readme

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }

        MainActivityFragment mainActivityFragment = new MainActivityFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main_fragment_container, mainActivityFragment)
                .commit();

        Stetho.initializeWithDefaults(this);
//        LeakCanary.install(getApplication());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void doSearch(String query) {
        ArrayList<Recipe> queryResults = new ArrayList<>();

        // TODO: insert firebase query on search submission & update adapter
        Toast.makeText(getApplicationContext(), "Your search query: " + query, Toast.LENGTH_LONG)
                .show();
    }
}