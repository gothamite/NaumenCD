package ru.naumen.naumencd.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.naumen.naumencd.di.module.CardModule;
import ru.naumen.naumencd.di.module.ContextModule;
import ru.naumen.naumencd.di.module.HomeModule;
import ru.naumen.naumencd.di.module.NetworkModule;
import ru.naumen.naumencd.di.module.RetrofitModule;

@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, RetrofitModule.class})
public interface AppComponent {

    HomeComponent addHomeComponent(HomeModule homeModule);

    CardComponent addCardComponent(CardModule cardModule);
}