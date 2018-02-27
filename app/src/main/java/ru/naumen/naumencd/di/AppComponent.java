package ru.naumen.naumencd.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.naumen.naumencd.di.module.ContextModule;
import ru.naumen.naumencd.di.module.NetworkModule;
import ru.naumen.naumencd.di.module.RetrofitModule;
import ru.naumen.naumencd.presentation.presenters.home.HomePresenter;

@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, RetrofitModule.class})
public interface AppComponent {
    Context getContext();

    void inject(HomePresenter homePresenter);
}