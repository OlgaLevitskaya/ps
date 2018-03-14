package com.peterservice.rtcom.post_openapi_v2_sales;

import com.peterservice.rtcom.TestSteps.CommonObjects;
import com.peterservice.rtcom.TestSteps.StepsSale;
import com.peterservice.rtcom.dto.ContactPersonBaseInfo;
import com.peterservice.rtcom.dto.ErrorResponse;
import com.peterservice.rtcom.dto.ManagerBaseInfo;
import com.peterservice.rtcom.dto.SaleCreateParams;
import com.peterservice.rtcom.tools.Props;
import org.json.JSONException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.peterservice.rtcom.testdata.StatusCode.INVALID_PARAMETERS;


/**
 * Проверка обязательных параметров на отсутствие
 * Ожидаемый ответ: 400 Bad Request - Неверно заданы входные параметры
 */
public class ParametersIsNullTest {
    private String userMessage;

    /**
     * sfaCustomerId = null
     * (id клиента)
     *
     * @throws JSONException
     */
    @Test
    public void sfaCustomerIdIsNull() throws JSONException {
        userMessage = "{sfaCustomerId=may not be null}";
        SaleCreateParams saleCreateParams = CommonObjects.createParamsSaleBase()
                .sfaCustomerId(null)
                .build();
        StepsSale.checkBadRequest(saleCreateParams, getErrorResponse(userMessage));
    }

    /**
     * accountManager.managerId = null
     * (Id оператора CMS (CPM))
     *
     * @throws JSONException
     */
    @Ignore("https://jira.billing.ru/browse/RTSFA-248")
    @Test
    public void accountManagerCmsOperatorIdIsNull() throws JSONException {
        userMessage = "{accountManager.cmsOperatorId=may not be null}";
        SaleCreateParams saleCreateParams = CommonObjects.createParamsSaleBase()
                .accountManager(ManagerBaseInfo.builder().cmsOperatorId(null).build())
                .build();
        StepsSale.checkBadRequest(saleCreateParams, getErrorResponse(userMessage));
    }

    /**
     * serviceManager.managerId = null
     * (Id оператора CMS (CPM))
     *
     * @throws JSONException
     */
    @Ignore("https://jira.billing.ru/browse/RTSFA-248")
    @Test
    public void serviceManagerCmsOperatorIdIsNull() throws JSONException {
        userMessage = "{serviceManager.cmsOperatorId=may not be null}";
        SaleCreateParams saleCreateParams = CommonObjects.createParamsSaleBase()
                .serviceManager(ManagerBaseInfo.builder().cmsOperatorId(null).build())
                .build();
        StepsSale.checkBadRequest(saleCreateParams, getErrorResponse(userMessage));
    }

    /**
     * contactPersonId = null
     * (id контактного лица)
     *
     * @throws JSONException
     */
    @Ignore("https://jira.billing.ru/browse/RTSFA-172")
    @Test
    public void contactPersonIdIsNull() throws JSONException {
        userMessage = "{contactPersons.contactPersonId=may not be null}";
        ContactPersonBaseInfo contactPersons = ContactPersonBaseInfo.builder().contactPersonId(null).build();
        List<ContactPersonBaseInfo> listContactPersons = new ArrayList<>();
        listContactPersons.add(contactPersons);
        SaleCreateParams saleCreateParams = CommonObjects.createParamsSaleBase()
                .contactPersons(listContactPersons)
                .build();
        StepsSale.checkBadRequest(saleCreateParams, getErrorResponse(userMessage));
    }


    private ErrorResponse getErrorResponse(String userMessage) {
        return ErrorResponse.builder()
                .serviceName(Props.getProp().productName())
                .errorCode(INVALID_PARAMETERS)
                .userMessage(userMessage)
                .build();
    }
}
