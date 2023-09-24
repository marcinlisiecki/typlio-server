package com.example.typlioserver.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagedResponse<T> {

    List<T> content;
    Integer currentPage;
    Long totalItems;
    Integer totalPages;
}
