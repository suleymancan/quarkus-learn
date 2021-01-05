package org.acme.getting.config;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "test")

public class TestConfig {

    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TestConfig{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
