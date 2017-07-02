package com.jeannaclark.android.charbakingapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by birdy on 7/1/17.
 */

public class CharDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "CharData.db";

    public CharDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //TODO: finish writing SQL statements
        final String SQL_CREATE_RECIPE_TABLE = "CREATE TABLE " + CharDatabaseContract.RecipeEntry.TABLE_NAME
                + " ("
                + CharDatabaseContract.RecipeEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CharDatabaseContract.RecipeEntry.COLUMN_RECIPE_NAME + " TEXT UNIQUE NOT NULL, "
                + CharDatabaseContract.RecipeEntry.COLUMN_IMAGE_URL + " TEXT, "
                + CharDatabaseContract.RecipeEntry.COLUMN_SERVINGS + " INTEGER NOT NULL);";

        final String SQL_CREATE_INGREDIENT_TABLE = "CREATE TABLE " + CharDatabaseContract.IngredientEntry.TABLE_NAME
                + " ("
                + CharDatabaseContract.IngredientEntry.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + CharDatabaseContract.IngredientEntry.COLUMN_RECIPE_ID + " INTEGER NOT NULL, "
                + CharDatabaseContract.IngredientEntry.COLUMN_INGREDIENT + " TEXT NOT NULL, "
                + CharDatabaseContract.IngredientEntry.COLUMN_MEASURE + " TEXT NOT NULL, "
                + CharDatabaseContract.IngredientEntry.COLUMN_QUANTITY + " REAL NOT NULL, "
                + " FOREIGN KEY ("
                + CharDatabaseContract.IngredientEntry.COLUMN_RECIPE_ID
                + ") REFERENCES "
                + CharDatabaseContract.RecipeEntry.TABLE_NAME + " ("
                + CharDatabaseContract.RecipeEntry.COLUMN_ID + "));";

        final String SQL_CREATE_STEP_TABLE = "CREATE TABLE " + CharDatabaseContract.StepEntry.TABLE_NAME
                + " ("
                + CharDatabaseContract.StepEntry.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + CharDatabaseContract.StepEntry.COLUMN_RECIPE_ID + " INTEGER NOT NULL, "
                + CharDatabaseContract.StepEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + CharDatabaseContract.StepEntry.COLUMN_SHORT_DESCRIPTION + " TEXT NOT NULL, "
                + CharDatabaseContract.StepEntry.COLUMN_STEP_NUMBER + " INTEGER NOT NULL, "
                + CharDatabaseContract.StepEntry.COLUMN_THUMBNAIL_URL + " TEXT, "
                + CharDatabaseContract.StepEntry.COLUMN_VIDEO_URL + " TEXT, "
                + " FOREIGN KEY ("
                + CharDatabaseContract.StepEntry.COLUMN_RECIPE_ID
                + ") REFERENCES "
                + CharDatabaseContract.RecipeEntry.TABLE_NAME + " ("
                + CharDatabaseContract.RecipeEntry.COLUMN_ID + "));";

        sqLiteDatabase.execSQL(SQL_CREATE_RECIPE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_INGREDIENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_STEP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + CharDatabaseContract.RecipeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + CharDatabaseContract.IngredientEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + CharDatabaseContract.StepEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
