package ru.naumen.naumencd.repositories;


import io.reactivex.Observable;
import ru.naumen.naumencd.app.ListApi;
import ru.naumen.naumencd.models.dto.Computers;


public class ListRepository {

    private ListApi listApi;

    public ListRepository(ListApi listApi) {
        this.listApi = listApi;
    }

    public Observable<Computers> getComputers(int page) {
        return listApi.getComputers(page);
    }

}
