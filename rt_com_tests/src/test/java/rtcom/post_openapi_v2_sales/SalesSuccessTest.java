package rtcom.post_openapi_v2_sales;

import com.fasterxml.jackson.databind.JsonNode;
import rtcom.TestSteps.CommonObjects;
import rtcom.TestSteps.StepsSale;
import com.peterservice.rtcom.dto.SaleCreateParams;
import com.peterservice.rtcom.dto.SuccessResponse;
import com.peterservice.rtcom.testdata.AllStage;
import com.peterservice.rtcom.tools.ObjectMapperMethods;
import com.peterservice.rtcom.tools.PrintLog;
import com.peterservice.rtcom.tools.Props;
import com.peterservice.rtcom.tools.ValidateMethods;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static com.peterservice.rtcom.dto.SuccessResponse.*;

/**
 * Создание новой продажи с шага "Создание задачи на контакт"
 * Со всеми заполненными значениями в форме
 * <p>
 * Проверка к базе
 * rtsfa_sales
 * dbc:oracle:thin:@//srv3-amain-o.net.billing.ru:1521/sfadb.net.billing.ru
 * SFA/SFA
 * Пример json из формы браузера:
 * {"sfaCustomerId":"1750","accountManager":{"managerId":"321"},"serviceManager":{"managerId":"321"},
 * "contactPersons":[{"contactPersonId":"2201"}],"installationPayment":"1000","initialStep":"COMPLETE_CONTACT",
 * "comment":"qwerty","monthlyPayment":"200","revenueAmount":"100","plannedPeriod":"12"}
 * //TODO НЕОБХОДИМО ПРОВЕРЯТЬ ДЛЯ COM и SFA
 */

public class SalesSuccessTest {
    private static Response response;

    @BeforeClass
    public static void beforeClass() {
        BigDecimal installationPayment = BigDecimal.valueOf(1000); //Инсталляционный платеж
        BigDecimal monthlyPayment = BigDecimal.valueOf(200); //Ежемесячный платеж
        int plannedPeriod = 11;//Планируемый срок сделки
        String comment = "Test test"; //комментарий

        SaleCreateParams saleCreateParams = CommonObjects.createParamsSaleBase()
                .comment(comment) //комментарий
                .initialStep(SaleCreateParams.InitialStep.DEFAULT) //Начать продажу с этапа
                .installationPayment(installationPayment)
                .monthlyPayment(monthlyPayment) //Ежемесячный платеж
                .plannedPeriod(plannedPeriod) //Планируемый срок сделки
                //TODO: expectedEndDate https://jira.billing.ru/browse/RTSFA-165
                //TODO: revenueAmount https://jira.billing.ru/browse/RTSFA-163
                .build();
        JsonNode jsonSale = ObjectMapperMethods.MAPPER.valueToTree(saleCreateParams);

        /** Создание продажи **/
        RequestSpecification spec = CommonObjects.getComSpec().body(jsonSale);
        response = spec.post(Props.getProp().saleUrl());

        PrintLog.urlRequest(spec);
        PrintLog.info("BODY: ", jsonSale);
        PrintLog.response(response);
    }

    /**
     * Успешная отправка запроса на продажу
     * Проверка: всех параметров ответа
     */
    @Test
    public void testSuccessResponse() throws IOException {
        StepsSale.checkSuccess(response);
        //TODO: После фикса доработать https://jira.billing.ru/browse/RTSFA-168
        JsonNode actualObj = ObjectMapperMethods.MAPPER.readTree(response.getBody().asString());

        ValidateMethods.assertTrueValidate(saleId.name(), getValue(actualObj, SuccessResponse.saleId), ValidateMethods.eType.UID);
        ValidateMethods.assertTrueValidate(processId.name(), getValue(actualObj, processId), ValidateMethods.eType.UID);
        ValidateMethods.assertTrueValidate(taskId.name(), getValue(actualObj, taskId), ValidateMethods.eType.UID);

        Assert.assertEquals("Wrong value " + SuccessResponse.taskFormId.name(),
                AllStage.STAGE_ID.create_contact_task.name(), getValue(actualObj, SuccessResponse.taskFormId));
        Assert.assertEquals("Wrong value " + SuccessResponse.taskLabel.name(),
                getStageLabel(), getValue(actualObj, SuccessResponse.taskLabel));

        ValidateMethods.assertTrueValidate(saleNumber.name(), getValue(actualObj, saleNumber), ValidateMethods.eType.INTEGER);
        ValidateMethods.assertTrueValidate(saleDate.name(), getValue(actualObj, saleDate), ValidateMethods.eType.DATE_ISO_8601_FULL);

        Assert.assertEquals("Wrong value " + AllStage.STAGE_ID.contact, getValue(actualObj, currentStage), AllStage.STAGE_ID.contact.name());
        Assert.assertNotNull("allStages is null", actualObj.get(allStages.name()));
        Assert.assertNotNull("processParams is null", actualObj.get(processParams.name()));
    }

    private String getStageLabel() {
        return AllStage.getStage(AllStage.STAGE_ID.create_contact_task);
    }

    /**
     * Получение параметров (только строковых)
     *
     * @param actualObj
     * @param parameter
     * @return
     */
    private String getValue(JsonNode actualObj, SuccessResponse parameter) {
        return actualObj.get(parameter.name()).textValue();
    }
}
