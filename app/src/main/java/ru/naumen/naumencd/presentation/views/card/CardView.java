package ru.naumen.naumencd.presentation.views.card;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.naumen.naumencd.models.Item;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CardView extends MvpView {

    void showWait();

    void removeWait();

    void setComputersSimilar(List<Item> computersSimilar);

    void setActionBar(String name);

    void setCompany(String name);

    void setIntroduced(String finalDate);

    void setDiscounted(String finalDate);

    void setDescription(String description);

    void setImage(String imageUrl);
}
