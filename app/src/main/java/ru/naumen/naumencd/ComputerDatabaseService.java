package ru.naumen.naumencd;


import java.util.List;

import retrofit2.http.Path;
import ru.naumen.naumencd.app.ComputerDatabaseApi;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.models.Item;
import rx.Observable;

public class ComputerDatabaseService {
    private ComputerDatabaseApi CdApi;

    public ComputerDatabaseService(ComputerDatabaseApi computerDatabaseApi) {
        this.CdApi = computerDatabaseApi;
    }

    public Observable<Computers> getComputers(int page) {
        return CdApi.getComputers(page);
    }

    public Observable<Item> getComputer(int id){
        return CdApi.getComputer(id);
    }

    public  Observable<List<Item>> getComputersSimilar(int id){
        return CdApi.getComputersSimilar(id);
    }
}