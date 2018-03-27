package ru.naumen.naumencd.di.card;

import dagger.Subcomponent;
import ru.naumen.naumencd.ui.activities.card.CardActivity;

@Subcomponent(modules = {CardModule.class})
@CardScope
public interface CardComponent {
    void inject(CardActivity cardActivity);
}
