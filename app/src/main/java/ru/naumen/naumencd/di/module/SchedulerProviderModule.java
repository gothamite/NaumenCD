package ru.naumen.naumencd.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.utils.SchedulerProvider;

@Module
public class SchedulerProviderModule {
    @Provides
    @Singleton
    public SchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }
}
