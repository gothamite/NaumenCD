package ru.naumen.naumencd.di;

import dagger.Subcomponent;
import ru.naumen.naumencd.di.module.CardModule;
import ru.naumen.naumencd.ui.activities.card.CardActivity;

@Subcomponent(modules = {CardModule.class})
@CardScope
public interface CardComponent {
    void inject(CardActivity cardActivity);
}
