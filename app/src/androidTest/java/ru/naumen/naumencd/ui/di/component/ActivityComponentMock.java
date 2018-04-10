package ru.naumen.naumencd.ui.di.component;

import dagger.Subcomponent;
import ru.naumen.naumencd.di.activity.ActivityComponent;
import ru.naumen.naumencd.di.activity.ActivityModule;
import ru.naumen.naumencd.di.activity.ActivityScope;
import ru.naumen.naumencd.di.card.CardComponent;
import ru.naumen.naumencd.di.card.CardModule;
import ru.naumen.naumencd.di.home.ListModule;
import ru.naumen.naumencd.di.module.NavigatorModule;
import ru.naumen.naumencd.ui.activities.home.HomeActivity;


@Subcomponent(modules = {ActivityModule.class, NavigatorModule.class})
@ActivityScope
public interface ActivityComponentMock extends ActivityComponent {
    @Override
    ListComponentMock addListComponent(ListModule listModule);

    @Override
    CardComponentMock addCardComponent(CardModule cardModule);

    @Override
    void inject(HomeActivity homeActivity);
}
