package ru.naumen.naumencd.di.activity;

import android.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.di.card.CardScope;

@Module
public class ActivityModule {
    private final FragmentManager fragmentManager;

    public ActivityModule(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() {
        return fragmentManager;
    }
}
