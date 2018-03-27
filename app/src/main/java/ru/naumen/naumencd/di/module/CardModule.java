package ru.naumen.naumencd.di.module;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.di.CardScope;
import ru.naumen.naumencd.presentation.presenters.card.CardPresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;

@Module
public class CardModule {
    private final CardView cardView;

    public CardModule(CardView cardView) {
        this.cardView = cardView;
    }

    @Provides
    @CardScope
    CardPresenter provideCardPresenter(ComputerDatabaseService cdService) {
        return new CardPresenter(cardView, cdService);
    }
}
