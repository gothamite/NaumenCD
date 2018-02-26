package ru.naumen.naumencd.presentation.presenters.home;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.app.ComputerDatabaseApp;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {

    @Inject
    ComputerDatabaseService mCdService;

    public HomePresenter() {
        ComputerDatabaseApp.getAppComponent().inject(this);
    }

    public void loadData(int page) {
        final Observable<Computers> observable = mCdService.getComputers(page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Computers>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Computers cityListResponse) {

                    }
                });
    }
}