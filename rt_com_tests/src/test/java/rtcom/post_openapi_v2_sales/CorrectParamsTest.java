package rtcom.post_openapi_v2_sales;

import rtcom.TestSteps.CommonObjects;
import com.peterservice.rtcom.testdata.StatusCode;
import com.peterservice.rtcom.testdata.TestConstans;
import com.peterservice.rtcom.tools.JsonMethods;
import com.peterservice.rtcom.tools.Props;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.peterservice.rtcom.dto.SaleParams.*;
import static java.lang.Math.abs;

/**
 * Проверка валидных параметров
 * Ожидаемый ответ: 200 Success
 * TODO Надо доработать
 */


@RunWith(Parameterized.class)
public class CorrectParamsTest {
    private static final Random RANDOM = new Random();
    private static final JsonMethods JM = JsonMethods.inst();
    private String param;
    private String value;

    public CorrectParamsTest(final String param, final String value) {
        this.param = param;
        this.value = value;
    }

    @Parameterized.Parameters(name = "{0} = {1}")
    public static List<Object[]> Params() {
        return Arrays.asList(new Object[][]{
                /* sfaCustomerId */
                {sfaCustomerId.name(), String.valueOf(abs(RANDOM.nextInt()))},
                {sfaCustomerId.name(), String.valueOf(TestConstans.MAX_VALUE_ID - 1)},
                {sfaCustomerId.name(), String.valueOf(TestConstans.MAX_VALUE_ID)},
                {sfaCustomerId.name(), String.valueOf(abs(RANDOM.nextInt()))},

                {managerId.name(), String.valueOf(abs(RANDOM.nextInt()))},
                {managerId.name(), String.valueOf(TestConstans.MAX_VALUE_ID - 1)},
                {managerId.name(), String.valueOf(TestConstans.MAX_VALUE_ID)},
                {managerId.name(), String.valueOf(abs(RANDOM.nextInt()))},
                /*comment - 512 символов*/
                {comment.name(), TestConstans.MAX_VALUE_TEXT},
        });
    }

    @Test
    public void testCorrectParams() throws IOException, ParseException {
        String json = JM.getJsonFromFile("sales.json");
        CommonObjects.getComSpec().
                body(JM.putJsonElement(json, param, value))
                .post(Props.getProp().saleUrl())
                .then()
                .assertThat()
                .statusCode(StatusCode.isOK());
    }
}
