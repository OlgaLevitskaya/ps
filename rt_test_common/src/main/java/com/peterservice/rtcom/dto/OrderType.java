package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;


/**
 * Тип услуга
 */
@Data
@Builder
public class OrderType {

    private Integer typeId;

    private String name;
}
