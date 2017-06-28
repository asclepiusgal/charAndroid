package com.jeannaclark.android.charbakingapp.ui;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jeannaclark.android.charbakingapp.R;
import com.jeannaclark.android.charbakingapp.model.Recipe;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Recipe> mRecipeList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    static final String URI_MAIN = "Main URI:";
    private Uri mUri;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(MainActivityFragment.URI_MAIN);
        }

        View view = inflater.inflate(R.layout.activity_main_recycler_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.activity_main_recycler_view);
        MainActivityAdapter mRecipeAdapter = new MainActivityAdapter(mRecipeList);
        recyclerView.setAdapter(mRecipeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //TODO: insert emptyView here
//        recyclerView.setEmptyView(view.findViewById(R.id.empty_garden));

        mSwipeRefreshLayout = (SwipeRefreshLayout)
                view.findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.material_blue_500,
                R.color.material_pink_A400, R.color.material_green_700);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateRecipes();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (null != mUri) {
            getRecipeData();
        }
        super.onActivityCreated(savedInstanceState);
    }

    public void updateRecipes() {
        getRecipeData();
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), "Data refreshed", Toast.LENGTH_LONG).show();
    }

    public void getRecipeData() {
        mRecipeList.clear();
        DatabaseReference myRecipeRef = FirebaseDatabase.getInstance().getReference();
        myRecipeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //TODO: connect to Firebase data
                    Recipe recipe = child.getValue(Recipe.class);
                    mRecipeList.add(recipe);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", databaseError.getMessage());
            }
        });
    }
}