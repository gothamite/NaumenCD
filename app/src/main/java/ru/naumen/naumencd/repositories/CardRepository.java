package ru.naumen.naumencd.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import ru.naumen.naumencd.app.CardApi;
import ru.naumen.naumencd.models.dbdto.CompanyDbDto;
import ru.naumen.naumencd.models.dbdto.ItemDbDto;
import ru.naumen.naumencd.models.dbdto.SimilarItemDbDto;
import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;
import ru.naumen.naumencd.models.dto.Item;
import ru.naumen.naumencd.models.dto.SimilarItem;
import ru.naumen.naumencd.room.AppDatabase;
import ru.naumen.naumencd.utils.SchedulerProvider;
import ru.naumen.naumencd.utils.Timer;

public class CardRepository {

    private AppDatabase appDatabase;
    private CardApi cardApi;
    private Timer timer;
    private SchedulerProvider schedulerProvider;

    public CardRepository(CardApi cardApi, AppDatabase appDatabase, Timer timer, SchedulerProvider schedulerProvider) {
        this.cardApi = cardApi;
        this.appDatabase = appDatabase;
        this.timer = timer;
        this.schedulerProvider = schedulerProvider;
    }

    public Observable<ItemEntity> getComputer(int id) {
        Observable<ItemEntity> itemObservable = cardApi.getComputer(id)
                .map(this::transformFromApi)
                .doOnNext(item -> appDatabase.itemDao().insert(item))
                .doOnNext(itemDbDto -> timer.updateTime(String.valueOf(itemDbDto.getId())))
                .map(itemDbDto -> itemDbDto);

        return Observable.<ItemEntity>fromCallable(() -> {
            ItemDbDto comp = appDatabase.itemDao().getId(id);

            if (comp != null) {

                if (timer.isTimeValid(String.valueOf(comp.getId()))) {
                    return comp;
                }
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

    public Observable<List<SimilarItemEntity>> getComputersSimilar(int id) { //TODO получилось только через каст
        Observable<List<SimilarItemEntity>> listObservable = cardApi.getComputersSimilar(id)
                .subscribeOn(schedulerProvider.schedulersIo())
                .map(this::transformSimilarFromApi)
                .doOnNext(similarItemDbDtos -> {
                    for (SimilarItemDbDto similarItemEntity : similarItemDbDtos) {
                        similarItemEntity.setItemId(id);
                        appDatabase.similarItemDao().insert(similarItemEntity);

                    }
                }).map(Collections::unmodifiableList);

        return Observable.<List<SimilarItemEntity>>fromCallable(() -> {
            List<SimilarItemDbDto> similarList = appDatabase.similarItemDao().getSimilarListById(id);
            if (similarList.size() != 0) {
                if (timer.isTimeValid(String.valueOf(id))) {
                    return Collections.unmodifiableList(similarList);
                }
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
