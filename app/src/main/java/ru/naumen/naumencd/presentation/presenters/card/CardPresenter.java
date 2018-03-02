package ru.naumen.naumencd.presentation.presenters.card;


import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.app.ComputerDatabaseApp;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.presentation.presenters.BasePresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class CardPresenter extends BasePresenter<CardView> {

    @Inject
    ComputerDatabaseService cdService; //TODO переименовать всю  ересь аля mSomething

    public CardPresenter() {
        ComputerDatabaseApp.getAppComponent().inject(this);
    }

    public void loadComputer(int id) {
        getViewState().showWait();

        Timber.d("************************LoadComp" + id);

        Observable<Item> observable = cdService.getComputer(id);

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(computer -> getViewState().setComputer(computer));
        unsubscribeOnDestroy(subscription);

        getViewState().removeWait();
    }

    public void loadSimilarComputers(int id) {
        getViewState().showWait();

        Timber.d("************************Load SIMILAR Comps" + id);

        Observable<List<Item>> observable = cdService.getComputersSimilar(id);

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(similarComputers -> getViewState().setComputersSimilar(similarComputers));
        unsubscribeOnDestroy(subscription);

        getViewState().removeWait();
    }
}
