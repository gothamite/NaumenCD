package ru.naumen.naumencd.page;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.ActionBar;

import org.junit.Rule;

import ru.naumen.naumencd.R;
import ru.naumen.naumencd.ui.activities.home.HomeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static ru.naumen.naumencd.util.ClickableSpan.clickClickableSpan;

public class CardPage {
    private ActionBar actionBar;

    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class, false, false);

    public CardPage launchActivity() {
        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.computers_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        actionBar = activityTestRule.getActivity().getSupportActionBar();
        return this;
    }

    public CardPage clickOnItemWithPosition(int position) {
        onView(withId(R.id.computers_similar)).perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
        return this;
    }

    public CardPage checkActionBarTitle(String name) {
        assertEquals(String.valueOf(actionBar.getTitle()), name);
        return this;
    }

    public CardPage checkBackStackEntryCount(int count) {
        assertEquals(count, activityTestRule.getActivity().getFragmentManager().getBackStackEntryCount());
        return this;
    }

    public CardPage checkItemField(int viewId, String name) {
        onView(withId(viewId)).check(matches(isDisplayed())).check(matches(withText(name)));
        return this;
    }

    public CardPage checkThatImageIsNotDisplayed() {
        onView(withId(R.id.image_comp)).check(matches(not(isDisplayed())));
        return this;
    }

    public CardPage clickOnClickableSpanWithText(String text) {
        onView(withId(R.id.description)).perform(clickClickableSpan(text));
        return this;
    }
}
