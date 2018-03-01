package ru.naumen.naumencd.presentation.presenters.home;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.app.ComputerDatabaseApp;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {

    @Inject
    ComputerDatabaseService mCdService; //TODO переименовать всю  ересь аля mSomething

    public HomePresenter() {
        ComputerDatabaseApp.getAppComponent().inject(this);
    }

    public void loadComputers(int page) {
        getViewState().showWait();
        Timber.d("LoadComps" + page);

        Observable<Computers> observable = mCdService.getComputers(page);

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(computers -> getViewState().setComputers(computers));//TODO добавить unsubscribe и вызывать его в onDestroyView
        getViewState().removeWait();
     // TODO  compositeSubscription.add(subscription); ?
    }
}