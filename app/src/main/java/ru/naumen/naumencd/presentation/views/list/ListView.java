package ru.naumen.naumencd.presentation.views.list;

import ru.naumen.naumencd.models.dbdto.PageEntity;

public interface ListView {

    void showWait();

    void removeWait();

    void setComputers(PageEntity computers);

    void showSnackbar(String message);
}
