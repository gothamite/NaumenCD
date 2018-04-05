package ru.naumen.naumencd.ui.fragments.card;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.di.card.CardComponent;
import ru.naumen.naumencd.di.card.CardModule;
import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;
import ru.naumen.naumencd.presentation.presenters.card.CardPresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;
import ru.naumen.naumencd.ui.activities.home.HomeActivity;
import ru.naumen.naumencd.ui.adapters.card.ComputersSimilarAdapter;
import ru.naumen.naumencd.utils.ResizableCustomView;

public class CardFragment extends Fragment implements CardView {

    private Bundle selectedComp;
    private static final int MAX_LINES = 2;
    private CardComponent cardComponent;

    @Inject
    CardPresenter cardPresenter;

    @Inject
    ComputersSimilarAdapter adapter;

    @BindView(R.id.computers_similar)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBarLoading;

    @BindView(R.id.looking_for)
    TextView lookingFor;

    @BindView(R.id.introduced)
    TextView introduced;

    @BindView(R.id.introduced_dis)
    TextView introducedDis;

    @BindView(R.id.discontinued)
    TextView discontinued;

    @BindView(R.id.discontinued_dis)
    TextView discontinuedDis;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.description_dis)
    TextView descriptionDis;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.company)
    TextView company;

    @BindView(R.id.company_dis)
    TextView companyDis;

    @BindView(R.id.container)
    View container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        ButterKnife.bind(this, view);

        addCardComponent().inject(this);

        showWait();

        selectedComp = getArguments();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        cardPresenter.loadComputer(selectedComp.getInt("SELECTED_COMPUTER_ID"));
        cardPresenter.loadSimilarComputers(selectedComp.getInt("SELECTED_COMPUTER_ID"));
        return view;
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
    public void showSnackbar(String message) {
        Snackbar.make(container, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", view -> {
                    cardPresenter.loadComputer(selectedComp.getInt("SELECTED_COMPUTER_ID"));
                    cardPresenter.loadSimilarComputers(selectedComp.getInt("SELECTED_COMPUTER_ID"));
                }).show();
    }

    @Override
    public void setComputersSimilar(List<? extends SimilarItemEntity> computersSimilar) {
        adapter.setComputersList(computersSimilar);
        lookingFor.setVisibility(View.VISIBLE);
    }

    @Override
    public void setActionBar(String name) {
        HomeActivity activity = (HomeActivity) getActivity();
        activity.setActionBar(name);
    }


    @Override
    public void setCompany(String name) {
        company.setText(name);
        company.setVisibility(View.VISIBLE);
        companyDis.setVisibility(View.VISIBLE);
    }

    @Override
    public void setIntroduced(String finalDate) {
        introduced.setText(finalDate);
        introduced.setVisibility(View.VISIBLE);
        introducedDis.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDiscounted(String finalDate) {
        discontinued.setText(finalDate);
        discontinued.setVisibility(View.VISIBLE);
        discontinuedDis.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDescription(String descriptionText) {
        description.setText(descriptionText);
        description.setVisibility(View.VISIBLE);
        ResizableCustomView.doResizeTextView(description, MAX_LINES, "... More", true);
        descriptionDis.setVisibility(View.VISIBLE);
    }

    @Override
    public void setImage(String imageUrl) {
        Picasso.with(getContext()).load(imageUrl).into(image);
        image.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        cardPresenter.finish();
        clearCardComponent();
        super.onStop();
    }

    public CardComponent addCardComponent() {
        if (cardComponent == null) {
            HomeActivity activity = (HomeActivity) getActivity();
            cardComponent = activity.getActivityComponent().addCardComponent(new CardModule(this));
        }
        return cardComponent;
    }

    public void clearCardComponent() {
        cardComponent = null;
    }
}

