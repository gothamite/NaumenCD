package ru.naumen.naumencd.ui.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.presentation.presenters.home.HomePresenter;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import ru.naumen.naumencd.ui.adapters.home.ComputersListAdapter;

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    public static final String TAG = "HomeActivity";
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @InjectPresenter
    HomePresenter mHomePresenter;

    @BindView(R.id.computers_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBarLoading;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mHomePresenter.loadData(1);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

       // mAdapter = new ComputersListAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
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
    public void getCityListSuccess(Computers computers) {

    }
}
