package com.peterservice.rtcom.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleStageInfo {

    private String stageId;

    private String name;
}
