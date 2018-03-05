package ru.naumen.naumencd.presentation.presenters.home;


import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.app.ComputerDatabaseApp;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.presentation.presenters.BasePresenter;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import ru.naumen.naumencd.utils.SharedPrefs;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class HomePresenter extends BasePresenter<HomeView> {

    @Inject
    SharedPrefs sharedPrefsPage;

    @Inject
    ComputerDatabaseService cdService;

    public HomePresenter() {
        ComputerDatabaseApp.getAppComponent().inject(this);
    }

    public void loadComputers(int page) {

        Timber.d("LoadComps" + page);

        Observable<Computers> observable = cdService.getComputers(page);
        sharedPrefsPage.putComputers(page);
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(computers -> getViewState().setComputers(computers));
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
        if (sharedPrefsPage.getComputers() != 0){
            loadComputers(sharedPrefsPage.getComputers());
        }
        else {
            loadComputers(0);
        }
    }
}