package com.peterservice.rtcom.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.Objects;

public final class ObjectMapperMethods {
    public static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    private ObjectMapperMethods() {}

    public static Object getObjectMapper(String stringBody, Class classObjectMapName) throws IOException {
        Objects.requireNonNull(classObjectMapName, "classObjectMapName = null");
        return MAPPER.readValue(stringBody, classObjectMapName);
    }
}
