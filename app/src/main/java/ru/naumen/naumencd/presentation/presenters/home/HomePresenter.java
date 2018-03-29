package ru.naumen.naumencd.presentation.presenters.home;


import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.presentation.presenters.BasePresenter;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import ru.naumen.naumencd.repositories.HomeRepository;
import ru.naumen.naumencd.utils.SharedPrefs;
import timber.log.Timber;

public class HomePresenter extends BasePresenter {
    private Optional<HomeView> optionalView = Optional.empty();
    private final HomeRepository homeRepository;
    private final SharedPrefs sharedPrefsPage;

    public HomePresenter(HomeView homeView, HomeRepository homeRepository, SharedPrefs sharedPrefsPage) {
        this.homeRepository = homeRepository;
        this.sharedPrefsPage = sharedPrefsPage;
        optionalView = Optional.of(homeView);
    }

    public void loadComputers(int page) {

        Timber.d("LoadComps" + page);

        Observable<Computers> observable = homeRepository.getComputers(page);
        sharedPrefsPage.putComputers(page);
        Disposable disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comps -> optionalView.ifPresent(v -> v.setComputers(comps)));
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