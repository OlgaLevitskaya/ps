package com.peterservice.rtcom.dto;

public enum SaleParams {
    /**
     * id клиента
     */
    sfaCustomerId,
    /**
     * ключ (id) этапа продажи
     */
    saleStepId,

    /**
     * Id оператора CMS (CPM)
     */
    managerId, //TODO: https://jira.billing.ru/browse/RTSFA-76?filter=60041 Должен замениться на cmsOperatorId

    /**
     * Маркер продажи
     */
    saleMarkerId,

    /**
     * Id оператора CMS (CPM)
     */
    cmsOperatorId,

    /**
     * Маркеры продажи
     */
    saleMarkers,

    /**
     * Контактные лица продажи
     */
    contactPersons,

    accountManager,

    serviceManager,

    /**
     * Process id of Camunda.
     */
    processId,

    /**
     * Комментарий
     */
    comment,

    contactPersonId

}
