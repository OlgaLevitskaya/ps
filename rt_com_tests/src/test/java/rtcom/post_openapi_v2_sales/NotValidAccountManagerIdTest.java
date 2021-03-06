package rtcom.post_openapi_v2_sales;

import rtcom.TestSteps.CommonObjects;
import rtcom.TestSteps.StepsSale;
import com.peterservice.rtcom.dto.ManagerBaseInfo;
import com.peterservice.rtcom.dto.SaleCreateParams;
import com.peterservice.rtcom.testdata.StatusCode;
import com.peterservice.rtcom.testdata.TestConstans;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static com.peterservice.rtcom.testdata.StatusCode.PARAMETER_ERROR_1;


/**
 * Не валидный accountManager.cmsOperatorId
 * Ожидаемый ответ:
 * 400 Bad Request - Неверно заданы входные параметры
 */

@RunWith(Parameterized.class)
public class NotValidAccountManagerIdTest {
    private static final String cmsOperatorId = "accountManager.cmsOperatorId";
    private String value;
    private String userMessage;

    public NotValidAccountManagerIdTest(final String value, final String userMessage) {
        this.value = value;
        this.userMessage = userMessage;
    }

    @Parameterized.Parameters(name = "{0} = {1}")
    public static List<Object[]> Params() {
        return Arrays.asList(new Object[][]{
                {"-1", cmsOperatorId + PARAMETER_ERROR_1},
                {"0", cmsOperatorId + PARAMETER_ERROR_1},
                {String.valueOf(TestConstans.MAX_VALUE_ID + 1), cmsOperatorId + StatusCode.PARAMETER_ERROR_2},
        });
    }

    @Test
    public void testNotValidAccountManagerId() throws JSONException {
        ManagerBaseInfo accountManagerInfo = ManagerBaseInfo.builder().cmsOperatorId(Long.valueOf(value)).build();
        SaleCreateParams saleCreateParams = CommonObjects.createParamsSaleBase()
                .accountManager(accountManagerInfo)
                .build();
        StepsSale.checkBadRequest(saleCreateParams, CommonObjects.getErrorResponse(StatusCode.INVALID_PARAMETERS, userMessage));
    }
}
