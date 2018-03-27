package ru.naumen.naumencd.di.card;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.presentation.presenters.card.CardPresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;
import ru.naumen.naumencd.utils.Navigator;
import ru.naumen.naumencd.ui.adapters.card.ComputersSimilarAdapter;

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

    @Provides
    @CardScope
    ComputersSimilarAdapter provideSimilarAdapter(Navigator navigator){
        return new ComputersSimilarAdapter(navigator);
    }
}
