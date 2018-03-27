package ru.naumen.naumencd.di;

import dagger.Subcomponent;
import ru.naumen.naumencd.di.module.HomeModule;
import ru.naumen.naumencd.ui.activities.home.HomeActivity;

@Subcomponent(modules = {HomeModule.class})
@HomeScope
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
