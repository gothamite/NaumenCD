package ru.naumen.naumencd.di.home;

import dagger.Subcomponent;
import ru.naumen.naumencd.ui.activities.home.HomeActivity;

@Subcomponent(modules = {HomeModule.class})
@HomeScope
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
