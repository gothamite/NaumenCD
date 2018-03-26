package ru.naumen.naumencd.presentation.views.home;

import ru.naumen.naumencd.models.Computers;

public interface HomeView {

    void showWait();

    void removeWait();

    void setComputers(Computers computers);

}
