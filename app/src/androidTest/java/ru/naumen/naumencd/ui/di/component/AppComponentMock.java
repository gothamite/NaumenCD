package ru.naumen.naumencd.ui.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.naumen.naumencd.di.AppComponent;
import ru.naumen.naumencd.di.activity.ActivityModule;
import ru.naumen.naumencd.di.module.ContextModule;
import ru.naumen.naumencd.di.module.RetrofitModule;
import ru.naumen.naumencd.di.module.RoomModule;
import ru.naumen.naumencd.di.module.SchedulerProviderModule;
import ru.naumen.naumencd.di.module.TimerModule;
import ru.naumen.naumencd.ui.activities.home.CardTest;
import ru.naumen.naumencd.ui.activities.home.ListTest;
import ru.naumen.naumencd.ui.di.module.CardDataSourceModuleMock;
import ru.naumen.naumencd.ui.di.module.ListDataSourceModuleMock;

@Singleton
@Component(modules = {ContextModule.class, RetrofitModule.class, RoomModule.class, TimerModule.class,
        SchedulerProviderModule.class, ListDataSourceModuleMock.class, CardDataSourceModuleMock.class})
public interface AppComponentMock extends AppComponent {
    @Override
    ActivityComponentMock addActivityComponent(ActivityModule activityModule);

    void inject(ListTest listTest);

    void inject(CardTest cardTest);
}
