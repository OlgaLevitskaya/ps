package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Объект продажи
 */
@Data
@Builder
public class Sale {

    /**
     * Идентификатор продажи
     */
    private String saleId;

    /**
     * Идентификатор экземпляра бизнес-процесса в COM (UID)
     */
    private String processId;

    /**
     * Номер продажи
     */
    private String saleNumber;

    /**
     * Дата создания продажи
     */
    private LocalDateTime createDate;

    /**
     * Предпологаемая дата закрытия продажи
     */
    private LocalDateTime closeDate;

    /**
     * Базовая информация по клиенту
     */
    private SfaCustomerBaseInfo sfaCustomer;
    /**
     * Этап продажи
     */
    private SaleStep step;

    /**
     * Маркеры продажи
     */
    private List<SaleMarker> saleMarkers;

    /**
     * Контактные лица продажи
     */
    private List<ContactPersonBaseInfo> contactPersons;

    /**
     * Ответственный сотрудника (account manger)
     */
    private ManagerBaseInfo accountManager;

    /**
     * Ответственный сотрудник (service manger)
     */
    private ManagerBaseInfo serviceManager;

    /**
     * Статуст продажи
     */
    private SaleStatus status;

    /**
     * Комментарий
     */
    private String comment;

    /**
     * МРФ продажи
     */
    private String filial;

    /**
     * РФ продажи
     */
    private String region;

    /**
     * Документ
     */
    private String document;
}

