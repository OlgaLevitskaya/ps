package rtcom.get_openapi_v2_orders;

import rtcom.TestSteps.CommonObjects;
import com.peterservice.rtcom.dto.OrderList;
import com.peterservice.rtcom.tools.ObjectMapperMethods;
import com.peterservice.rtcom.tools.PrintLog;
import com.peterservice.rtcom.tools.Props;
import com.peterservice.rtcom.testdata.StatusCode;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * /openapi/v2/sales/{saleId}/orders':
 * summary: Получить список заказ
 * description: Функция возвращает список заказов
 */
@Ignore //TODO: Тест в разработке
public class GetOrdersSuccessTest {
    private static Response response;

    @BeforeClass
    public static void beforeClass() {
        //TODO: 1) надо предварительно создать продажу
        //TODO: 2) saleId получать из продажи
        //TODO: 3) создать заказ
        String saleId = "3d139009-fc6b-4b31-b774-e7a7dd798d7d";
        RequestSpecification spec = CommonObjects.getComSpec();
        spec.pathParam("saleId", saleId);
        response = spec.get(Props.getProp().saleUrl() + "/{saleId}/orders");
        PrintLog.urlRequest(spec);
    }

    @Test
    public void testGetOrdersSuccess() throws IOException {
        response.then()
                .assertThat()
                .statusCode(StatusCode.isOK());

        String respBody = response.getBody().asString();
        PrintLog.response(response);
        OrderList saleList = (OrderList) ObjectMapperMethods.getObjectMapper(respBody, OrderList.class);
    }
}
