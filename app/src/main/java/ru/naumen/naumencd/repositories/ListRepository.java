package ru.naumen.naumencd.repositories;


import io.reactivex.Observable;
import ru.naumen.naumencd.app.ListApi;
import ru.naumen.naumencd.models.dto.Computers;
import ru.naumen.naumencd.room.AppDatabase;


public class ListRepository {

    private AppDatabase appDatabase;

    private ListApi listApi;

    public ListRepository(ListApi listApi, AppDatabase appDatabase) {
        this.listApi = listApi;
        this.appDatabase = appDatabase;
    }

    public Observable<Computers> getComputers(int page) {
        return listApi.getComputers(page);
    }

}
