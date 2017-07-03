package com.jeannaclark.android.charbakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jeannaclark.android.charbakingapp.R;
import com.jeannaclark.android.charbakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by birdy on 6/26/17.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> {

    private ArrayList<Recipe> recipes;
    private Context mContext;
    private int mPosition;
    private MainActivityViewHolder mViewHolder;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

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
        mPosition = position;
        mViewHolder = viewHolder;

        if (recipe != null) {
            mViewHolder.recipeNameView.setText(recipe.getName());

            Picasso.with(mContext)
                    .load(recipe.getImage())
                    .placeholder(R.drawable.ic_menu_gallery)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(mViewHolder.imageView);

            mViewHolder.favoriteButton.setChecked(recipe.isFavorite());
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

        public MainActivityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.recipe_card_share_button)
        void shareRecipe() {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("text/plain");
            //TODO: update placeholder to send recipe data
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Recipe placeholder");
            mContext.startActivity(shareIntent);
            Log.v("share button: ", "sending intent");
        }

        @OnCheckedChanged(R.id.recipe_card_favorite_button)
        void favoriteRecipe(CompoundButton button, boolean isChecked) {
            //TODO: fix listener bug: automatically updates favorite to isChecked & !isChecked consecutively
            mDatabase.child(mPosition + "/favorite").setValue(isChecked);
            Log.v("favorite updated: ", Boolean.toString(isChecked));
            mViewHolder.favoriteButton.setChecked(isChecked);
        }
    }
}
