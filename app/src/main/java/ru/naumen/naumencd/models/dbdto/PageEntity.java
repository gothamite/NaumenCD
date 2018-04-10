package ru.naumen.naumencd.models.dbdto;

import java.util.ArrayList;
import java.util.List;

import ru.naumen.naumencd.models.dbdto.interfaces.PageItemEntity;

public class PageEntity {

    private List<PageItemEntity> items = new ArrayList<>();

    private Integer page;

    private Integer total;

    public List<PageItemEntity> getItems() {
        return items;
    }

    public void setItems(List<PageItemEntity> items) {
        this.items = items;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void addItem(PageItemEntity dto) {
        items.add(dto);
    }
}
