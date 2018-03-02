package ru.naumen.naumencd.ui.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.presentation.presenters.home.HomePresenter;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import ru.naumen.naumencd.ui.activities.card.CardActivity;
import ru.naumen.naumencd.ui.adapters.home.ComputersListAdapter;
import ru.naumen.naumencd.utils.SharedPrefs;

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    public static final String TAG = "HomeActivity";
    public static final String SAVE_COMPUTERS = "SAVE_COMPUTERS";
    private ComputersListAdapter mAdapter;
    private SharedPrefs sharedPrefs;
    private int pageNumber;
    private Intent intent;

    @InjectPresenter
    HomePresenter mHomePresenter;

    @BindView(R.id.computers_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBarLoading;

    @BindView(R.id.pages)
    TextView pages;

    List<Item> comps;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mAdapter = new ComputersListAdapter();
        mAdapter.setListener((pos) -> {
            prepareIntent(pos);
            startActivity(intent);
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mHomePresenter.loadComputers(0);

        sharedPrefs = new SharedPrefs(this);

        mHomePresenter.loadComputers(0); // TODO если приложение открывается заново при нехватке памяти то берем из prefs
    }


    //TODO для ProgressBar настроить
    @Override
    public void showWait() {
        progressBarLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBarLoading.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void setComputers(Computers computers) {
        comps = computers.getItems();
        mAdapter.setComputersList(comps);
        pageNumber = computers.getPage();
        pages.setText("Page " + (pageNumber + 1) + " of " + calculatePages(computers.getTotal()));
    }

    private String calculatePages(Integer total) {

        int pages = total/10;

        if (total % 10 != 0) {
            return String.valueOf(pages + 1);
        } else {
            return String.valueOf(pages);
        }
    }

    public void onPreviousClick(View view) {
        if (pageNumber != 0) {
            mHomePresenter.loadComputers(pageNumber - 1);
        }
    }

    public void onNextClick(View view) {
        if (pageNumber != 57) {
            mHomePresenter.loadComputers(pageNumber + 1);
        }
    }

    private void prepareIntent(int position) {
        intent = new Intent(this, CardActivity.class);
        intent.putExtra("SELECTED_COMPUTER_ID", comps.get(position).getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
