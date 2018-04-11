package ru.naumen.naumencd.di.home;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.naumen.naumencd.app.ListApi;
import ru.naumen.naumencd.repositories.ListRepository;
import ru.naumen.naumencd.repositories.ListRepositoryImpl;
import ru.naumen.naumencd.room.AppDatabase;
import ru.naumen.naumencd.utils.Timer;


@Module
public class ListDataSourceModule {
    @Provides
    @ListScope
    public ListRepository provideListRepository(ListApi listApi, AppDatabase appDatabase, Timer hashMap) {
        return new ListRepositoryImpl(listApi, appDatabase, hashMap);
    }

    @Provides
    @ListScope
    public ListApi provideApi(Retrofit retrofit) {
        return retrofit.create(ListApi.class);
    }

}
