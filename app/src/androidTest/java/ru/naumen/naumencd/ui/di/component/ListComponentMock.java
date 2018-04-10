package ru.naumen.naumencd.ui.di.component;

import dagger.Subcomponent;
import ru.naumen.naumencd.di.home.ListComponent;
import ru.naumen.naumencd.di.home.ListModule;
import ru.naumen.naumencd.di.home.ListScope;

@Subcomponent(modules = {ListModule.class})
@ListScope
interface ListComponentMock extends ListComponent {
}
