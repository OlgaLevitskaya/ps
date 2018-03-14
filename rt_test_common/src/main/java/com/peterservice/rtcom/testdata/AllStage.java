package com.peterservice.rtcom.testdata;

import java.util.EnumMap;

/**
 * Этапы создания продажи
 */
public class AllStage {
    private static final EnumMap<STAGE_ID, String> allStageMap = createAllStageMap();

    private static EnumMap<STAGE_ID, String> createAllStageMap() {
        EnumMap<STAGE_ID, String> mMap = new EnumMap<>(STAGE_ID.class);
        mMap.put(STAGE_ID.create_contact_task, "Создать контакт с клиентом");
        mMap.put(STAGE_ID.complete_contact_task, "Выполнить контакт");
        mMap.put(STAGE_ID.contact, "Уточнение потребностей клиента");
        mMap.put(STAGE_ID.orders, "Создание заказов");
        mMap.put(STAGE_ID.kp, "Создание КП");
        mMap.put(STAGE_ID.dogovor, "Подготовка договора");
        mMap.put(STAGE_ID.connect, "Подключение услуги");
        return mMap;
    }

    public static String getStage(STAGE_ID id) {
        return allStageMap.get(id);
    }

    public enum STAGE_ID {
        create_contact_task, complete_contact_task, contact, orders, kp, dogovor, connect
    }
}
