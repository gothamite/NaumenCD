package ru.naumen.naumencd.app;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.models.SimilarItem;

public interface CardApi {

    @GET("rest/computers/{id}")
    Observable<Item> getComputer(@Path("id") int id);

    @GET("rest/computers/{id}/similar")
    Observable<List<SimilarItem>> getComputersSimilar(@Path("id") int id);
}
