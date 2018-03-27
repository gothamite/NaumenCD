package ru.naumen.naumencd.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.utils.Navigator;


@Module
public class NavigatorModule {
    @Singleton
    @Provides
    public Navigator provideNavigator(Context context) {
        return new Navigator(context);
    }
}
