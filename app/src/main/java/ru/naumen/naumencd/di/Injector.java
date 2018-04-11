package ru.naumen.naumencd.di;

import ru.naumen.naumencd.di.activity.ActivityComponent;
import ru.naumen.naumencd.di.activity.ActivityModule;

public class Injector {

    private static Injector injector;
    private AppComponent appComponent;

    public static Injector getInjector() {
        return injector;
    }

    public static void setInjector(Injector injector) {
        Injector.injector = injector;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    public ActivityComponent addActivityComponent(ActivityModule activityModule) {
        return appComponent.addActivityComponent(activityModule);
    }
}
