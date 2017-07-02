package com.jeannaclark.android.charbakingapp.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by birdy on 7/1/17.
 */

public class CharContentProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private CharDatabaseHelper mOpenHelper;

    static final int RECIPE = 100;
    static final int STEP = 101;
    static final int INGREDIENT = 102;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CharDatabaseContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, CharDatabaseContract.PATH_RECIPE, RECIPE);
        matcher.addURI(authority, CharDatabaseContract.PATH_INGREDIENT, INGREDIENT);
        matcher.addURI(authority, CharDatabaseContract.PATH_STEP, STEP);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new CharDatabaseHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case RECIPE:
                return CharDatabaseContract.RecipeEntry.CONTENT_TYPE;
            case INGREDIENT:
                return CharDatabaseContract.IngredientEntry.CONTENT_TYPE;
            case STEP:
                return CharDatabaseContract.StepEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case RECIPE: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        CharDatabaseContract.RecipeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case INGREDIENT: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        CharDatabaseContract.IngredientEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case STEP: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        CharDatabaseContract.StepEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case RECIPE: {
                long _id = db.insert(CharDatabaseContract.RecipeEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = CharDatabaseContract.RecipeEntry.buildRecipeUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case INGREDIENT: {
                long _id = db.insert(CharDatabaseContract.IngredientEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = CharDatabaseContract.IngredientEntry.buildIngredientsUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case STEP: {
                long _id = db.insert(CharDatabaseContract.StepEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = CharDatabaseContract.StepEntry.buildStepsUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int update(
            Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case RECIPE:
                Log.v("in update recipe: ", uri.toString());
                rowsUpdated = db.update(CharDatabaseContract.RecipeEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        if (null == selection) selection = "1";
        switch (match) {
            case RECIPE:
                rowsDeleted = db.delete(CharDatabaseContract.RecipeEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }
}