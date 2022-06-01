package com.gitmuts.dzemo.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListResponse {

    private List<?> data;
    private int totalPages;
    private int totalElements;
}