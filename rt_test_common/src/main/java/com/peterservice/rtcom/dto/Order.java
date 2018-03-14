package com.peterservice.rtcom.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Order {

    /**
     * Идентификатор
     */
    private Long orderId;

    /**
     * Этап
     */
    private SaleStageInfo stage;

    /**
     * Ответственный менеджер
     */
    private ManagerBaseInfo manager;

    /**
     * Дата создания заказа
     */
    private LocalDateTime createDate;

    /**
     * Тип услуги
     */
    private OrderType type;

    /**
     * Количество услуг
     */
    private Integer productCount;

    /**
     * Потенциал
     */
    private BigDecimal oneTimePotential;

    /**
     * Потенциал
     */
    private BigDecimal curringPotential;

    /**
     * Адрес подключения
     */
    private Address address;
}
