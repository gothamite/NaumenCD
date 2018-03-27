package ru.naumen.naumencd.presentation.views.card;

import java.util.List;

import ru.naumen.naumencd.models.Item;


public interface CardView {

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
