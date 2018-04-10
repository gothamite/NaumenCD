package ru.naumen.naumencd.app;

import ru.naumen.naumencd.di.Injector;
import ru.naumen.naumencd.di.module.ContextModule;
import ru.naumen.naumencd.ui.di.component.AppComponentMock;
import ru.naumen.naumencd.ui.di.component.DaggerAppComponentMock;

public class ComputerDatabaseTestApp extends ComputerDatabaseApp {

    @Override
    protected void initializeInjector() {
        Injector injector = new Injector();
        AppComponentMock appComponentMock = DaggerAppComponentMock.builder()
                .contextModule(new ContextModule(this))
                .build();
        injector.setAppComponent(appComponentMock);
        Injector.setInjector(injector);
    }
}