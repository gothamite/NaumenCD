package ru.naumen.naumencd.repositories;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ru.naumen.naumencd.app.CardApi;
import ru.naumen.naumencd.models.dbdto.CompanyDbDto;
import ru.naumen.naumencd.models.dbdto.ItemDbDto;
import ru.naumen.naumencd.models.dbdto.SimilarItemDbDto;
import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.models.dto.Item;
import ru.naumen.naumencd.models.dto.SimilarItem;
import ru.naumen.naumencd.room.AppDatabase;

public class CardRepository {

    private AppDatabase appDatabase;

    private CardApi cardApi;

    public CardRepository(CardApi cardApi, AppDatabase appDatabase) {
        this.cardApi = cardApi;
        this.appDatabase = appDatabase;
    }

    public Observable<? extends ItemEntity> getComputer(int id) {
        Observable<ItemDbDto> itemObservable = cardApi.getComputer(id)
                .map(this::transformFromApi)
                .doOnNext(item -> appDatabase.itemDao().insert(item));

        return Observable.fromCallable(() -> {
            ItemDbDto comp = appDatabase.itemDao().getId(id);
            if (comp != null) {
                return comp;
            }
            throw new IllegalStateException("Comp not found");
        }).onErrorResumeNext(itemObservable);
    }

    private ItemDbDto transformFromApi(Item item) {
        ItemDbDto itemDbDto = new ItemDbDto();
        CompanyDbDto companyDbDto = new CompanyDbDto();

        itemDbDto.setId(item.getId());
        itemDbDto.setName(item.getName());

        if (item.getImageUrl() != null) {
            itemDbDto.setImageUrl(item.getImageUrl());
        }
        if (item.getIntroduced() != null) {
            itemDbDto.setIntroduced(item.getIntroduced());
        }
        if (item.getDiscounted() != null) {
            itemDbDto.setDiscounted(item.getDiscounted());
        }
        if (item.getDescription() != null) {
            itemDbDto.setDescription(item.getDescription());
        }
        if (item.getCompany() != null) {
            companyDbDto.setName(item.getCompany().getName());
            companyDbDto.setId(item.getCompany().getId());
            itemDbDto.setCompany(companyDbDto);
        }

        return itemDbDto;
    }

    public Observable<List<SimilarItemDbDto>> getComputersSimilar(int id) { //TODO не получилось с <? extends Interface>
        Observable<List<SimilarItemDbDto>> listObservable = cardApi.getComputersSimilar(id)
                .subscribeOn(Schedulers.io())
                .map(this::transformSimilarFromApi)
                .doOnNext(similarItemList -> {
                    for (SimilarItemDbDto similarItemDbDto : similarItemList) {
                        similarItemDbDto.setItemId(id);
                        appDatabase.similarItemDao().insert(similarItemDbDto);
                    }
                });

        return Observable.fromCallable(() -> {
            List<SimilarItemDbDto> similarList = appDatabase.similarItemDao().getSimilarListById(id);
            if (similarList.size() != 0) {
                return similarList;
            }
            throw new IllegalStateException("SimilarList not found");
        }).onErrorResumeNext(listObservable);
    }


    private List<SimilarItemDbDto> transformSimilarFromApi(List<SimilarItem> similarItems) {
        List<SimilarItemDbDto> similarItemDbDtoList = new ArrayList<>();

        for (int i = 0; i < similarItems.size(); i++) {
            SimilarItemDbDto similarItemDbDto = new SimilarItemDbDto();
            similarItemDbDto.setId(similarItems.get(i).getId());
            similarItemDbDto.setName(similarItems.get(i).getName());
            similarItemDbDtoList.add(similarItemDbDto);
        }
        return similarItemDbDtoList;
    }
}
