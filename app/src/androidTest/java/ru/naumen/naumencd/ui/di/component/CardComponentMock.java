package ru.naumen.naumencd.ui.di.component;

import dagger.Subcomponent;
import ru.naumen.naumencd.di.card.CardComponent;
import ru.naumen.naumencd.di.card.CardModule;
import ru.naumen.naumencd.di.card.CardScope;

@Subcomponent(modules = {CardModule.class})
@CardScope
interface CardComponentMock extends CardComponent {
}
