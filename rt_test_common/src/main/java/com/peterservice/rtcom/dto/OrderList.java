package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * Список заказов
 */
@Data
@Builder
public class OrderList {

    private ListInfo listInfo;

    /**
     * Объекты заказа
     */
    private List<Order> items;
}
