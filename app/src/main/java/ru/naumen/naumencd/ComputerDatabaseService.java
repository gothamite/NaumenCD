package ru.naumen.naumencd;


import java.util.List;

import ru.naumen.naumencd.app.ComputerDatabaseApi;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.models.Item;
import rx.Observable;

public class ComputerDatabaseService {

    private ComputerDatabaseApi cdApi;

    public ComputerDatabaseService(ComputerDatabaseApi computerDatabaseApi) {
        this.cdApi = computerDatabaseApi;
    }

    public Observable<Computers> getComputers(int page) {
        return cdApi.getComputers(page);
    }

    public Observable<Item> getComputer(int id){
        return cdApi.getComputer(id);
    }

    public  Observable<List<Item>> getComputersSimilar(int id){
        return cdApi.getComputersSimilar(id);
    }
}