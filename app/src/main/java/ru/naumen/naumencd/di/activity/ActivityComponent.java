package ru.naumen.naumencd.di.activity;

import dagger.Subcomponent;
import ru.naumen.naumencd.di.card.CardComponent;
import ru.naumen.naumencd.di.card.CardModule;
import ru.naumen.naumencd.di.home.ListComponent;
import ru.naumen.naumencd.di.home.ListModule;
import ru.naumen.naumencd.di.module.NavigatorModule;
import ru.naumen.naumencd.ui.activities.home.HomeActivity;

@Subcomponent(modules = {ActivityModule.class, NavigatorModule.class})
@ActivityScope
public interface ActivityComponent {

    ListComponent addListComponent(ListModule listModule);

    CardComponent addCardComponent(CardModule cardModule);

    void inject(HomeActivity homeActivity);
}
