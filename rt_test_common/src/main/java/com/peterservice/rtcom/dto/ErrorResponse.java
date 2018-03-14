package com.peterservice.rtcom.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String serviceName;
    private String errorCode;
    private String userMessage;
    private String developerMessage;
    private String internalErrors;
    private String errorDetail;
}
