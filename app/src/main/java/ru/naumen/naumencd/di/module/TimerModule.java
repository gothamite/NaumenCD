package ru.naumen.naumencd.di.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.utils.Timer;
import ru.naumen.naumencd.utils.TimerImpl;

@Module
public class TimerModule {

    @Provides
    @Singleton
    public Timer provideTimer() {
        return new TimerImpl();
    }
}
