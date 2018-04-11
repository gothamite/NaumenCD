package ru.naumen.naumencd.ui.activities.home;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
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
import ru.naumen.naumencd.page.CardPage;
import ru.naumen.naumencd.repositories.CardRepository;
import ru.naumen.naumencd.repositories.ListRepository;
import ru.naumen.naumencd.ui.di.component.AppComponentMock;
import ru.naumen.naumencd.util.ItemEntityFactory;
import ru.naumen.naumencd.util.PageItemEntityFactory;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static ru.naumen.naumencd.util.ClickableSpan.clickClickableSpan;

@RunWith(AndroidJUnit4.class)
public class CardTest {
    private ItemEntity itemEntity;
    private PageEntity pageEntity;
    private List<SimilarItemEntity> list;
    private CardPage cardPage;

    @Inject
    CardRepository cardRepositoryMock;

    @Inject
    ListRepository listRepositoryMock;

    @Before
    public void setUp() {
        Injector injector = Injector.getInjector();
        AppComponentMock appComponentMock = (AppComponentMock) injector.getAppComponent();
        MockitoAnnotations.initMocks(this);
        appComponentMock.inject(this);
        cardPage = new CardPage();
        list = ItemEntityFactory.createList(5);
        itemEntity = ItemEntityFactory.createItemEntity();
        int pageNumber = 0;
        pageEntity = new PageEntity();
        pageEntity.setItems(PageItemEntityFactory.createList(10));
        pageEntity.setTotal(580);
        pageEntity.setPage(pageNumber);
        initRepositories();
    }

    @Test
    public void onItemClick() {
        cardPage.launchActivity()
                .clickOnItemWithPosition(0)
                .checkActionBarTitle(itemEntity.getName());
    }

    @Test
    public void onBackPressed() {
        cardPage.launchActivity();
        pressBack();
        cardPage.checkBackStackEntryCount(0);
    }

    @Test
    public void compareFields() {
        cardPage.launchActivity()
                .checkItemField(R.id.company, itemEntity.getCompany().getName())
                .checkItemField(R.id.introduced, "16 Jan 1986")
                .checkItemField(R.id.discontinued, "16 Jan 1986")
                .checkActionBarTitle(itemEntity.getName())
                .checkThatImageIsNotDisplayed();
    }

    @Test
    public void checkMore() {
        cardPage.launchActivity()
                .clickOnClickableSpanWithText("... More")
                .checkItemField(R.id.description, itemEntity.getDescription() + " ");
    }

    private void initRepositories() {
        when(listRepositoryMock.getComputers(anyInt())).thenReturn(Observable.just(pageEntity));
        when(cardRepositoryMock.getComputer(anyInt())).thenReturn(Observable.just(itemEntity));
        when(cardRepositoryMock.getComputersSimilar(anyInt())).thenReturn(Observable.just(list));
    }
}