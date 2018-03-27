package ru.naumen.naumencd.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.di.HomeScope;
import ru.naumen.naumencd.presentation.presenters.home.HomePresenter;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import ru.naumen.naumencd.utils.SharedPrefs;

@Module
public class HomeModule {
    private final HomeView homeView;
    public HomeModule(HomeView homeView){
        this.homeView = homeView;
    }

    @Provides
    @HomeScope
    public SharedPrefs provideSharedPrefers(Context context){
        return new SharedPrefs(context);
    }

    @Provides
    @HomeScope
    HomePresenter provideHomePresenter(ComputerDatabaseService cdService, SharedPrefs sharedPrefsPage) {
        return new HomePresenter(homeView, cdService, sharedPrefsPage);
    }
}
