package ru.naumen.naumencd.app;



import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.models.Item;
import rx.Observable;

public interface ComputerDatabaseApi {

    int PAGE_SIZE = 5;

    @GET("rest/computers")
    Observable<Computers> getComputers(@Query("p") int page);

    @GET("rest/computers/{id}")
    Observable<Item> getComputer(@Path("id") int id);

    @GET("rest/computers/{id}/similar")
    Observable<List<Item>> getComputersSimilar(@Path("id") int id);
}
