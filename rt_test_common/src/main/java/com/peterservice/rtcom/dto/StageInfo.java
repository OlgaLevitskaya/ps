package com.peterservice.rtcom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Описание стадии продажи
 */
@Data
@Builder
public class StageInfo {

    /**
     * Идентификатор camunda
     */
    private String stageId;
    /**
     * Название
     */
    private String label;
}
