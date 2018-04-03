package ru.naumen.naumencd.di.home;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.naumen.naumencd.app.ListApi;
import ru.naumen.naumencd.presentation.presenters.list.ListPresenter;
import ru.naumen.naumencd.presentation.views.list.ListView;
import ru.naumen.naumencd.repositories.ListRepository;
import ru.naumen.naumencd.room.AppDatabase;
import ru.naumen.naumencd.ui.adapters.list.ComputersListAdapter;
import ru.naumen.naumencd.utils.Timer;
import ru.naumen.naumencd.utils.Navigator;
import ru.naumen.naumencd.utils.SharedPrefs;

@Module
public class ListModule {
    private final ListView listView;

    public ListModule(ListView listView) {
        this.listView = listView;
    }

    @Provides
    @ListScope
    public SharedPrefs provideSharedPrefers(Context context) {
        return new SharedPrefs(context);
    }

    @Provides
    @ListScope
    ListPresenter provideListPresenter(ListRepository listRepository, SharedPrefs sharedPrefsPage) {
        return new ListPresenter(listView, listRepository, sharedPrefsPage);
    }

    @Provides
    @ListScope
    ComputersListAdapter provideListAdapter(Navigator navigator) {
        return new ComputersListAdapter(navigator);
    }

    @Provides
    @ListScope
    public ListRepository provideListRepository(ListApi listApi, AppDatabase appDatabase, Timer hashMap) {
        return new ListRepository(listApi, appDatabase, hashMap);
    }

    @Provides
    @ListScope
    public ListApi provideApi(Retrofit retrofit) {
        return retrofit.create(ListApi.class);
    }

}
