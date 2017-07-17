package com.jeannaclark.android.charbakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import com.jeannaclark.android.charbakingapp.R;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

public class DetailFlowListActivity extends AppCompatActivity {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: create data table for ingredients
        // TODO: create vertical stepper for steps
        // TODO: implement heterogeneous viewholder with data table on top of stepper
        // TODO: implement detail flow activity for 'continue' button for recipe steps

        setContentView(R.layout.activity_detail_flow_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.recipe_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
