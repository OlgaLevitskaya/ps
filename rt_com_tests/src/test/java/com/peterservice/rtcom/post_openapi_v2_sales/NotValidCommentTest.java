package com.peterservice.rtcom.post_openapi_v2_sales;

import com.peterservice.rtcom.TestSteps.CommonObjects;
import com.peterservice.rtcom.TestSteps.StepsSale;
import com.peterservice.rtcom.dto.SaleCreateParams;
import com.peterservice.rtcom.dto.SaleParams;
import com.peterservice.rtcom.testdata.StatusCode;
import com.peterservice.rtcom.testdata.TestConstans;
import org.json.JSONException;
import org.junit.Test;

import static com.peterservice.rtcom.testdata.StatusCode.PARAMETER_ERROR_3;


/**
 * Не валидный comment
 * Ожидаемый ответ:
 * 400 Bad Request - Неверно заданы входные параметры
 */
public class NotValidCommentTest {
    private static final String comment = SaleParams.comment.name();
    private static final String value = TestConstans.MAX_VALUE_TEXT + "a";
    private static final String userMessage = comment + PARAMETER_ERROR_3;

    @Test
    public void testNotValidComment() throws JSONException {
        SaleCreateParams saleCreateParams = CommonObjects.createParamsSaleBase()
                .comment(value)
                .build();
        StepsSale.checkBadRequest(saleCreateParams, CommonObjects.getErrorResponse(StatusCode.INVALID_PARAMETERS, userMessage));
    }
}
