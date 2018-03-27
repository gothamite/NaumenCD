package ru.naumen.naumencd.ui.activities.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.app.ComputerDatabaseApp;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.presentation.presenters.home.HomePresenter;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import ru.naumen.naumencd.ui.adapters.home.ComputersListAdapter;
import ru.naumen.naumencd.utils.SharedPrefs;

public class HomeActivity extends AppCompatActivity implements HomeView {

    public static final String TAG = "HomeActivity";
    private ComputersListAdapter adapter;
    private int pageNumber;
    private int pageAll;
    private List<Item> comps;

    @Inject
    ComputerDatabaseService cdService;

    @Inject
    HomePresenter homePresenter;

    @Inject
    SharedPrefs sharedPrefsPage;

    @BindView(R.id.computers_list)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBarLoading;

    @BindView(R.id.pages)
    TextView pages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ComputerDatabaseApp.getAppComponent().inject(this);
        homePresenter.onCreate(this);

        showWait();
        adapter = new ComputersListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        homePresenter.loadCompsFromSharedPrefs();
    }

    @Override
    public void showWait() {
        progressBarLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBarLoading.setVisibility(View.GONE);
    }

    @Override
    public void setComputers(Computers computers) {
        comps = computers.getItems();
        adapter.setComputersList(comps);
        pageNumber = computers.getPage();
        pageAll = homePresenter.calculatePages(computers.getTotal());
        pages.setText("Page " + (pageNumber + 1) + " of " + pageAll);
        removeWait();
    }

    public void onPreviousClick(View view) {
        if (pageNumber != 0) {
            showWait();
            homePresenter.loadComputers(pageNumber - 1);
        }
    }

    public void onNextClick(View view) {
        if (pageNumber != pageAll) {
            showWait();
            homePresenter.loadComputers(pageNumber + 1);
        }
    }

    @Override
    protected void onDestroy() {
        homePresenter.finish();
        super.onDestroy();
    }
}