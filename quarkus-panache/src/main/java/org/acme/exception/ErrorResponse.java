package org.acme.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private long timestamp = System.currentTimeMillis();

    private String exception;

    private List<ErrorDetailDto> errors = new ArrayList<>();

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public List<ErrorDetailDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetailDto> errors) {
        this.errors = errors;
    }

    public void addError(ErrorDetailDto errorDetailDto){
        this.errors.add(errorDetailDto);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "timestamp=" + timestamp +
                ", exception='" + exception + '\'' +
                ", errorDetailDtoList=" + errors +
                '}';
    }
}
