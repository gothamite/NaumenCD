package ru.naumen.naumencd.ui.activities.home;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.di.Injector;
import ru.naumen.naumencd.models.dbdto.PageEntity;
import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;
import ru.naumen.naumencd.repositories.CardRepository;
import ru.naumen.naumencd.repositories.ListRepository;
import ru.naumen.naumencd.ui.di.component.AppComponentMock;
import ru.naumen.naumencd.util.ItemEntityFactory;
import ru.naumen.naumencd.util.PageItemEntityFactory;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static ru.naumen.naumencd.util.ClickableSpan.clickClickableSpan;

@RunWith(AndroidJUnit4.class)
public class CardTest {
    private ActionBar actionBar;
    private ItemEntity itemEntity;
    private PageEntity pageEntity;
    private List<SimilarItemEntity> list;

    @Inject
    CardRepository cardRepositoryMock;

    @Inject
    ListRepository listRepositoryMock;

    @Rule
    public ActivityTestRule<HomeActivity> activityActivityTestRule = new ActivityTestRule<>(HomeActivity.class, false, false);

    @Before
    public void setUp() {
        Injector injector = Injector.getInjector();
        AppComponentMock appComponentMock = (AppComponentMock) injector.getAppComponent();
        MockitoAnnotations.initMocks(this);
        appComponentMock.inject(this);

        list = ItemEntityFactory.createList(5);
        itemEntity = ItemEntityFactory.createItemEntity();
        int pageNumber = 0;
        pageEntity = new PageEntity();
        pageEntity.setItems(PageItemEntityFactory.createList(10));
        pageEntity.setTotal(580);
        pageEntity.setPage(pageNumber);

    }

    @Test
    public void onItemClick() {
        launchActivity();

        onView(withId(R.id.computers_similar))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        assertEquals(String.valueOf(actionBar.getTitle()), itemEntity.getName());
    }

    @Test
    public void onBackPressed() {
        launchActivity();
        pressBack();

        assertEquals(0, activityActivityTestRule.getActivity().getFragmentManager().getBackStackEntryCount());
    }

    @Test
    public void compareFields() {
        launchActivity();
        onView(withId(R.id.company)).check(matches(isDisplayed())).check(matches(withText(itemEntity.getCompany().getName())));
        onView(withId(R.id.introduced)).check(matches(isDisplayed())).check(matches(withText("16 Jan 1986")));
        onView(withId(R.id.discontinued)).check(matches(withText("16 Jan 1986")));
        assertEquals(String.valueOf(actionBar.getTitle()), itemEntity.getName());
        onView(withId(R.id.image_comp)).check(matches(not(isDisplayed())));
    }

    @Test
    public void checkMore() {
        launchActivity();
        onView(withId(R.id.description)).perform(clickClickableSpan("... More"));
        onView(withId(R.id.description)).check(matches(withText(itemEntity.getDescription() + " ")));
    }

    private void launchActivity() {
        when(listRepositoryMock.getComputers(anyInt())).thenReturn(Observable.just(pageEntity));
        when(cardRepositoryMock.getComputer(anyInt())).thenReturn(Observable.just(itemEntity));
        when(cardRepositoryMock.getComputersSimilar(anyInt())).thenReturn(Observable.just(list));
        activityActivityTestRule.launchActivity(new Intent());
        onView(withId(R.id.computers_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        actionBar = activityActivityTestRule.getActivity().getSupportActionBar();
    }
}