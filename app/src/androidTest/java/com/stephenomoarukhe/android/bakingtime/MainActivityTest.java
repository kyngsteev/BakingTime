package com.stephenomoarukhe.android.bakingtime;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.stephenomoarukhe.android.bakingtime.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Omoarukhe on 06/23/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    public static final String RECIPE_TEST_TEXT = "Nutella Pie";

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest(){
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.recipe_list), withParent(allOf(withId(R.id.bakersFrame), withParent(withId(android.R.id.content)))),
                isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }
}
