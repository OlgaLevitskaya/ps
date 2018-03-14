package com.peterservice.rtcom.dto;

public enum SuccessResponse {

    /**
     * Идентификатор продажи
     */
    saleId,

    /**
     * Идентификатор экземпляра бизнес-процесса в COM (UID)
     */
    processId,

    /**
     * Идентификатор таски
     */
    taskId,

    /**
     * Лэйбл таски
     */
    taskLabel,

    /**
     * Идентификатор задачи camunda
     */
    taskFormId,

    /**
     * Номер продажи
     */
    saleNumber,

    /**
     * Дата создания продажи
     */
    saleDate,

    /**
     * Текущий этап процесса
     */
    currentStage,

    /**
     * Параметры процесса
     */
    processParams,

    /**
     * Список всех этапов процесса в формате stageId : stageLabel
     */
    allStages
}
