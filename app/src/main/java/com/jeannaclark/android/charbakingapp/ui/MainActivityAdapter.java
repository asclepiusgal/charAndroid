package com.jeannaclark.android.charbakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jeannaclark.android.charbakingapp.R;
import com.jeannaclark.android.charbakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by birdy on 6/26/17.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> {

    private ArrayList<Recipe> recipes;
    private Context mContext;
    private DatabaseReference mDatabase;
    private Boolean mIsFavorite;

    public MainActivityAdapter(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public MainActivityViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main_recipe_card,
                viewGroup, false);
        MainActivityViewHolder mainActivityViewHolder = new MainActivityViewHolder(view);
        mContext = viewGroup.getContext();
        return mainActivityViewHolder;
    }

    @Override
    public void onBindViewHolder(MainActivityViewHolder mainActivityViewHolder, int position) {
        MainActivityViewHolder mViewHolder = (MainActivityViewHolder) mainActivityViewHolder;
        configureRecipeViewHolder(mViewHolder, position);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    private void configureRecipeViewHolder(final MainActivityViewHolder viewHolder, final int position) {
        Recipe recipe = (Recipe) recipes.get(position);

        if (recipe != null) {
            viewHolder.recipeNameView.setText(recipe.getName());

            Picasso.with(mContext)
                    .load(recipe.getImage())
                    .placeholder(R.drawable.ic_menu_gallery)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(viewHolder.imageView);

            viewHolder.favoriteButton.setSelected(recipe.isFavorite());
        }
    }

    public class MainActivityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recipe_card_favorite_button)
        ToggleButton favoriteButton;
        @BindView(R.id.recipe_card_image_view)
        ImageView imageView;
        @BindView(R.id.recipe_card_recipe_name)
        TextView recipeNameView;
        @BindView(R.id.recipe_card_share_button)
        Button shareButton;
        @BindView(R.id.recipe_card_view)
        CardView cardView;

        private Recipe mRecipe;

        public MainActivityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.recipe_card_share_button)
        void shareRecipe() {

            DatabaseReference database = FirebaseDatabase.getInstance().getReference()
                    .child(Integer.toString(getAdapterPosition()));

            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mRecipe = dataSnapshot.getValue(Recipe.class);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, mRecipe.getName());
                    mContext.startActivity(shareIntent);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("\n\n\nin onCancelled()", "Failed to read value.", error.toException());
                }
            });
        }

        @OnClick(R.id.recipe_card_favorite_button)
        void favoriteRecipe() {

            mDatabase = FirebaseDatabase.getInstance().getReference()
                    .child(Integer.toString(getAdapterPosition()) + "/favorite");

            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mIsFavorite = (Boolean) dataSnapshot.getValue();
                    mDatabase.setValue(!mIsFavorite);
                    Toast.makeText(mContext, "Favorite toggled", Toast.LENGTH_LONG).show();
                    // TODO: toggle is not updating the stateSelected() XML color / notifyingAdapter
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("\n\n\nin onCancelled()", "Failed to read value.", error.toException());
                }
            });
        }

        @OnClick(R.id.recipe_card_image_view)
        void recipeDetailFlowIntent() {
            Uri firebaseChildUri = Uri.parse("FirebaseDatabase.getInstance().getReference()" +
                    ".child(Integer.toString(getAdapterPosition()))");
            Intent intent = new Intent(mContext, DetailFlowListActivity.class)
                    .setData(firebaseChildUri);
            mContext.startActivity(intent);
        }
    }
}