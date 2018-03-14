package com.peterservice.rtcom.post_openapi_v2_sales;

import com.peterservice.rtcom.TestSteps.StepsSale;
import com.peterservice.rtcom.dto.ErrorResponse;
import com.peterservice.rtcom.testdata.StatusCode;
import com.peterservice.rtcom.tools.Props;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ServerErrorTest {
    private String json;
    //TODO: проверять когда будет нормальнй текст
    private String developerMessage;

    public ServerErrorTest(final String json, final String developerMessage) {
        this.json = json;
        this.developerMessage = developerMessage;
    }

    @Parameterized.Parameters
    public static List<Object[]> Params() {
        return Arrays.asList(new Object[][]{
                {"", null},
                {"[]", null},
                {"     ", null},
                {".", null},

        });
    }

    /**
     * Отправка невалидного json
     * Ожидаемый ответ: 500 Internal Serever Error
     */
    @Test
    public void testNotValidJson() throws JSONException {
        ErrorResponse respError = ErrorResponse.builder()
                .serviceName(Props.getProp().productName())
                .developerMessage(developerMessage)
                .errorCode(StatusCode.INVALID_PARAMETERS)
                .userMessage(StatusCode.NOT_READABLE_HTTP_MESSAGE)
                .build();
        StepsSale.checkBadRequest(json, respError);
    }
}
