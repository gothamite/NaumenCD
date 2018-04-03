package ru.naumen.naumencd.presentation.presenters.card;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.presentation.presenters.BasePresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;
import ru.naumen.naumencd.repositories.CardRepository;
import timber.log.Timber;

public class CardPresenter extends BasePresenter {

    private Optional<CardView> optionalView = Optional.empty();
    private CardRepository cardRepository;

    public CardPresenter(CardView cardView, CardRepository cardRepository) {
        this.cardRepository = cardRepository;
        optionalView = Optional.of(cardView);
    }

    public void loadComputer(int id) {
        Timber.d("************************LoadComp" + id);

        Disposable disposable = cardRepository.getComputer(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> optionalView.ifPresent(cardView -> setComputer(item, cardView)),throwable ->
                        Log.e("loadComputer", throwable.getMessage(), throwable));
        unsubscribeOnDestroy(disposable);
    }

    public void loadSimilarComputers(int id) {
        Timber.d("************************Load SIMILAR Comps" + id);

        Disposable disposable = cardRepository.getComputersSimilar(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(similar -> optionalView.ifPresent(v -> v.setComputersSimilar(similar)),throwable ->
                        Log.e("loadSimilarComp", throwable.getMessage(), throwable));
        unsubscribeOnDestroy(disposable);
    }

    private void setComputer(ItemEntity computer, CardView cardView) {
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
        optionalView = Optional.empty();
        onDestroy();
    }
}
