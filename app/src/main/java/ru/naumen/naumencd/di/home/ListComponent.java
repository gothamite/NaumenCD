package ru.naumen.naumencd.di.home;

import dagger.Subcomponent;
import ru.naumen.naumencd.ui.fragments.list.ListFragment;

@Subcomponent(modules = {ListModule.class})
@ListScope
public interface ListComponent {
    void inject(ListFragment listFragment);
}
