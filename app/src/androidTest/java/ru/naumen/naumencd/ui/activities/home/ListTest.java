package ru.naumen.naumencd.ui.activities.home;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ru.naumen.naumencd.R;
import ru.naumen.naumencd.utils.SharedPrefs;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ListTest {
    private SharedPrefs sharedPrefs;
    private int pageNumber;

    @Rule
    public ActivityTestRule<HomeActivity> activityActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() {
        sharedPrefs = new SharedPrefs(activityActivityTestRule.getActivity());
        pageNumber = sharedPrefs.getComputers();
    }

    @Test
    public void onNextClick() throws Exception {
        int page = pageNumber + 1;
        if (page != 58) {
            onView(withId(R.id.pages)).check(matches(withText(String.valueOf(page) + " of 58")));
            onView(withId(R.id.next))
                    .check(matches(isDisplayed()))
                    .perform(click());
        }
    }

    @Test
    public void onPreviousClick() throws Exception {
        int page = pageNumber + 1;
        if (page != 1) {
            onView(withId(R.id.pages)).check(matches(withText(String.valueOf(page) + " of 58")));
            onView(withId(R.id.previous))
                    .check(matches(isDisplayed()))
                    .perform(click());
            onView(withId(R.id.pages)).check(matches(withText(String.valueOf(page - 1) + " of 58")));
        }
    }

    @Test
    public void onItemClick() throws Exception {
        onView(withId(R.id.computers_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void compareItem() throws Exception {
        String computerName = "Computer name";

        onView(withId(R.id.computers_list)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.computers_list)).check(matches(hasDescendant(withText(computerName))));
    }
}