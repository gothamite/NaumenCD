package ru.naumen.naumencd.presentation.presenters.list;

import java.util.Optional;

import io.reactivex.disposables.Disposable;
import ru.naumen.naumencd.presentation.presenters.BasePresenter;
import ru.naumen.naumencd.presentation.views.list.ListView;
import ru.naumen.naumencd.repositories.ListRepository;
import ru.naumen.naumencd.utils.SchedulerProvider;
import ru.naumen.naumencd.utils.SharedPrefs;
import timber.log.Timber;

public class ListPresenter extends BasePresenter {
    private Optional<ListView> optionalView = Optional.empty();
    private final ListRepository listRepository;
    private final SharedPrefs sharedPrefsPage;
    private SchedulerProvider schedulerProvider;

    public ListPresenter(ListView listView, ListRepository listRepository, SharedPrefs sharedPrefsPage, SchedulerProvider schedulerProvider) {
        this.listRepository = listRepository;
        this.sharedPrefsPage = sharedPrefsPage;
        this.schedulerProvider = schedulerProvider;
        optionalView = Optional.of(listView);
    }

    public void loadComputers(int page) {

        Timber.d("LoadComps" + page);

        sharedPrefsPage.putComputers(page);

        Disposable disposable = listRepository.getComputers(page)
                .subscribeOn(schedulerProvider.schedulersIo())
                .observeOn(schedulerProvider.androidMainThread())
                .subscribe(comps -> optionalView.ifPresent(v -> v.setComputers(comps)),throwable ->
                                optionalView.ifPresent(v -> v.showSnackbar(throwable.getMessage())));
                        //Log.e("loadPage", throwable.getMessage(), throwable));
        unsubscribeOnDestroy(disposable);
    }

    public int calculatePages(Integer total) {
        int pages = total / 10;

        if (total % 10 != 0) {
            return (pages + 1);
        } else {
            return pages;
        }
    }

    public void loadCompsFromSharedPrefs() {
        if (sharedPrefsPage.getComputers() != 0) {
            loadComputers(sharedPrefsPage.getComputers());
        } else {
            loadComputers(0);
        }
    }

    public void finish() {
        optionalView = Optional.empty();
        onDestroy();
    }
}