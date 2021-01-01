package org.acme.exception;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("ValidationException");
        errorResponse.addError(this.getErrorDetail("ValidationException", exception.getMessage(), null));
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }

    private ErrorDetailDto getErrorDetail(String key, String message, String[] args) {
        final ErrorDetailDto errorDetailDto = new ErrorDetailDto();
        errorDetailDto.setKey(key);
        errorDetailDto.setMessage(message);
        errorDetailDto.setArgs(args);
        return errorDetailDto;
    }


}
