package ru.naumen.naumencd.presentation.presenters.card;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.presentation.presenters.BasePresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class CardPresenter extends BasePresenter {

    private Optional<CardView> mView = Optional.empty();

    private ComputerDatabaseService cdService;

    public CardPresenter(ComputerDatabaseService cdService) {
        this.cdService = cdService;
    }

    public void onCreate(CardView view) {
        this.mView = Optional.of(view);
    }

    public void loadComputer(int id) {
        Timber.d("************************LoadComp" + id);

        Observable<Item> observable = cdService.getComputer(id);

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> mView.ifPresent(cardView -> setComputer(item, cardView)));
        unsubscribeOnDestroy(subscription);
    }

    public void loadSimilarComputers(int id) {
        Timber.d("************************Load SIMILAR Comps" + id);

        Observable<List<Item>> observable = cdService.getComputersSimilar(id);

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(similar -> mView.ifPresent(v -> v.setComputersSimilar(similar)));
        unsubscribeOnDestroy(subscription);
    }

    private void setComputer(Item computer, CardView cardView) {
        SimpleDateFormat responseDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        responseDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd MMM yyyy");
        formattedDate.setTimeZone(TimeZone.getDefault());
        Date date = null;
        String finalDate;

        if (computer.getName() != null) {
            cardView.setActionBar(computer.getName());
        } else {
            cardView.setActionBar("Naumen CD");
        }

        if (computer.getCompany() != null) {
            cardView.setCompany(computer.getCompany().getName());
        }
        if (computer.getIntroduced() != null) {
            try {
                date = responseDate.parse(computer.getIntroduced());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            finalDate = formattedDate.format(date);
            cardView.setIntroduced(finalDate);
        }

        if (computer.getDiscounted() != null) {
            try {
                date = responseDate.parse(computer.getDiscounted());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            finalDate = formattedDate.format(date);
            cardView.setDiscounted(finalDate);
        }

        if (computer.getDescription() != null) {
            cardView.setDescription(computer.getDescription());
        }

        if (computer.getImageUrl() != null) {
            cardView.setImage(computer.getImageUrl());
        }
        cardView.removeWait();
    }

    public void finish() {
        mView = Optional.empty();
        onDestroy();
    }
}
