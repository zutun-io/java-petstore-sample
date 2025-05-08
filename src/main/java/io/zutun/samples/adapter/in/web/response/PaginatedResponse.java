package io.zutun.samples.adapter.in.web.response;

import java.util.List;

import lombok.Data;

@Data
public class PaginatedResponse<T> {
    private List<T> content;
    private int page;
    private int pagesize;
    private long totalElements;
    private int totalPages;

    public PaginatedResponse(List<T> content, int page, int pagesize, long totalElements, int totalPages) {
        this.content = content;
        this.page = page;
        this.pagesize = pagesize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
