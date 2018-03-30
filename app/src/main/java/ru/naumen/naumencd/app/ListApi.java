package ru.naumen.naumencd.app;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.naumen.naumencd.models.Computers;

public interface ListApi {

    @GET("rest/computers")
    Observable<Computers> getComputers(@Query("p") int page);
}
