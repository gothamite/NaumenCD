package ru.naumen.naumencd.di.module;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.di.activity.ActivityScope;
import ru.naumen.naumencd.presentation.views.card.CardView;
import ru.naumen.naumencd.utils.Navigator;


@Module
public class NavigatorModule {
    @ActivityScope
    @Provides
    public Navigator provideNavigator(Context context, FragmentManager fragmentManager) {
        return new Navigator(context, fragmentManager);
    }
}
