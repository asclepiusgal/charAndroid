package com.jeannaclark.android.charbakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jeannaclark.android.charbakingapp.model.Recipe;

import java.util.ArrayList;

/**
 * Created by birdy on 7/11/17.
 */

public class CharService extends IntentService {

    public static final String ACTION_VIEW_RECIPE = "com.jeannaclark.android.charbakingapp.action.view_recipe";
    public static final String ACTION_UPDATE_RECIPE_WIDGET = "com.jeannaclark.android.charbakingapp.action.update_recipe_widget";

    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    private ArrayList<Recipe> mRecipeList = new ArrayList<>();

    public CharService() {
        super("CharService");
    }

    public static void startActionViewRecipe(Context context) {
        Intent intent = new Intent(context, CharService.class);
        intent.setAction(ACTION_VIEW_RECIPE);
        context.startService(intent);
    }

    public static void startActionUpdateWidget(Context context) {
        Intent intent = new Intent(context, CharService.class);
        intent.setAction(ACTION_UPDATE_RECIPE_WIDGET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_VIEW_RECIPE.equals(action)) {
                handleActionViewRecipe();
            } else if (ACTION_UPDATE_RECIPE_WIDGET.equals(action)) {
                handleUpdateWidget();
            }
        }
    }

    private void handleActionViewRecipe() {
        // TODO: insert widget functionality to display ingredient list for desired recipe
    }

    private void handleUpdateWidget() {
        // TODO: insert widget functionality to display ingredient list for desired recipe

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Recipe recipe = child.getValue(Recipe.class);
                    mRecipeList.add(recipe);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("\n\n\nin onCancelled()", "Failed to read value.", error.toException());
            }
        });
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, CharWidgetProvider.class));
//        CharWidgetProvider.updateAppWidget(this, appWidgetManager, mRecipeList.get(0).getImage(), appWidgetIds);
    }
}
