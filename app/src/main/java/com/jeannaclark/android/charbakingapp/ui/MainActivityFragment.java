package com.jeannaclark.android.charbakingapp.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Recipe> mRecipeList = new ArrayList<>();

    @BindView(R.id.activity_main_recycler_view)
    RecyclerView recyclerView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main_recycler_view, container, false);
        ButterKnife.bind(this, view);

        getRecipeData();

        MainActivityAdapter mRecipeAdapter = new MainActivityAdapter(mRecipeList);
        recyclerView.setAdapter(mRecipeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Log.v("in Fragment", "in main activity");
        return view;
    }

    @OnClick(R.id.recipe_card_share_button)
    void shareRecipe() {
        //TODO: insert button functions
        Toast.makeText(getActivity(), "Insert share button functionality",
                Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.recipe_card_favorite_button)
    void favoriteRecipe() {
        //TODO: insert button functions
        Toast.makeText(getActivity(), "Insert favorite toggle button functionality",
                Toast.LENGTH_SHORT).show();
    }

    public void getRecipeData() {
        mRecipeList.clear();
        DatabaseReference myRecipeRef = FirebaseDatabase.getInstance().getReference();
        myRecipeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.e("\n\n\n\ndata: ", child.toString());

                    Recipe recipe = child.getValue(Recipe.class);
                    mRecipeList.add(recipe);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", databaseError.getMessage());
                Log.e("\n\n\n\nError!!!", "THIS IS FIREBASE.");
            }
        });
    }
}