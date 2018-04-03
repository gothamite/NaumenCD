package ru.naumen.naumencd.repositories;

import io.reactivex.Observable;
import ru.naumen.naumencd.app.ListApi;
import ru.naumen.naumencd.models.dbdto.CompanyDbDto;
import ru.naumen.naumencd.models.dbdto.ItemDbDto;
import ru.naumen.naumencd.models.dbdto.PageDbDto;
import ru.naumen.naumencd.models.dbdto.PageEntity;
import ru.naumen.naumencd.models.dbdto.PageItemDbDto;
import ru.naumen.naumencd.models.dbdto.interfaces.PageItemEntity;
import ru.naumen.naumencd.models.dto.Computers;
import ru.naumen.naumencd.models.dto.Item;
import ru.naumen.naumencd.room.AppDatabase;

public class ListRepository {

    private AppDatabase appDatabase;
    private ListApi listApi;

    public ListRepository(ListApi listApi, AppDatabase appDatabase) {
        this.listApi = listApi;
        this.appDatabase = appDatabase;
    }

    public Observable<PageEntity> getComputers(int page) {
        Observable<PageEntity> getComputersFromApi = listApi.getComputers(page)
                .map(this::transformFromApi)
                .doOnNext(this::saveToDatabase);


        return Observable.fromCallable(() -> {
            PageDbDto pageItem = appDatabase.pageDao().getPage(page);

            if (pageItem != null) {
                return pageItem;
            }
            throw new IllegalStateException("CompsList not found");
        }).map(pageDbDto -> transformFromDb(page, pageDbDto))
                .onErrorResumeNext(getComputersFromApi);
    }

    private void saveToDatabase(PageEntity pageEntity) {
        PageDbDto pageDbDto = new PageDbDto();
        pageDbDto.setPage(pageEntity.getPage());
        pageDbDto.setTotal(pageEntity.getTotal());

        appDatabase.pageDao().insert(pageDbDto);

        for (int i = 0; i < pageEntity.getItems().size(); i++) {
            PageItemDbDto pageItemDbDto = new PageItemDbDto();
            PageItemEntity item = pageEntity.getItems().get(i);

            pageItemDbDto.setPageId(pageDbDto.getPage());
            pageItemDbDto.setName(item.getName());
            pageItemDbDto.setId(item.getId());

            if (item.getCompanyName() != null) {
                pageItemDbDto.setCompanyName(item.getCompanyName());
            }
            appDatabase.pageItemDao().insert(pageItemDbDto);
        }
    }

    private PageEntity transformFromApi(Computers computers) {

        PageEntity pageEntity = new PageEntity();
        for (Item item : computers.getItems()) {
            ItemDbDto itemDbDto = new ItemDbDto();
            CompanyDbDto companyDbDto = new CompanyDbDto();

            if (item.getCompany() != null) {
                companyDbDto.setId(item.getCompany().getId());
                companyDbDto.setName(item.getCompany().getName());
            }
            itemDbDto.setId(item.getId());
            itemDbDto.setCompany(companyDbDto);
            itemDbDto.setName(item.getName());

            pageEntity.addItem(itemDbDto);
        }
        pageEntity.setPage(computers.getPage());
        pageEntity.setTotal(computers.getTotal());
        return pageEntity;
    }

    private PageEntity transformFromDb(int page, PageDbDto pageDbDto) {

        PageEntity pageEntity = new PageEntity();
        for (PageItemDbDto dto : appDatabase.pageItemDao().getPageItem(pageDbDto.getPage())) {
            pageEntity.addItem(dto);
        }
        pageEntity.setPage(page);
        pageEntity.setTotal(pageDbDto.getTotal());
        return pageEntity;
    }
}

