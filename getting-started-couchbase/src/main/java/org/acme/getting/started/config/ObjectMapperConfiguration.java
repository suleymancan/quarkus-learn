package org.acme.getting.started.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.Startup;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

public class ObjectMapperConfiguration {

    @Singleton
    @Produces
    @Startup
    public ObjectMapper getObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }
}
