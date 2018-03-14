package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;


/**
 * Базовая информация по ответственному лицу
 */
@Data
@Builder
public class ManagerBaseInfo {

    /**
     * id ответственного лица
     *
     * @deprecated Не соответствует спецификации OAPI. Выпилить, когда фронтенд полностью передет на cmsOperatorId
     */
    @Deprecated
    private Long managerId;

    /**
     * Id оператора CMS (CPM)
     */
    private Long cmsOperatorId;

    /**
     * Имя ответственного лица
     */
    private String name;

    /**
     * Полное имя ответственного лица
     */
    private String fullName;
}
