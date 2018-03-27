package ru.naumen.naumencd.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.presentation.presenters.card.CardPresenter;
import ru.naumen.naumencd.presentation.presenters.home.HomePresenter;
import ru.naumen.naumencd.utils.SharedPrefs;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    HomePresenter provideHomePresenter(ComputerDatabaseService cdService, SharedPrefs sharedPrefsPage) {
        return new HomePresenter(cdService, sharedPrefsPage);
    }

    @Provides
    @Singleton
    CardPresenter provideCardPresenter(ComputerDatabaseService cdService) {
        return new CardPresenter(cdService);
    }
}
