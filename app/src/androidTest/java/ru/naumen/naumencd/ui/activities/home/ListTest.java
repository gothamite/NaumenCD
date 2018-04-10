package ru.naumen.naumencd.ui.activities.home;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class ListTest {
    private int pageNumber;
    private PageEntity pageEntity;
    private ItemEntity itemEntity;
    private List<SimilarItemEntity> list;

    @Inject
    ListRepository listRepositoryMock;

    @Inject
    CardRepository cardRepositoryMock;

    @Rule
    public ActivityTestRule<HomeActivity> activityActivityTestRule = new ActivityTestRule<>(HomeActivity.class, false, false);

    @Before
    public void setUp() {
        Injector injector = Injector.getInjector();
        AppComponentMock appComponentMock = (AppComponentMock) injector.getAppComponent();
        MockitoAnnotations.initMocks(this);
        appComponentMock.inject(this);

        list = ItemEntityFactory.createList(5);
        pageNumber = 0;
        pageEntity = new PageEntity();
        pageEntity.setItems(PageItemEntityFactory.createList(10));
        itemEntity = ItemEntityFactory.createItemEntity();
        pageEntity.setTotal(580);
        pageEntity.setPage(pageNumber);
    }

    @Test
    public void onNextClick() throws Exception {
        launchActivity();

        int page = pageNumber + 1;
        onView(withId(R.id.pages)).check(matches(withText(String.valueOf(page) + " of 58")));
        pageEntity.setPage(pageNumber + 1);
        onView(withId(R.id.next))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.pages)).check(matches(withText(String.valueOf(page + 1) + " of 58")));
    }

    @Test
    public void onPreviousClick() throws Exception {
        pageEntity.setPage(pageNumber + 1);
        launchActivity();

        int page = pageNumber + 2;
        onView(withId(R.id.pages)).check(matches(withText(String.valueOf(page) + " of 58")));
        pageEntity.setPage(pageNumber);
        onView(withId(R.id.previous))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.pages)).check(matches(withText(String.valueOf(page - 1) + " of 58")));
    }

    @Test
    public void onItemClick() throws Exception {
        launchActivity();

        onView(withId(R.id.computers_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        assertEquals(1, activityActivityTestRule.getActivity().getFragmentManager().getBackStackEntryCount());
    }

    @Test
    public void compareItem() throws Exception {
        launchActivity();
        String computerName = pageEntity.getItems().get(0).getName();
        String companyName = pageEntity.getItems().get(0).getCompanyName();

        onView(withId(R.id.computers_list)).check(matches(hasDescendant(withText(computerName))));
        onView(withId(R.id.computers_list)).check(matches(hasDescendant(withText(companyName))));
    }

    private void launchActivity() {
        when(listRepositoryMock.getComputers(anyInt())).thenReturn(Observable.just(pageEntity));
        when(cardRepositoryMock.getComputer(anyInt())).thenReturn(Observable.just(itemEntity));
        when(cardRepositoryMock.getComputersSimilar(anyInt())).thenReturn(Observable.just(list));
        activityActivityTestRule.launchActivity(new Intent());
    }
}