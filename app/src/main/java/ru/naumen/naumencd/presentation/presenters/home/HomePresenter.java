package ru.naumen.naumencd.presentation.presenters.home;


import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.presentation.presenters.BasePresenter;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import ru.naumen.naumencd.utils.SharedPrefs;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class HomePresenter extends BasePresenter {
    private final HomeView view;
    private final ComputerDatabaseService cdService;
    private final SharedPrefs sharedPrefsPage;

    public HomePresenter(HomeView view, ComputerDatabaseService cdService, SharedPrefs sharedPrefsPage) {
        this.view = view;
        this.cdService = cdService;
        this.sharedPrefsPage = sharedPrefsPage;
    }

    public void loadComputers(int page) {

        Timber.d("LoadComps" + page);

        Observable<Computers> observable = cdService.getComputers(page);
        sharedPrefsPage.putComputers(page);
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::setComputers);
        unsubscribeOnDestroy(subscription);
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
        onDestroy();
    }
}