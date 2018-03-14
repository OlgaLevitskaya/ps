package rtcom.get_openapi_v2_sales;

import rtcom.TestSteps.StepsSale;
import com.peterservice.rtcom.dto.SaleList;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

/**
 * GET /openapi/v2/sales  - Получить список продаж
 */
public class GetSalesSuccessTest {
    private static final Integer LIMIT_DEFAULT = 20;
    private static final Integer OFFSET_DEFAULT = 0;
    private static Response resGetSales;

    /**
     * Проверка успешного получения продаж + дефолтных значений в ListInfo
     */
    @Test
    public void testGetSalesSuccess() {
        resGetSales = StepsSale.doGetSales();
        StepsSale.checkSuccess(resGetSales);
        SaleList saleList = StepsSale.getSaleList(resGetSales);
        Assert.assertEquals("Wrong limit default", LIMIT_DEFAULT, saleList.getListInfo().getLimit());
        Assert.assertEquals("Wrong offset default", OFFSET_DEFAULT, saleList.getListInfo().getOffset());
    }
}
