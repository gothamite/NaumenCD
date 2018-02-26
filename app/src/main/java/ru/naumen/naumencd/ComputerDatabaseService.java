package ru.naumen.naumencd;


import ru.naumen.naumencd.app.ComputerDatabaseApi;
import ru.naumen.naumencd.models.Computers;
import rx.Observable;

public class ComputerDatabaseService {
    private ComputerDatabaseApi CdApi;

    public ComputerDatabaseService(ComputerDatabaseApi computerDatabaseApi) {
        this.CdApi = computerDatabaseApi;
    }

    public Observable<Computers> getComputers(int page) {
        return CdApi.getComputers(page);
    }
}