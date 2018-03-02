package ru.naumen.naumencd.presentation.presenters.home;


import com.arellomobile.mvp.InjectViewState;
import com.google.gson.Gson;

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
                .subscribe(computers -> getViewState().setComputers(computers));
        unsubscribeOnDestroy(subscription);

        getViewState().removeWait();
    }

/*    private int loadPrefers() {
        Gson gson = new Gson();
        String json = mSharedPrefs.getComputers();
        Computers computers = gson.fromJson(json, Computers.class);
        if (computers.getPage() != 0){
            return computers.getPage();
        }
        else {
            return  0;
        }
    }

    private int savePrefers(){
        Gson gson = new Gson();
        String json = gson.toJson(comps);
        sharedPrefs.putComputers(json);
    }*/

}