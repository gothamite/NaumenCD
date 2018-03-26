package ru.naumen.naumencd.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.naumen.naumencd.di.module.ContextModule;
import ru.naumen.naumencd.di.module.NetworkModule;
import ru.naumen.naumencd.di.module.RetrofitModule;
import ru.naumen.naumencd.presentation.presenters.card.CardPresenter;
import ru.naumen.naumencd.presentation.presenters.home.HomePresenter;
import ru.naumen.naumencd.ui.activities.card.CardActivity;
import ru.naumen.naumencd.ui.activities.home.HomeActivity;

@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, RetrofitModule.class})
public interface AppComponent {

    void inject(HomeActivity homeActivity);

    void inject(CardActivity cardActivity);
}