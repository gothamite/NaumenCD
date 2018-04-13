package ru.naumen.naumencd.di.card;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.naumen.naumencd.app.CardApi;
import ru.naumen.naumencd.repositories.CardRepository;
import ru.naumen.naumencd.repositories.CardRepositoryImpl;
import ru.naumen.naumencd.room.AppDatabase;
import ru.naumen.naumencd.utils.SchedulerProvider;
import ru.naumen.naumencd.utils.Timer;

@Module
public class CardDataSourceModule {

    @Provides
    @CardScope
    public CardRepository provideCardRepository(CardApi cardApi, AppDatabase appDatabase, Timer timer, SchedulerProvider schedulerProvider) {
        return new CardRepositoryImpl(cardApi, appDatabase, timer, schedulerProvider);
    }

    @Provides
    @CardScope
    public CardApi provideApi(Retrofit retrofit) {
        return retrofit.create(CardApi.class);
    }
}
