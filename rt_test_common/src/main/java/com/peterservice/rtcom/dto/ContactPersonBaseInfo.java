package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;


/**
 * Контактное лицо
 */
@Data
@Builder
public class ContactPersonBaseInfo {

    /**
     * id контактного лица
     */
    private Long contactPersonId;

    /**
     * Полное имя контактного лица
     */
    private String fullName;


    private Gender gender;
}

