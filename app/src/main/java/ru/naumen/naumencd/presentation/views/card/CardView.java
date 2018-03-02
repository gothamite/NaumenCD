package ru.naumen.naumencd.presentation.views.card;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.naumen.naumencd.models.Computers;
import ru.naumen.naumencd.models.Item;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CardView extends MvpView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void setComputer(Item computer);

    void setComputersSimilar(List<Item> computersSimilar);
}
