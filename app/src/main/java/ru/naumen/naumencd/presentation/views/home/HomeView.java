package ru.naumen.naumencd.presentation.views.home;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.naumen.naumencd.models.Computers;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface HomeView extends MvpView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void setComputers(Computers computers);
}
