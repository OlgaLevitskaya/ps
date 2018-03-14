package com.peterservice.rtcom.dto;


import lombok.Data;


/**
 * Пол
 */
@Data
public class Gender {

    /**
     * id пола
     */
    private Integer genderId;

    /**
     * Наименование пола
     */
    private String name;
}

