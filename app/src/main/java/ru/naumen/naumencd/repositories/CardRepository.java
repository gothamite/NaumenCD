package ru.naumen.naumencd.repositories;

import java.util.List;

import io.reactivex.Observable;
import ru.naumen.naumencd.app.CardApi;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.models.ItemEntity;
import ru.naumen.naumencd.models.SimilarItem;
import ru.naumen.naumencd.models.SimilarItemEntity;
import ru.naumen.naumencd.room.AppDatabase;

public class CardRepository {

    private AppDatabase appDatabase;

    private CardApi cardApi;

    public CardRepository(CardApi cardApi, AppDatabase appDatabase) {
        this.cardApi = cardApi;
        this.appDatabase = appDatabase;
    }

    public Observable<? extends ItemEntity> getComputer(int id) {
        Observable<Item> itemObservable = cardApi.getComputer(id)
                .doOnNext(item -> appDatabase.itemDao().insert(item));

        return Observable.fromCallable(() -> {
            Item comp = appDatabase.itemDao().getId(id);
            if (comp != null) {
                return comp;
            }
            throw new IllegalStateException("Comp not found");
        }).onErrorResumeNext(itemObservable);
    }

    public Observable<List<SimilarItem>> getComputersSimilar(int id) {
        return cardApi.getComputersSimilar(id);
    }
}
