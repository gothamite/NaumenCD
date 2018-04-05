package ru.naumen.naumencd.presentation.presenters.card;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import ru.naumen.naumencd.TestSchedulerProvider;
import ru.naumen.naumencd.models.dbdto.CompanyDbDto;
import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.presentation.views.card.CardView;
import ru.naumen.naumencd.repositories.CardRepository;
import ru.naumen.naumencd.utils.VariableGenerator;

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
    private CardRepository cardRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider();
        variableGenerator = new VariableGenerator();
        cardPresenter = new CardPresenter(cardViewMock, cardRepositoryMock, testSchedulerProvider);
    }

    @Test
    public void loadComputer_andUpdateView() throws Exception {

        // prepare

        int id = 1;
        String date = "1986-01-15T19:00:00Z";
        String parseDate = "16 янв 1986";
        String expectedName = variableGenerator.generateString();
        String expectedCompanyName = variableGenerator.generateString();
        String expectedDescription = variableGenerator.generateString();
        String expectedImageUrl = variableGenerator.generateString();
        Integer integerExpected = variableGenerator.generateInteger();

        when(itemEntityMock.getName()).thenReturn(expectedName);
        when(itemEntityMock.getCompany()).thenReturn(companyDbDtoMock);
        when(companyDbDtoMock.getName()).thenReturn(expectedCompanyName);
        when(itemEntityMock.getDescription()).thenReturn(expectedDescription);
        when(itemEntityMock.getDiscounted()).thenReturn(date);
        when(itemEntityMock.getImageUrl()).thenReturn(expectedImageUrl);
        when(itemEntityMock.getIntroduced()).thenReturn(date);
        when(itemEntityMock.getId()).thenReturn(integerExpected);

        when(cardRepositoryMock.getComputer(id)).thenReturn(Observable.just(itemEntityMock));

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
    public void loadSimilarComputers() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

}