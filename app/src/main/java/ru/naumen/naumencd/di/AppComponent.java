package ru.naumen.naumencd.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.naumen.naumencd.di.activity.ActivityComponent;
import ru.naumen.naumencd.di.activity.ActivityModule;
import ru.naumen.naumencd.di.module.ContextModule;
import ru.naumen.naumencd.di.module.SchedulerProviderModule;
import ru.naumen.naumencd.di.module.TimerModule;
import ru.naumen.naumencd.di.module.RetrofitModule;
import ru.naumen.naumencd.di.module.RoomModule;

@Singleton
@Component(modules = {ContextModule.class, RetrofitModule.class, RoomModule.class, TimerModule.class, SchedulerProviderModule.class})
public interface AppComponent {

    ActivityComponent addActivityComponent(ActivityModule activityModule);
}