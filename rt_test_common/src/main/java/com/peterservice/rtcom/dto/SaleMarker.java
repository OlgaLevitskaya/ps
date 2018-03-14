package com.peterservice.rtcom.dto;

import lombok.Data;

/**
 * Маркер продажи
 */
@Data
public class SaleMarker {

    /**
     * id маркера продажи
     */
    private Long saleMarkerId;

    /**
     * Наименование маркера продажи
     */
    private String name;
}

