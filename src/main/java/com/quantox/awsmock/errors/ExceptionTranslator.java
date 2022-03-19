package com.quantox.awsmock.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling, SecurityAdviceTrait {

    private final Logger log = LoggerFactory.getLogger(ExceptionTranslator.class);

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception, NativeWebRequest request) {
        log.debug("Exception {}", (Object) exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ExceptionErrors.INTERNAL_SERVER_ERROR.getErrorCode(), exception.getMessage() == null ?
                        ExceptionErrors.INTERNAL_SERVER_ERROR.getErrorDescription() : exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBadActionException(BadActionException exception, NativeWebRequest request) {
        log.debug("Exception {}", (Object) exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exception.getError().getErrorCode(), exception.getError().getErrorDescription(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception, NativeWebRequest request) {
        log.debug("Exception {}", (Object) exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ExceptionErrors.ENTITY_NOT_FOUND.getErrorCode(), exception.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

}