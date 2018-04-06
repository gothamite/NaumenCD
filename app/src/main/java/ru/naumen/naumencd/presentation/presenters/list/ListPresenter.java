package ru.naumen.naumencd.presentation.presenters.list;


import java.util.Optional;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.naumen.naumencd.presentation.presenters.BasePresenter;
import ru.naumen.naumencd.presentation.views.list.ListView;
import ru.naumen.naumencd.repositories.ListRepository;
import ru.naumen.naumencd.utils.SharedPrefs;
import timber.log.Timber;

public class ListPresenter extends BasePresenter {
    private Optional<ListView> optionalView = Optional.empty();
    private final ListRepository listRepository;
    private final SharedPrefs sharedPrefsPage;

    public ListPresenter(ListView listView, ListRepository listRepository, SharedPrefs sharedPrefsPage) {
        this.listRepository = listRepository;
        this.sharedPrefsPage = sharedPrefsPage;
        optionalView = Optional.of(listView);
    }

    public void loadComputers(int page) {

        Timber.d("LoadComps" + page);

        sharedPrefsPage.putComputers(page);

        Disposable disposable = listRepository.getComputers(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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