package ru.naumen.naumencd.di.card;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.naumen.naumencd.app.CardApi;
import ru.naumen.naumencd.presentation.presenters.card.CardPresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;
import ru.naumen.naumencd.repositories.CardRepository;
import ru.naumen.naumencd.room.AppDatabase;
import ru.naumen.naumencd.ui.adapters.card.ComputersSimilarAdapter;
import ru.naumen.naumencd.utils.SchedulerProvider;
import ru.naumen.naumencd.utils.Timer;
import ru.naumen.naumencd.utils.Navigator;

@Module
public class CardModule {
    private final CardView cardView;

    public CardModule(CardView cardView) {
        this.cardView = cardView;
    }

    @Provides
    @CardScope
    CardPresenter provideCardPresenter(CardRepository cardRepository, SchedulerProvider schedulerProvider) {
        return new CardPresenter(cardView, cardRepository, schedulerProvider);
    }

    @Provides
    @CardScope
    ComputersSimilarAdapter provideSimilarAdapter(Navigator navigator) {
        return new ComputersSimilarAdapter(navigator);
    }

    @Provides
    @CardScope
    public CardRepository provideCardRepository(CardApi cardApi, AppDatabase appDatabase, Timer hashMap, SchedulerProvider schedulerProvider) {
        return new CardRepository(cardApi, appDatabase, hashMap, schedulerProvider);
    }

    @Provides
    @CardScope
    public CardApi provideApi(Retrofit retrofit) {
        return retrofit.create(CardApi.class);
    }
}
