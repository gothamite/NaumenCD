package ru.naumen.naumencd.presentation.views.list;

import ru.naumen.naumencd.models.dto.Computers;

public interface ListView {

    void showWait();

    void removeWait();

    void setComputers(Computers computers);

}
