package rtcom.TestSteps;

import com.peterservice.rtcom.dto.*;
import com.peterservice.rtcom.testdata.TestConstans;
import com.peterservice.rtcom.tools.Props;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class CommonObjects {
    /**
     * Получение объекта ListInfo
     *
     * @param limit
     * @param offset
     * @return возвращает объект ListInfo
     */
    public static ListInfo buildListInfo(int limit, int offset) {
        return ListInfo.builder()
                .limit(limit)
                .offset(offset)
                .build();
    }

    /**
     * Получение базовой спецификации для отправки запросов в COM
     */
    public static RequestSpecification getComSpec() {
        return RestAssured
                .given()
                .baseUri(Props.getProp().server())
                .port(Props.getProp().port())
                .contentType("application/json;charset=UTF-8")
                .accept("application/json;charset=UTF-8");
    }

    /**
     * Получение ошибки
     *
     * @param errorCode
     * @param userMessage
     * @return
     */
    public static ErrorResponse getErrorResponse(String errorCode, String userMessage) {
        return ErrorResponse.builder()
                .serviceName(Props.getProp().productName())
                .errorCode(errorCode)
                .userMessage("{" + userMessage + "}")
                .build();
    }


    /**
     * Базовый набор обязательных параметров для создания продажи
     *
     * @return SaleCreateParams.SaleCreateParamsBuilder
     */
    public static SaleCreateParams.SaleCreateParamsBuilder createParamsSaleBase() {
        /** Создание параметров продажи **/
        ManagerBaseInfo accountManagerInfo = ManagerBaseInfo.builder().cmsOperatorId(TestConstans.ACCOUNT_MANAGER).build();
        ManagerBaseInfo serviceManagerInfo = ManagerBaseInfo.builder().cmsOperatorId(TestConstans.SERVICE_MANAGER).build();
        ContactPersonBaseInfo contactPersons = ContactPersonBaseInfo.builder().contactPersonId(TestConstans.CONTACT_PERSON_ID).build();
        List<ContactPersonBaseInfo> listContactPersons = new ArrayList<>();
        listContactPersons.add(contactPersons);
        return SaleCreateParams.builder()
                .sfaCustomerId(TestConstans.SFA_CUSTOMER_ID)
                .accountManager(accountManagerInfo)
                .serviceManager(serviceManagerInfo)
                .contactPersons(listContactPersons);
    }
}
