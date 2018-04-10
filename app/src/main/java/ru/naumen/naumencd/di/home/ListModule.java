package ru.naumen.naumencd.di.home;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.presentation.presenters.list.ListPresenter;
import ru.naumen.naumencd.presentation.views.list.ListView;
import ru.naumen.naumencd.repositories.ListRepository;
import ru.naumen.naumencd.ui.adapters.list.ComputersListAdapter;
import ru.naumen.naumencd.utils.Navigator;
import ru.naumen.naumencd.utils.SchedulerProvider;
import ru.naumen.naumencd.utils.SharedPrefs;

@Module
public class ListModule {
    private final ListView listView;

    public ListModule(ListView listView) {
        this.listView = listView;
    }

    @Provides
    @ListScope
    ListPresenter provideListPresenter(ListRepository listRepository, SharedPrefs sharedPrefsPage, SchedulerProvider schedulerProvider) {
        return new ListPresenter(listView, listRepository, sharedPrefsPage, schedulerProvider);
    }

    @Provides
    @ListScope
    ComputersListAdapter provideListAdapter(Navigator navigator) {
        return new ComputersListAdapter(navigator);
    }

    @Provides
    @ListScope
    public SharedPrefs provideSharedPrefers(Context context) {
        return new SharedPrefs(context);
    }
}
