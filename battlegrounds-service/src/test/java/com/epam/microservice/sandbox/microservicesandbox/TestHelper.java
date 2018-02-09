package com.epam.microservice.sandbox.microservicesandbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class TestHelper {

    private static ObjectMapper mapper = new ObjectMapper()
            .registerModule(new Jdk8Module());

    private TestHelper() {
        throw new UnsupportedOperationException();
    }

    public static String asJsonString(final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
