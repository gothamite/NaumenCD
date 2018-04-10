package ru.naumen.naumencd.repositories;

import java.util.List;

import io.reactivex.Observable;
import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;

public interface CardRepository {
    Observable<ItemEntity> getComputer(int id);
    Observable<List<SimilarItemEntity>> getComputersSimilar(int id);
}
