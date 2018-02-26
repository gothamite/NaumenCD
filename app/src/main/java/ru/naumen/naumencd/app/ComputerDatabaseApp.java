package ru.naumen.naumencd.app;

import android.app.Application;

import ru.naumen.naumencd.di.AppComponent;
import ru.naumen.naumencd.di.DaggerAppComponent;
import ru.naumen.naumencd.di.module.ContextModule;

public class ComputerDatabaseApp extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}