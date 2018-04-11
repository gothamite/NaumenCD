package ru.naumen.naumencd.presentation.presenters.card;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.naumen.naumencd.TestSchedulerProvider;
import ru.naumen.naumencd.VariableGenerator;
import ru.naumen.naumencd.models.dbdto.CompanyDbDto;
import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;
import ru.naumen.naumencd.presentation.views.card.CardView;
import ru.naumen.naumencd.repositories.CardRepositoryImpl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CardPresenterTest {
    private CardPresenter cardPresenter;
    private VariableGenerator variableGenerator;

    @Mock
    private ItemEntity itemEntityMock;
    @Mock
    private CompanyDbDto companyDbDtoMock;
    @Mock
    private CardView cardViewMock;
    @Mock
    private CardRepositoryImpl cardRepositoryImplMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider();
        variableGenerator = new VariableGenerator();
        cardPresenter = new CardPresenter(cardViewMock, cardRepositoryImplMock, testSchedulerProvider);
    }

    @Test
    public void loadComputer_andUpdateView() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        String date = "1986-01-15T19:00:00Z";
        String parseDate = "16 янв 1986";
        String expectedName = variableGenerator.generateString();
        String expectedCompanyName = variableGenerator.generateString();
        String expectedDescription = variableGenerator.generateString();
        String expectedImageUrl = variableGenerator.generateString();

        when(itemEntityMock.getName()).thenReturn(expectedName);
        when(itemEntityMock.getCompany()).thenReturn(companyDbDtoMock);
        when(companyDbDtoMock.getName()).thenReturn(expectedCompanyName);
        when(itemEntityMock.getDescription()).thenReturn(expectedDescription);
        when(itemEntityMock.getDiscounted()).thenReturn(date);
        when(itemEntityMock.getImageUrl()).thenReturn(expectedImageUrl);
        when(itemEntityMock.getIntroduced()).thenReturn(date);
        when(cardRepositoryImplMock.getComputer(id)).thenReturn(Observable.just(itemEntityMock));

        // do actions
        cardPresenter.loadComputer(id);

        // assert
        verify(cardViewMock).setActionBar(eq(expectedName));
        verify(cardViewMock).setCompany(eq(expectedCompanyName));
        verify(cardViewMock).setDescription(eq(expectedDescription));
        verify(cardViewMock).setDiscounted(eq(parseDate));
        verify(cardViewMock).setImage(eq(expectedImageUrl));
        verify(cardViewMock).setIntroduced(eq(parseDate));
    }

    @Test
    public void loadComputer_failed() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        String expectedError = variableGenerator.generateString();
        when(cardRepositoryImplMock.getComputer(id)).thenReturn(Observable.error(new NullPointerException(expectedError)));

        // do
        cardPresenter.loadComputer(id);

        // assert
        verify(cardViewMock).showSnackbar(eq(expectedError));
    }

    @Test
    public void loadSimilarComputers_andUpdateView() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        List<SimilarItemEntity> list = new ArrayList<>();
        when(cardRepositoryImplMock.getComputersSimilar(id)).thenReturn(Observable.just(list));

        //do
        cardPresenter.loadSimilarComputers(id);

        //assert
        verify(cardViewMock).setComputersSimilar(list);
    }

    @Test
    public void loadSimilarComputers_failed() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        String expectedError = variableGenerator.generateString();
        when(cardRepositoryImplMock.getComputersSimilar(id)).thenReturn(Observable.error(new NullPointerException(expectedError)));

        //do
        cardPresenter.loadSimilarComputers(id);

        //assert
        verify(cardViewMock).showSnackbar(eq(expectedError));
    }
}