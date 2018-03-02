package ru.naumen.naumencd.ui.activities.card;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.presentation.presenters.card.CardPresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;

//TODO
public class CardActivity extends MvpAppCompatActivity implements CardView {

    public static final String TAG = "CardActivity";
    @InjectPresenter
    CardPresenter mCardPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, CardActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void setComputer(Item computer) {

    }

    @Override
    public void setComputersSimilar(List<Item> computersSimilar) {

    }
}
