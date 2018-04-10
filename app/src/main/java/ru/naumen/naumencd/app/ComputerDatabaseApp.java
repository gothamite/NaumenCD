package ru.naumen.naumencd.app;

import android.app.Application;

import ru.naumen.naumencd.di.DaggerAppComponent;
import ru.naumen.naumencd.di.Injector;
import ru.naumen.naumencd.di.module.ContextModule;
import timber.log.Timber;

public class ComputerDatabaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        initializeInjector();
    }

    protected void initializeInjector() {
        Injector injector = new Injector();
        injector.setAppComponent(DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build());
        Injector.setInjector(injector);
    }
}