package ru.naumen.naumencd.repositories;

import io.reactivex.Observable;
import ru.naumen.naumencd.models.dbdto.PageEntity;

public interface ListRepository {
    Observable<PageEntity> getComputers(int page);
}
