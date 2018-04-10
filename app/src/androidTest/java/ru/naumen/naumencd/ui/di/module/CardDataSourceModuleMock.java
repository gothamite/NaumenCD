package ru.naumen.naumencd.ui.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.repositories.CardRepository;

import static org.mockito.Mockito.mock;

@Module
public class CardDataSourceModuleMock {
    @Provides
    @Singleton
    public CardRepository provideCardRepository() {
        return mock(CardRepository.class);
    }
}
