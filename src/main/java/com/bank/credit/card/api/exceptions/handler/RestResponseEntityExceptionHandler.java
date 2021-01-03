package com.bank.credit.card.api.exceptions.handler;

import com.bank.credit.card.api.binding.ErrorResource;
import com.bank.credit.card.api.binding.FieldErrorResource;
import com.bank.credit.card.api.exceptions.EntityNotFoundException;
import com.bank.credit.card.api.exceptions.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * RestResponseEntityExceptionHandler class will be responsible  to handle all the error or failure scenario as part of implemented error framework.
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    public static final Logger LOG = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
        LOG.debug("response status {}", status);
        return new ResponseEntity<>(body, new HttpHeaders(), status);
    }

    protected ResponseEntity<ErrorResource> errorResponse(Throwable throwable, HttpStatus status, HttpServletRequest request) {
        LOG.debug("Error Response with status {}", status);
        if (Objects.nonNull(throwable)) {
            ErrorResource errorResource = new ErrorResource(throwable);
            LOG.error("error  {}  captured para ip {} ==> {}", errorResource.getCode(), request.getRemoteAddr(), errorResource, throwable);
            return response(errorResource, status);
        }
        LOG.error("error for ip {} ==> {}", request.getRemoteAddr(), status);
        return response(null, status);
    }

    protected ResponseEntity<ErrorResource> errorResponse(Throwable throwable, List<FieldErrorResource> fieldErrors, HttpStatus status, HttpServletRequest request) {
        LOG.debug("Error Response with status {} when field level error happens", status);
        if (Objects.nonNull(throwable)) {
            ErrorResource errorResource = new ErrorResource(throwable, fieldErrors);
            LOG.error("error  {}  captured para ip {} ==> {}", errorResource.getCode(), request.getRemoteAddr(), errorResource, throwable);
            return response(errorResource, status);
        }
            LOG.error("error for ip {} ==> {}", request.getRemoteAddr(), status);
            return response(null, status);
    }

    /**
     * handleInvalidRequest is the funcation which we have created to handle field level exception as given in assignment,
     * one of the scenario can be  custom validation on credit card number written using annotation CardNumber
     * @param e : Exception object
     * @param request : Http servlet request
     * @return : return handled error response
     */
    @ExceptionHandler({InvalidRequestException.class})
    @ResponseBody
    protected ResponseEntity<ErrorResource> handleInvalidRequest(RuntimeException e, HttpServletRequest request) {
        InvalidRequestException ire = (InvalidRequestException) e;
        List<FieldErrorResource> fieldErrorResources;
        List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
        fieldErrorResources = fieldErrors.stream().map(fieldError -> FieldErrorResource.builder().parameter(fieldError.getField()).code(fieldError.getCode()).message(fieldError.getDefaultMessage()).build()).collect(Collectors.toList());

        return errorResponse(e, fieldErrorResources, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    protected ResponseEntity<ErrorResource> handleEntityNotFound(RuntimeException e, HttpServletRequest request) {
        return errorResponse(e, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseBody
    protected ResponseEntity<ErrorResource> handleAccessDenied(RuntimeException e, HttpServletRequest request) {
        return errorResponse(e, HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    @ResponseBody
    protected ResponseEntity<ErrorResource> handleMaxUploadSizeExceeded(RuntimeException e, HttpServletRequest request) {
        return errorResponse(e, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResource> handleError(Exception e, HttpServletRequest request) {
        return errorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
