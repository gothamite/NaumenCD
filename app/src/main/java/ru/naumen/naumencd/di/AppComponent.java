package ru.naumen.naumencd.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.naumen.naumencd.di.card.CardComponent;
import ru.naumen.naumencd.di.card.CardModule;
import ru.naumen.naumencd.di.home.HomeComponent;
import ru.naumen.naumencd.di.module.ContextModule;
import ru.naumen.naumencd.di.home.HomeModule;
import ru.naumen.naumencd.di.module.NavigatorModule;
import ru.naumen.naumencd.di.module.NetworkModule;
import ru.naumen.naumencd.di.module.RetrofitModule;

@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, RetrofitModule.class, NavigatorModule.class})
public interface AppComponent {

    HomeComponent addHomeComponent(HomeModule homeModule);

    CardComponent addCardComponent(CardModule cardModule);
}