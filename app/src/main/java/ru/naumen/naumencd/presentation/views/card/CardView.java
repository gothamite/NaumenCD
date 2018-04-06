package ru.naumen.naumencd.presentation.views.card;

import java.util.List;

import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;

public interface CardView {

    void showWait();

    void removeWait();

    void showSnackbar(String message);

    void setComputersSimilar(List<SimilarItemEntity> computersSimilar);

    void setActionBar(String name);

    void setCompany(String name);

    void setIntroduced(String finalDate);

    void setDiscounted(String finalDate);

    void setDescription(String description);

    void setImage(String imageUrl);
}
