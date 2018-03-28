package ru.naumen.naumencd.di.home;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.naumen.naumencd.app.HomeApi;
import ru.naumen.naumencd.presentation.presenters.home.HomePresenter;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import ru.naumen.naumencd.repositories.HomeRepository;
import ru.naumen.naumencd.ui.adapters.home.ComputersListAdapter;
import ru.naumen.naumencd.utils.Navigator;
import ru.naumen.naumencd.utils.SharedPrefs;

@Module
public class HomeModule {
    private final HomeView homeView;

    public HomeModule(HomeView homeView) {
        this.homeView = homeView;
    }

    @Provides
    @HomeScope
    public SharedPrefs provideSharedPrefers(Context context) {
        return new SharedPrefs(context);
    }

    @Provides
    @HomeScope
    HomePresenter provideHomePresenter(HomeRepository homeRepository, SharedPrefs sharedPrefsPage) {
        return new HomePresenter(homeView, homeRepository, sharedPrefsPage);
    }

    @Provides
    @HomeScope
    ComputersListAdapter provideListAdapter(Navigator navigator) {
        return new ComputersListAdapter(navigator);
    }

    @Provides
    @HomeScope
    public HomeRepository provideHomeRepository(HomeApi homeApi) {
        return new HomeRepository(homeApi);
    }

    @Provides
    @HomeScope
    public HomeApi provideApi(Retrofit retrofit) {
        return retrofit.create(HomeApi.class);
    }
}
