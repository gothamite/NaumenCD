package ru.naumen.naumencd.utils;


import android.content.Context;
import android.content.Intent;

import ru.naumen.naumencd.ui.activities.card.CardActivity;

public class Navigator {
    private final Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void goToNextActivity(int compId) {
        Intent intent = new Intent(context, CardActivity.class);
        intent.putExtra("SELECTED_COMPUTER_ID", compId);
        context.startActivity(intent);
    }
}
