package ru.naumen.naumencd.ui.activities.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import ru.naumen.naumencd.R;
import ru.naumen.naumencd.app.ComputerDatabaseApp;
import ru.naumen.naumencd.di.activity.ActivityComponent;
import ru.naumen.naumencd.di.activity.ActivityModule;
import ru.naumen.naumencd.utils.Navigator;

public class HomeActivity extends AppCompatActivity {
    private ActivityComponent activityComponent;

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addActivityComponent().inject(this);

        navigator.startMainFragment();
    }

    public ActivityComponent addActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ComputerDatabaseApp.getAppComponent().addActivityComponent(new ActivityModule(getFragmentManager()));
        }
        return activityComponent;
    }

    public void clearActivityComponent() {
        activityComponent = null;
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void setActionBar(String name) {
       getSupportActionBar().setTitle(name);
    }

    @Override
    protected void onDestroy() {
        clearActivityComponent();
        super.onDestroy();
    }
}