package ru.naumen.naumencd.app;

import android.app.Application;

import ru.naumen.naumencd.di.AppComponent;
import ru.naumen.naumencd.di.DaggerAppComponent;
import ru.naumen.naumencd.di.module.ContextModule;
import ru.naumen.naumencd.di.module.NetworkModule;
import ru.naumen.naumencd.di.module.RetrofitModule;
import timber.log.Timber;

public class ComputerDatabaseApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .networkModule(new NetworkModule())
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}