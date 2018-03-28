package ru.naumen.naumencd.repositories;


import ru.naumen.naumencd.app.HomeApi;
import ru.naumen.naumencd.models.Computers;
import rx.Observable;

public class HomeRepository {

    private HomeApi homeApi;

    public HomeRepository(HomeApi homeApi) {
        this.homeApi = homeApi;
    }

    public Observable<Computers> getComputers(int page) {
        return homeApi.getComputers(page);
    }

}
