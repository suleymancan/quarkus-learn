package org.acme.exception;

import io.quarkus.qute.i18n.MessageBundles;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionHandler implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException exception) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("BusinessException");
        errorResponse.addError(this.getErrorDetail(exception.getKey(), exception.getMessage(), exception.getArgs()));
        return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
    }

    private ErrorDetailDto getErrorDetail(String key, String message, String[] args) {
        final ErrorDetailDto errorDetailDto = new ErrorDetailDto();
        errorDetailDto.setKey(key);
        errorDetailDto.setMessage(message);
        errorDetailDto.setArgs(args);
        return errorDetailDto;
    }


}
