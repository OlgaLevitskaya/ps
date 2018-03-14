package com.peterservice.rtcom.tools;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import java.util.Objects;

public final class PrintLog {
    private final static Logger LOGGER = Logger.getLogger(PrintLog.class);

    private PrintLog() {
    }

    /**
     * @param requestSpecification спецификация запроса
     */
    public static void urlRequest(RequestSpecification requestSpecification) {
        Objects.requireNonNull(requestSpecification, "RequestSpecification = null");
        FilterableRequestSpecification filterableRS = (FilterableRequestSpecification) requestSpecification;
        info(filterableRS.getMethod() + ":\t",
                filterableRS.getBaseUri()
                        + ":" + filterableRS.getPort()
                        + filterableRS.getDerivedPath());
        if (!filterableRS.getRequestParams().isEmpty()) {
            info("PARAMS: ", filterableRS.getRequestParams());
        }
    }

    public static void response(Response message) {
        info("RESPONSE:\n", message.getBody().asString());
    }

    public static void error(Object message) {
        LOGGER.error(message);
    }

    public static void info(String str, Object message) {
        LOGGER.info(str + message);
    }
}
