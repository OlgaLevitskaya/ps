package com.peterservice.rtcom.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleStatus {
    private String saleStatusId;
    private String name;
}

