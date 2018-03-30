package ru.naumen.naumencd.presentation.presenters.list;


import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.naumen.naumencd.models.Computers;
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

        Observable<Computers> observable = listRepository.getComputers(page);
        sharedPrefsPage.putComputers(page);
        Disposable disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comps -> optionalView.ifPresent(v -> v.setComputers(comps)),throwable -> {});
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