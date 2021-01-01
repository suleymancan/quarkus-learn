package org.acme.exception;

import java.util.Arrays;

public class ErrorDetailDto {

    private String key;

    private String message;

    private String[] args;

    public ErrorDetailDto(){
        this.args = new String[0];
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "ErrorDetailDto{" +
                "key='" + key + '\'' +
                ", message='" + message + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
