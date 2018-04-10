package ru.naumen.naumencd.ui.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.repositories.ListRepository;

import static org.mockito.Mockito.mock;

@Module
public class ListDataSourceModuleMock {
    @Provides
    @Singleton
    public ListRepository provideListRepository() {
        return mock(ListRepository.class);
    }
}
