package ru.naumen.naumencd.page;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;

import ru.naumen.naumencd.R;
import ru.naumen.naumencd.ui.activities.home.HomeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

public class ListPage {
    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class, false, false);

    public ListPage launchActivity() {
        activityTestRule.launchActivity(new Intent());
        return this;
    }

    public ListPage checkNumberOfPage(int page) {
        onView(withId(R.id.pages)).check(matches(withText(String.valueOf(page) + " of 58")));
        return this;
    }

    public ListPage showNextPage() {
        onView(withId(R.id.next)).check(matches(isDisplayed())).perform(click());
        return this;
    }

    public ListPage showPreviousPage() {
        onView(withId(R.id.previous)).check(matches(isDisplayed())).perform(click());
        return this;
    }

    public ListPage clickOnItemWithPosition(int position) {
        onView(withId(R.id.computers_list)).perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
        return this;
    }

    public ListPage checkBackStackEntryCount(int count) {
        assertEquals(count, activityTestRule.getActivity().getFragmentManager().getBackStackEntryCount());
        return this;
    }

    public ListPage checkItemWithText(String field) {
        onView(withId(R.id.computers_list)).check(matches(hasDescendant(withText(field))));
        return this;
    }
}
