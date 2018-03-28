package ru.naumen.naumencd.app;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.naumen.naumencd.models.Computers;
import rx.Observable;

public interface HomeApi {

    @GET("rest/computers")
    Observable<Computers> getComputers(@Query("p") int page);
}
