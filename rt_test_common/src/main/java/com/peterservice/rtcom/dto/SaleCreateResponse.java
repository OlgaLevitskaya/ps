package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Data
@Builder
public class SaleCreateResponse {

    /**
     * Идентификатор продажи
     */
    private String saleId;

    /**
     * Идентификатор экземпляра бизнес-процесса в COM (UID)
     */
    private String processId;

    /**
     * Идентификатор таски
     */
    private String taskId;

    /**
     * Лэйбл таски
     */
    private String taskLabel;

    /**
     * Идентификатор задачи camunda
     */
    private String taskFormId;

    /**
     * Номер продажи
     */
    private Long saleNumber;

    /**
     * Дата создания продажи
     */
    private LocalDateTime saleDate;

    /**
     * Текущий этап процесса
     */
    private String currentStage;

    /**
     * Параметры процесса
     */
    private Map<String, Object> processParams;

    /**
     * Список всех этапов процесса в формате stageId : stageLabel
     */
    private List<Map<String, String>> allStages;
}

