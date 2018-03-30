package ru.naumen.naumencd.ui.fragments.list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.di.home.ListComponent;
import ru.naumen.naumencd.di.home.ListModule;
import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.presentation.presenters.list.ListPresenter;
import ru.naumen.naumencd.presentation.views.list.ListView;
import ru.naumen.naumencd.ui.activities.home.HomeActivity;
import ru.naumen.naumencd.ui.adapters.list.ComputersListAdapter;

public class ListFragment extends Fragment implements ListView {

    private int pageNumber;
    private int pageAll;
    private List<Item> comps;
    private ListComponent listComponent;

    @Inject
    ListPresenter listPresenter;

    @Inject
    ComputersListAdapter adapter;

    @BindView(R.id.computers_list)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBarLoading;

    @BindView(R.id.pages)
    TextView pages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        addHomeComponent().inject(this);
        showWait();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        listPresenter.loadCompsFromSharedPrefs();
        setActionBar();
        return view;
    }

    public void setActionBar() {
        HomeActivity activity = (HomeActivity) getActivity();
        activity.setActionBar("Naumen CD");
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
        pageAll = listPresenter.calculatePages(computers.getTotal());
        pages.setText("Page " + (pageNumber + 1) + " of " + pageAll);
        removeWait();
    }

    @OnClick(R.id.previous)
    public void onPreviousClick() {
        if (pageNumber != 0) {
            showWait();
            listPresenter.loadComputers(pageNumber - 1);
        }
    }

    @OnClick(R.id.next)
    public void onNextClick() {
        if (pageNumber != pageAll) {
            showWait();
            listPresenter.loadComputers(pageNumber + 1);
        }
    }

    public ListComponent addHomeComponent() {
        if (listComponent == null) {
            HomeActivity activity = (HomeActivity) getActivity();
            listComponent = activity.getActivityComponent().addListComponent(new ListModule(this));
        }
        return listComponent;
    }

    public void clearHomeComponent() {
        listComponent = null;
    }

    @Override
    public void onDestroyView() {
        listPresenter.finish();
        clearHomeComponent();
        super.onDestroyView();
    }
}
