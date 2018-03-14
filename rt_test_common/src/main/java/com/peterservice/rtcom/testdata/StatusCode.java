package com.peterservice.rtcom.testdata;

public final class StatusCode {
    public static final int IS_OK = 200;
    public static final String INVALID_PARAMETERS = "InvalidParameters";
    public static final String NOT_READABLE_HTTP_MESSAGE = "HTTP message not readable";
    public static final String PARAMETER_ERROR_1 = "=must be greater than or equal to 1";
    public static final String PARAMETER_ERROR_2 = "=numeric value out of bounds (<10 digits>.<0 digits> expected)";
    public static final String PARAMETER_ERROR_3 = "=size must be between 0 and 512";
    public static final int BAD_REQUEST = 400; //Ошибка 400: Bad request
    private StatusCode() {

    }

    /**
     * Success
     *
     * @return 200
     */
    public static int isOK() {
        return IS_OK;
    }

    /**
     * Bad Request
     *
     * @return 400
     */
    public static int isBadRequest() {
        return BAD_REQUEST;
    }
}
