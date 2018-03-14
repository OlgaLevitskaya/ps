package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Параметры продажи.
 *
 * @see <a href="https://confluence.billing.ru/pages/viewpage.action?pageId=107253896">Параметры продажи</a>
 */
@Data
@Builder
public class SaleCreateParams {

    public enum InitialStep {DEFAULT, COMPLETE_CONTACT}

    /**
     * id клиента.
     */
    private Long sfaCustomerId;


    private InitialStep initialStep;

    /**
     * Маркеры продажи.
     */
    private List<SaleMarker> saleMarkers;

    /**
     * Контактные лица продажи.
     */
    private List<ContactPersonBaseInfo> contactPersons;

    /**
     * Ответственный сотрудника
     */

    private ManagerBaseInfo accountManager;
    /**
     * Ответственный сотрудник
     */
    private ManagerBaseInfo serviceManager;

    /**
     * Process id of Camunda.
     */
    private String processId;

    /**
     * Комментарий.
     */
    private String comment;

    /**
     * Инсталляционный платёж.
     */
    private BigDecimal installationPayment;

    /**
     * Ежемесячный платёж.
     */
    private BigDecimal monthlyPayment;

    /**
     * Планируемая дата оказания услуги.
     */
    private LocalDateTime expectedEndDate;

    /**
     * Планируемый срок сделки (месяцы).
     */
    private Integer plannedPeriod;
}

