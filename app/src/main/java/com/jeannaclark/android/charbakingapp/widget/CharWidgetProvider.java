package com.jeannaclark.android.charbakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.jeannaclark.android.charbakingapp.R;
import com.jeannaclark.android.charbakingapp.ui.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class CharWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.char_widget);

        // TODO: widget is currently  only opening the app; add service with recipe cards
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.widget_image, pendingIntent);

//        Intent recipeIntent = new Intent(context, CharService.class);
//        recipeIntent.setAction(CharService.ACTION_VIEW_RECIPE);
//        PendingIntent recipePendingIntent = PendingIntent.getService(
//                context,
//                0,
//                recipeIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setOnClickPendingIntent(R.id.widget_image, recipePendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

