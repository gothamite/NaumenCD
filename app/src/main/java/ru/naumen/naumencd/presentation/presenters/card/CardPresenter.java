package ru.naumen.naumencd.presentation.presenters.card;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.app.ComputerDatabaseApp;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.presentation.presenters.BasePresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class CardPresenter extends BasePresenter {
    private final CardView view;

    private ComputerDatabaseService cdService;

   public CardPresenter(CardView view, ComputerDatabaseService cdService){
       this.view = view;
       this.cdService = cdService;
   }

    public void loadComputer(int id) {
        Timber.d("************************LoadComp" + id);

        Observable<Item> observable = cdService.getComputer(id);

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setComputer);
        unsubscribeOnDestroy(subscription);
    }

    public void loadSimilarComputers(int id) {
        Timber.d("************************Load SIMILAR Comps" + id);

        Observable<List<Item>> observable = cdService.getComputersSimilar(id);

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::setComputersSimilar);
        unsubscribeOnDestroy(subscription);
    }

    private void setComputer(Item computer) {
        SimpleDateFormat responseDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        responseDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd MMM yyyy");
        formattedDate.setTimeZone(TimeZone.getDefault());
        Date date = null;
        String finalDate;

        if (computer.getName() != null) {
            view.setActionBar(computer.getName());
        } else {
            view.setActionBar("Naumen CD");
        }

        if (computer.getCompany() != null) {
            view.setCompany(computer.getCompany().getName());
        }
        if (computer.getIntroduced() != null) {
            try {
                date = responseDate.parse(computer.getIntroduced());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            finalDate = formattedDate.format(date);
            view.setIntroduced(finalDate);
        }

        if (computer.getDiscounted() != null) {
            try {
                date = responseDate.parse(computer.getDiscounted());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            finalDate = formattedDate.format(date);
            view.setDiscounted(finalDate);
        }

        if (computer.getDescription() != null) {
            view.setDescription(computer.getDescription());
        }

        if (computer.getImageUrl() != null) {
            view.setImage(computer.getImageUrl());
        }
        view.removeWait();
    }
    public void finish() {
        onDestroy();
    }
}
