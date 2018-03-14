package com.peterservice.rtcom.TestSteps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterservice.rtcom.dto.ErrorResponse;
import com.peterservice.rtcom.dto.SaleCreateParams;
import com.peterservice.rtcom.dto.SaleList;
import com.peterservice.rtcom.testdata.StatusCode;
import com.peterservice.rtcom.tools.ObjectMapperMethods;
import com.peterservice.rtcom.tools.PrintLog;
import com.peterservice.rtcom.tools.Props;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.util.Objects;

/**
 * Шаги для тестов "Продажи"
 */
public class StepsSale {
    private static final RequestSpecification requestSpecification = CommonObjects.getComSpec();

    /**
     * @param saleCreateParams - параметры запроса
     * @param errorResponse    - - ожидаемая ошибка ответа
     * @throws JSONException
     */
    public static void checkBadRequest(SaleCreateParams saleCreateParams, ErrorResponse errorResponse) throws JSONException {
        checkBadRequest(ObjectMapperMethods.MAPPER.valueToTree(saleCreateParams).toString(), errorResponse);
    }
    /**
     * @param jsonRequest - джейсон запроса
     * @param errorResponse - ожидаемая ошибка ответа
     * @throws JSONException
     */
    public static void checkBadRequest(String jsonRequest, ErrorResponse errorResponse) throws JSONException {
        Objects.requireNonNull(jsonRequest);
        Objects.requireNonNull(errorResponse);
        ObjectMapper mErrorR = new ObjectMapper();
        /*Отправка запроса*/
        RequestSpecification spec = CommonObjects.getComSpec()
                .body(jsonRequest);
        Response resp = spec.post(Props.getProp().saleUrl());
        PrintLog.urlRequest(spec);
        PrintLog.info("REQUEST JSON: ", jsonRequest);

        resp.then()
                .assertThat()
                .statusCode(StatusCode.isBadRequest());

        /*Проверка Response*/
        String expectError = null;
        String actualResp = null;
        try {
            //TODO: Костыль чтобы не проверять developerMessage
            ErrorResponse er = mErrorR.readValue(resp.getBody().asString(), ErrorResponse.class);
            er.setDeveloperMessage(null);

            expectError = mErrorR.writeValueAsString(errorResponse);
            actualResp = mErrorR.writeValueAsString(er);
        } catch (IOException e) {
            PrintLog.error(e);
        }
        PrintLog.info("Actual response: ", actualResp);
        PrintLog.info("Expected response:", expectError);
        JSONAssert.assertEquals(expectError, actualResp, false);
    }

    /**
     * POST /openapi/v2/sales - создание продажи
     * Заполнены только обязательные поля
     */
    public static Response doSaleBase() {
        JsonNode jsonSale = ObjectMapperMethods.MAPPER.valueToTree(CommonObjects.createParamsSaleBase().build());
        return requestSpecification
                .body(jsonSale).post(Props.getProp().saleUrl());
    }

    /**
     * GET /openapi/v2/sales  - Получить список продаж
     *
     * @param specification - дополнение к спецификации
     * @return Response
     */
    public static Response doGetSales(RequestSpecification specification) {
        if (specification != null) {
            specification.spec(specification);
        }
        return specification.get(Props.getProp().saleUrl());
    }

    /**
     * GET /openapi/v2/sales  - Получить список продаж
     * @return Response
     */
    public static Response doGetSales() {
        return doGetSales(requestSpecification);
    }

    /**
     * Проверка на успешный ответ
     *
     * @param response
     */
    public static void checkSuccess(Response response) {
        Objects.requireNonNull(response, "response = null");
        response.then()
                .assertThat()
                .statusCode(StatusCode.isOK());
    }

    /**
     * Получение SaleList
     *
     * @param response
     * @return
     */
    public static SaleList getSaleList(Response response) {
        Objects.requireNonNull(response, "response is null");
        SaleList saleList = null;
        try {
            saleList = (SaleList) ObjectMapperMethods.getObjectMapper(response.getBody().asString(), SaleList.class);
        } catch (IOException e) {
            Assert.fail("Can't get SaleList from " + response.getBody().asString() + "\n" +
                    e.getLocalizedMessage());
        }
        return saleList;
    }
}
