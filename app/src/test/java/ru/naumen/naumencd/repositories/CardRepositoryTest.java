package ru.naumen.naumencd.repositories;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import ru.naumen.naumencd.TestSchedulerProvider;
import ru.naumen.naumencd.app.CardApi;
import ru.naumen.naumencd.models.dbdto.ItemDbDto;
import ru.naumen.naumencd.models.dbdto.SimilarItemDbDto;
import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;
import ru.naumen.naumencd.models.dto.Item;
import ru.naumen.naumencd.models.dto.SimilarItem;
import ru.naumen.naumencd.presentation.views.card.CardView;
import ru.naumen.naumencd.room.AppDatabase;
import ru.naumen.naumencd.room.ItemDao;
import ru.naumen.naumencd.room.SimilarItemDao;
import ru.naumen.naumencd.utils.Timer;
import ru.naumen.naumencd.VariableGenerator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CardRepositoryTest {
    private VariableGenerator variableGenerator;
    private CardRepository cardRepository;

    @Mock
    private Timer timerMock;
    @Mock
    private CardApi cardApiMock;
    @Mock
    private AppDatabase appDatabaseMock;
    @Mock
    private Item itemMock;
    @Mock
    private ItemDao itemDaoMock;
    @Mock
    private ItemDbDto itemDbDtoMock;
    @Mock
    private SimilarItemDao similarItemDaoMock;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider();
        variableGenerator = new VariableGenerator();
        cardRepository = new CardRepository(cardApiMock, appDatabaseMock, timerMock, testSchedulerProvider);
    }

    @Test
    public void getComputer_fromDatabase() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        Integer compId = variableGenerator.generateInteger();
        when(cardApiMock.getComputer(id)).thenReturn(Observable.just(itemMock));
        when(appDatabaseMock.itemDao()).thenReturn(itemDaoMock);
        when(itemDaoMock.getId(id)).thenReturn(itemDbDtoMock);
        when(itemDbDtoMock.getId()).thenReturn(compId);
        when(timerMock.isTimeValid(String.valueOf(compId))).thenReturn(true);

        // do
        cardRepository.getComputer(id).subscribe();

        // assert
        verify(appDatabaseMock).itemDao();
        verify(itemDaoMock).getId(eq(id));
        verify(timerMock).isTimeValid(String.valueOf(compId));
    }

    @Test
    public void getComputer_fromDatabase_failed() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        NullPointerException exception = new NullPointerException(variableGenerator.generateString());
        TestObserver<ItemEntity> testObserver = TestObserver.create();
        when(cardApiMock.getComputer(id)).thenReturn(Observable.just(itemMock));
        when(appDatabaseMock.itemDao()).thenReturn(itemDaoMock);
        when(itemDaoMock.getId(id)).thenThrow(exception);

        // do
        cardRepository.getComputer(id).subscribe(testObserver);

        // assert
        testObserver.onError(exception);
    }

    @Test
    public void getComputer_fromApi() throws Exception {
        // prepare
        TestObserver<ItemEntity> testObserver = TestObserver.create();
        Integer id = variableGenerator.generateInteger();
        when(cardApiMock.getComputer(id)).thenReturn(Observable.just(itemMock));
        when(appDatabaseMock.itemDao()).thenReturn(itemDaoMock);
        when(itemDaoMock.getId(id)).thenReturn(null);

        // do
        cardRepository.getComputer(id).subscribe(testObserver);

        // assert
        testObserver.assertSubscribed();
        verify(cardApiMock).getComputer(id);
        verify(itemDaoMock).insert(any());
        verify(timerMock).updateTime(anyString());
        verify(itemDaoMock).getId(id);
    }

    @Test
    public void getComputer_fromApi_failed() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        String expectedError = variableGenerator.generateString();
        NullPointerException exception = new NullPointerException(expectedError);
        TestObserver<ItemEntity> testObserver = TestObserver.create();
        when(cardApiMock.getComputer(id)).thenReturn(Observable.error(exception));

        // do
        cardRepository.getComputer(id).subscribe(testObserver);

        // assert
        testObserver.onError(exception);
    }

    @Test
    public void getComputersSimilar_fromApi() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        List<SimilarItem> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(variableGenerator.generateSimilarItem());
        }
        when(cardApiMock.getComputersSimilar(id)).thenReturn(Observable.just(list));
        when(appDatabaseMock.similarItemDao()).thenReturn(similarItemDaoMock);
        when(similarItemDaoMock.getSimilarListById(id)).thenReturn(Collections.EMPTY_LIST);

        // do
        cardRepository.getComputersSimilar(id).subscribe();

        // assert
        verify(cardApiMock).getComputersSimilar(id);
        verify(similarItemDaoMock, times(list.size())).insert(any());
        verify(similarItemDaoMock).getSimilarListById(id);
    }

    @Test
    public void getComputersSimilar_fromApi_failed() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        NullPointerException exception = new NullPointerException(variableGenerator.generateString());
        TestObserver<List<SimilarItemEntity>> testObserver = TestObserver.create();
        when(cardApiMock.getComputersSimilar(id)).thenReturn(Observable.error(exception));

        // do
        cardRepository.getComputersSimilar(id).subscribe(testObserver);

        // assert
        testObserver.onError(exception);
    }

    @Test
    public void getComputersSimilar_fromDatabase() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        List<SimilarItem> list = new ArrayList<>();
        List<SimilarItemDbDto> listDbDto = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(variableGenerator.generateSimilarItem());
            listDbDto.add(variableGenerator.generateSimilarItemDbDto());
        }
        when(cardApiMock.getComputersSimilar(id)).thenReturn(Observable.just(list));
        when(appDatabaseMock.similarItemDao()).thenReturn(similarItemDaoMock);
        when(similarItemDaoMock.getSimilarListById(id)).thenReturn(listDbDto);
        when(timerMock.isTimeValid(String.valueOf(id))).thenReturn(true);

        // do
        cardRepository.getComputersSimilar(id).subscribe();

        // assert
        verify(timerMock).isTimeValid(String.valueOf(id));
    }

    @Test
    public void getComputersSimilar_fromDatabase_failed() throws Exception {
        // prepare
        Integer id = variableGenerator.generateInteger();
        List<SimilarItem> list = new ArrayList<>();
        NullPointerException exception = new NullPointerException(variableGenerator.generateString());
        TestObserver<List<SimilarItemEntity>> testObserver = TestObserver.create();
        when(cardApiMock.getComputersSimilar(id)).thenReturn(Observable.just(list));
        when(appDatabaseMock.similarItemDao()).thenReturn(similarItemDaoMock);
        when(similarItemDaoMock.getSimilarListById(id)).thenThrow(exception);

        // do
        cardRepository.getComputersSimilar(id).subscribe(testObserver);

        // assert
        testObserver.onError(exception);
    }
}