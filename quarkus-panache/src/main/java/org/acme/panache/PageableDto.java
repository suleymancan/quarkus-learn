package org.acme.panache;

import org.acme.panache.repository_pattern.Fruit;

import java.util.List;

public class PageableDto<T> {

    private final List<T> resource;

    private final Long count;

    private final Long totalPage;

    public PageableDto(List<T> resource, Long count, Long totalPage) {
        this.resource = resource;
        this.count = count;
        this.totalPage = totalPage;
    }

    public List<T> getResource() {
        return resource;
    }

    public Long getCount() {
        return count;
    }

    public Long getTotalPage() {
        return totalPage;
    }
}
