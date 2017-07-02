package com.jeannaclark.android.charbakingapp.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jeannaclark.android.charbakingapp.R;
import com.jeannaclark.android.charbakingapp.model.Recipe;

import java.util.ArrayList;

public class MainActivityFragment extends Fragment {

    private ArrayList<Recipe> mRecipeList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MainActivityAdapter mRecipeAdapter;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main_recycler_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.activity_main_recycler_view);
        mRecipeAdapter = new MainActivityAdapter(mRecipeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mRecipeAdapter);

        getRecipeData();

        TextView emptyText = view.findViewById(R.id.empty_text);
        ImageView emptyImage = view.findViewById(R.id.empty_image);


            recyclerView.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.VISIBLE);
            emptyImage.setVisibility(View.VISIBLE);


//        if (mRecipeList.isEmpty()) {
//            recyclerView.setVisibility(View.GONE);
//            emptyText.setVisibility(View.VISIBLE);
//            emptyImage.setVisibility(View.VISIBLE);
//        } else {
//            recyclerView.setVisibility(View.VISIBLE);
//            emptyText.setVisibility(View.GONE);
//            emptyImage.setVisibility(View.GONE);
//        }

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

    public void updateRecipes() {
        getRecipeData();
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), "Data refreshed", Toast.LENGTH_LONG).show();
    }

    public void getRecipeData() {
        mRecipeList.clear();
        mRecipeAdapter.notifyDataSetChanged();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Recipe recipe = child.getValue(Recipe.class);
                    mRecipeList.add(recipe);
                } mRecipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("\n\n\nin onCancelled()", "Failed to read value.", error.toException());
            }
        });
    }
}