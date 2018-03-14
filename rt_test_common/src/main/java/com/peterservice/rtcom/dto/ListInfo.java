package com.peterservice.rtcom.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListInfo {
/**
 *  integer
 *  Количество возвращаемых записей. Используется для постраничного вывода. Подробнее в разделе «Постраничный вывод»
 *  (https://confluence.billing.ru/pages/viewpage.action?pageId=15073821).
 *  Default value: 20
 */
    private Integer limit;
    /**
     * Задает правило определения номера записи, в наборе отфильтрованных и отсортированных записей, с которой
     * начинается вывод. Используется для постраничного вывода. Подробнее в разделе
     * «Постраничный вывод»(https://confluence.billing.ru/pages/viewpage.action?pageId=15073821).
     * Default value: 0
     */
    private  Integer offset;
    /**
     * Количество записей
     */
    private Integer count;
}
