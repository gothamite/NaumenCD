package ru.naumen.naumencd.ui.activities.home;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.naumen.naumencd.di.Injector;
import ru.naumen.naumencd.models.dbdto.PageEntity;
import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;
import ru.naumen.naumencd.page.ListPage;
import ru.naumen.naumencd.repositories.CardRepository;
import ru.naumen.naumencd.repositories.ListRepository;
import ru.naumen.naumencd.ui.di.component.AppComponentMock;
import ru.naumen.naumencd.util.ItemEntityFactory;
import ru.naumen.naumencd.util.PageItemEntityFactory;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class ListTest {
    private int pageNumber;
    private PageEntity pageEntity;
    private ItemEntity itemEntity;
    private List<SimilarItemEntity> list;
    private ListPage listPage;

    @Inject
    ListRepository listRepositoryMock;

    @Inject
    CardRepository cardRepositoryMock;

    @Before
    public void setUp() {
        Injector injector = Injector.getInjector();
        AppComponentMock appComponentMock = (AppComponentMock) injector.getAppComponent();
        MockitoAnnotations.initMocks(this);
        appComponentMock.inject(this);
        listPage = new ListPage();
        list = ItemEntityFactory.createList(5);
        pageNumber = 0;
        pageEntity = new PageEntity();
        pageEntity.setItems(PageItemEntityFactory.createList(10));
        itemEntity = ItemEntityFactory.createItemEntity();
        pageEntity.setTotal(580);
        pageEntity.setPage(pageNumber);
        initRepositories();
    }

    @Test
    public void onNextClick() throws Exception {
        listPage.launchActivity();
        int page = pageNumber + 1;

        listPage.checkNumberOfPage(page);
        pageEntity.setPage(pageNumber + 1);

        listPage.showNextPage()
                .checkNumberOfPage(page + 1);
    }

    @Test
    public void onPreviousClick() throws Exception {
        pageEntity.setPage(pageNumber + 1);
        listPage.launchActivity();
        int page = pageNumber + 2;

        listPage.checkNumberOfPage(page);
        pageEntity.setPage(pageNumber);

        listPage.showPreviousPage()
                .checkNumberOfPage(page - 1);
    }

    @Test
    public void onItemClick() throws Exception {
        listPage.launchActivity()
                .clickOnItemWithPosition(0)
                .checkBackStackEntryCount(1);
    }

    @Test
    public void compareItem() throws Exception {
        String computerName = pageEntity.getItems().get(0).getName();
        String companyName = pageEntity.getItems().get(0).getCompanyName();
        listPage.launchActivity()
                .checkItemWithText(computerName)
                .checkItemWithText(companyName);
    }

    private void initRepositories() {
        when(listRepositoryMock.getComputers(anyInt())).thenReturn(Observable.just(pageEntity));
        when(cardRepositoryMock.getComputer(anyInt())).thenReturn(Observable.just(itemEntity));
        when(cardRepositoryMock.getComputersSimilar(anyInt())).thenReturn(Observable.just(list));
    }
}