package ru.naumen.naumencd.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.naumen.naumencd.di.activity.ActivityComponent;
import ru.naumen.naumencd.di.activity.ActivityModule;
import ru.naumen.naumencd.di.module.ContextModule;
import ru.naumen.naumencd.di.module.RetrofitModule;

@Singleton
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface AppComponent {

    ActivityComponent addActivityComponent(ActivityModule activityModule);
}