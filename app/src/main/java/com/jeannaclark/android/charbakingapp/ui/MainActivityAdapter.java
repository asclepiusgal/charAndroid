package com.jeannaclark.android.charbakingapp.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.jeannaclark.android.charbakingapp.R;
import com.jeannaclark.android.charbakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by birdy on 6/26/17.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> {

    private ArrayList<Recipe> recipes;
    private Context mContext;

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
        MainActivityViewHolder viewHolder = (MainActivityViewHolder) mainActivityViewHolder;
        configureRecipeViewHolder(viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    private void configureRecipeViewHolder(MainActivityViewHolder viewHolder, int position) {
        Recipe recipe = (Recipe) recipes.get(position);

        if (recipe != null) {
            viewHolder.recipeNameView.setText(recipe.getName());

            Picasso.with(getContext())
                    .load(recipe.getImageURL())
                    .placeholder(R.drawable.ic_menu_gallery)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(viewHolder.imageView);
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

        // TODO: update activity_XML_card_view to use dimen.xml & remove hardcoding

        public MainActivityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
