package ru.naumen.naumencd.repositories;

import java.util.List;

import ru.naumen.naumencd.app.CardApi;
import ru.naumen.naumencd.models.Item;
import rx.Observable;

public class CardRepository {

    private CardApi cardApi;

    public CardRepository(CardApi cardApi) {
        this.cardApi = cardApi;
    }

    public Observable<Item> getComputer(int id) {
        return cardApi.getComputer(id);
    }

    public Observable<List<Item>> getComputersSimilar(int id) {
        return cardApi.getComputersSimilar(id);
    }
}
