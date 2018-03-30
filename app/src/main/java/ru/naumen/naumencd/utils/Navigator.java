package ru.naumen.naumencd.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import ru.naumen.naumencd.R;
import ru.naumen.naumencd.ui.fragments.card.CardFragment;
import ru.naumen.naumencd.ui.fragments.list.ListFragment;

public class Navigator {
    private final Context context;
    private final FragmentManager fragmentManager;
    private Bundle bundle;
    private Fragment cardFragment;
    private Fragment listFragment;

    public Navigator(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    public void goToNextFragment(int compId) {
        cardFragment = new CardFragment();
        bundle = new Bundle();
        bundle.putInt("SELECTED_COMPUTER_ID", compId);
        cardFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, cardFragment);
        fragmentTransaction.addToBackStack("CardFragment");
        fragmentTransaction.commit();
    }

    public void startMainFragment() {
        int backStackCount = fragmentManager.getBackStackEntryCount();

        if (backStackCount != 0) {
            return;
        } else {
            listFragment = new ListFragment();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, listFragment);
        fragmentTransaction.commit();
    }

}

