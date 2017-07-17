package com.jeannaclark.android.charbakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jeannaclark.android.charbakingapp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by birdy on 7/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class FavoriteToggleActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityFragmentActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickFavoriteToggleButton_ChangesDrawableIconColorAndUpdatesFirebase() {

        //TODO: update to be onData since this uses an adapter
        onView(withId(R.id.recipe_card_favorite_button)).check(matches(not(isEnabled())));
        onView(withId(R.id.recipe_card_favorite_button)).perform(click());
        onView(withId(R.id.recipe_card_favorite_button)).check(matches(isEnabled()));
    }
}
