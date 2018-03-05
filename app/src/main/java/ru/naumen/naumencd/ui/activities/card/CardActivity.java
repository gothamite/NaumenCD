package ru.naumen.naumencd.ui.activities.card;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.presentation.presenters.card.CardPresenter;
import ru.naumen.naumencd.presentation.views.card.CardView;
import ru.naumen.naumencd.ui.adapters.card.ComputersSimilarAdapter;
import ru.naumen.naumencd.utils.ResizableCustomView;

public class CardActivity extends MvpAppCompatActivity implements CardView {

    public static final String TAG = "CardActivity";
    private Bundle selectedComp;
    private ComputersSimilarAdapter adapter;
    private static final int MAX_LINES = 2;

    @InjectPresenter
    CardPresenter cardPresenter;

    @BindView(R.id.nested)
    NestedScrollView nestedScrollView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
        showWait();

        selectedComp = getIntent().getExtras();
        adapter = new ComputersSimilarAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        cardPresenter.loadComputer(selectedComp.getInt("SELECTED_COMPUTER_ID"));
        cardPresenter.loadSimilarComputers(selectedComp.getInt("SELECTED_COMPUTER_ID"));
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
    public void setComputersSimilar(List<Item> computersSimilar) {
        adapter.setComputersList(computersSimilar);
        lookingFor.setVisibility(View.VISIBLE);
    }

    @Override
    public void setActionBar(String name) {
        getSupportActionBar().setTitle(name);
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
        Picasso.with(this).load(imageUrl).into(image);
        image.setVisibility(View.VISIBLE);
    }
}
