package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;


/**
 * Базовая информация по клиенту
 */
@Data
@Builder
public class SfaCustomerBaseInfo {

    /**
     * id клиента
     * @deprecated Не соответствует спецификации OAPI. Выпилить, когда фронтенд полностью передет на sfaCustomerId
     */
    @Deprecated
    private Long customerId;

    /**
     * id клиента
     */
    private Long sfaCustomerId;

    /**
     * Полное имя клиента
     */
    private String name;
}

