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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.presentation.presenters.home.HomePresenter;
import ru.naumen.naumencd.presentation.views.home.HomeView;
import ru.naumen.naumencd.ui.adapters.home.ComputersListAdapter;
import timber.log.Timber;

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    public static final String TAG = "HomeActivity";
    private ComputersListAdapter mAdapter;
    private int pageNumber;

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
        mHomePresenter.loadComputers(0); // TODO если приложение открывается из свернутого вида, то возвращать на текущую страницу
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
        mAdapter = new ComputersListAdapter(comps); //TODO каждый раз добавляется новый адаптер
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        pageNumber = computers.getPage();
        pages.setText("Page " + (pageNumber + 1) + " of 58");//TODO посчитать количество страниц?
    }

    public void onPreviousClick(View view) {
        if (pageNumber != 0){
            mHomePresenter.loadComputers(pageNumber-1);
        }
    }

    public void onNextClick(View view) {
       // if (pageNumber != 57){
            mHomePresenter.loadComputers(pageNumber+1);
       // }
    }
}
