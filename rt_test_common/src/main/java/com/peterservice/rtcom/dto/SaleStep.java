package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * Этап продажи
 */
@Data
@Builder
public class SaleStep {

    /**
     * ключ (id) этапа продажи
     */
    private String saleStepId;

    /**
     * Наименование этапа продажи
     */
    private String name;

    /**
     * Комментарий
     */
    private String commnet;

    /**
     * Инсталляционный платёж
     */
    private BigDecimal installationPayment;

    /**
     * Ежемесячный платёж
     */
    private BigDecimal monthlyPayment;

    /**
     * Дата начала этапа
     */
    private LocalDateTime startDate;

    /**
     * Плановая дата завершения этапа
     */
    private LocalDateTime expectedEndDate;

    /**
     * Дата окончания этапа
     */
    private LocalDateTime endDate;

    /**
     * Стадия продажи
     */
    private StageInfo saleStage;

    /**
     * Все стадии продажи
     */
    private List<StageInfo> allStages;

    /**
     * Параметры
     */
    private Map<String, String> processParameters;
}

