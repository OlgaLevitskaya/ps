package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;


/**
 * Адрес подключения
 */
@Data
@Builder
public class Address {

    private Integer addressId;

    private String addressAsString;
}
