package com.BaimukhametovIlnaz.E_Commerce.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// Почему в конце метода есть параметр null?
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        return response(HttpStatus.NOT_FOUND, "NOT_FOUND", ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex, HttpServletRequest req) {
        return response(HttpStatus.CONFLICT,
                "BUSINESS_ERROR", ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler({AccessDeniedException.class, org.springframework.security.access.AccessDeniedException.class})
    public ResponseEntity<ErrorResponse> handleForbidden(Exception ex, HttpServletRequest req) {
        return response(HttpStatus.FORBIDDEN, "ACCESS_DENIED", ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(HttpServletRequest req) {
        return response(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "Authentication required", req.getRequestURI(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, String> fields = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (a, b) -> a, LinkedHashMap::new));
        return response(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", "Validation failed", req.getRequestURI(), fields);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraint(ConstraintViolationException ex, HttpServletRequest req) {
        return response(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOther(Exception ex, HttpServletRequest req) {
        log.error("Unhandled exception", ex);
        return response(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "Unexpected error", req.getRequestURI(), null);
    }

    private ResponseEntity<ErrorResponse> response(HttpStatus status,
                                                   String error, String message,
                                                   String path,
                                                   Map<String, String> filedErrors) {
        return ResponseEntity.status(status)
                .body(new ErrorResponse(OffsetDateTime.now(), status.value(), error, message, path, filedErrors));
    }

}