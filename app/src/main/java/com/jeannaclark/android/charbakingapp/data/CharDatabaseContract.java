package com.jeannaclark.android.charbakingapp.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by birdy on 7/1/17.
 */

public class CharDatabaseContract {

    public static final String CONTENT_AUTHORITY = "com.jeannaclark.android.charbakingapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RECIPE = "recipes";
    public static final String PATH_INGREDIENT = "ingredients";
    public static final String PATH_STEP = "steps";

    public static final class RecipeEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPE).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_RECIPE;
        //TODO: insert content_item?

        public static final String TABLE_NAME = PATH_RECIPE;

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_RECIPE_NAME = "recipe_name";
        public static final String COLUMN_IMAGE_URL = "image_url";
        public static final String COLUMN_SERVINGS = "servings";

        public static Uri buildRecipeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getRecipeIdFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    public static final class IngredientEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_INGREDIENT).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY +
                "/" + PATH_INGREDIENT;

        //TODO: insert content_item?

        public static final String TABLE_NAME = PATH_INGREDIENT;

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_INGREDIENT = "ingredient";
        public static final String COLUMN_MEASURE = "measure";
        public static final String COLUMN_QUANTITY = "quantity";

        public static Uri buildIngredientsUri(long id) {
            return CONTENT_URI;
        }
    }

    public static final class StepEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_STEP).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY +
                "/" + PATH_STEP;

        //TODO: insert content_item?

        public static final String TABLE_NAME = PATH_STEP;

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_SHORT_DESCRIPTION = "short_description";
        public static final String COLUMN_THUMBNAIL_URL = "thumbnail_url";
        public static final String COLUMN_VIDEO_URL = "video_url";
        public static final String COLUMN_STEP_NUMBER = "step_number";

        public static Uri buildStepsUri(long id) {
            return CONTENT_URI;
        }
    }
}
