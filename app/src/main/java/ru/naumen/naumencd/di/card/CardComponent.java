package ru.naumen.naumencd.di.card;

import dagger.Subcomponent;
import ru.naumen.naumencd.ui.fragments.card.CardFragment;

@Subcomponent(modules = {CardModule.class, CardDataSourceModule.class})
@CardScope
public interface CardComponent {
    void inject(CardFragment cardFragment);
}
