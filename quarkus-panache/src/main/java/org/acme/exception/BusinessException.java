package org.acme.exception;


public class BusinessException extends RuntimeException {

    private final String key;
    private final String message;
    private final String[] args;

    public BusinessException(String key, String message){
        this.key = key;
        this.message = message;
        this.args = new String[0];
    }

    public BusinessException(String key, String message, String... args){
        this.key = key;
        this.message = message;
        this.args = args;
    }

    public String getKey() {
        return key;
    }

    public String[] getArgs() {
        return this.args;
    }

    @Override
    public String getMessage() {
        return this.message + " " + String.join(",", this.args);
    }
}

