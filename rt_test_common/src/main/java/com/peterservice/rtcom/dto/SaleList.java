package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * Список продаж
 */
@Data
@Builder
public class SaleList {

    private ListInfo listInfo;

    /**
     * Объекты продаж
     */
    private List<Sale> items;
}

