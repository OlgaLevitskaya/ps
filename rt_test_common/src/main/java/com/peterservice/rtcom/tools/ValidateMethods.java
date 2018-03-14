package com.peterservice.rtcom.tools;

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка валидации
 */
public class ValidateMethods {
    private final static Logger LOGGER = Logger.getLogger(ValidateMethods.class);

    /**
     * @param t - type Validate
     * @return get Pattern
     */
    private static Pattern getPattern(eType t) {
        Pattern pattern;
        switch (t) {
            case INTEGER:
                pattern = Pattern.compile("\\d+");
                break;
            case UID:
                pattern = Pattern.compile("^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$");
                break;
            case DATE_ISO_8601_FULL:
                pattern = Pattern.compile("^\\d{4}-\\d\\d-\\d\\dT\\d\\d:\\d\\d:\\d\\d(\\.\\d+)?(([+-]\\d\\d:\\d\\d)|Z)?$");
                break;
            default:
                throw new AssertionError("Шаблон для " + t + " не найден!!!");
        }
        return pattern;
    }

    /**
     * Asserts that validate is true. If it isn't it throws an
     *
     * @param name_parameter - name parameter for validate
     * @param value          - validate value
     * @param t              - type validate
     */
    public static void assertTrueValidate(String name_parameter, Object value, eType t) {
        Objects.requireNonNull(name_parameter, "Wrong parameter " + name_parameter + " is null");
        Objects.requireNonNull(value, "Wrong parameter " + name_parameter + " is null");

        Pattern pattern = getPattern(t);
        Matcher m = pattern.matcher(value.toString());

        String logtext = "parameter <" + name_parameter + ">" +
                "\nValue: " + value +
                "\nUsed pattern: " + pattern +
                "\nResult validate: " + m.matches();
        LOGGER.info(logtext);

        Assert.assertTrue("Error validate " + logtext, m.matches());
    }


    public enum eType {
        INTEGER,
        //like 0be5e5ad-8aaa-4fa3-ac50-0a4e1f878683
        UID,
        //yyyy-MM-ddTHH:mm:ssZ
        DATE_ISO_8601_FULL
    }
}
