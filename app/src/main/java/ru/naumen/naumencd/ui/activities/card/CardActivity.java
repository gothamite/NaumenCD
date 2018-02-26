package ru.naumen.naumencd.ui.activities.card;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ru.naumen.naumencd.R;
import ru.naumen.naumencd.presentation.presenters.card.CardPresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;

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
}
