package com.delivery.app.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(GlobalErrorCode.INVALID_METHOD_ARGUMENT.getCode())
                .message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .status(GlobalErrorCode.INVALID_METHOD_ARGUMENT.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(GlobalErrorCode.INVALID_METHOD_ARGUMENT.getStatus()));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(GlobalErrorCode.INVALID_JSON_FORMAT.getCode())
                .message(GlobalErrorCode.INVALID_JSON_FORMAT.getMessage())
                .status(GlobalErrorCode.INVALID_JSON_FORMAT.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(GlobalErrorCode.INVALID_JSON_FORMAT.getStatus()));
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(GlobalErrorCode.UNSUPPORTED_MEDIA_TYPE.getCode())
                .message(GlobalErrorCode.UNSUPPORTED_MEDIA_TYPE.getMessage())
                .status(GlobalErrorCode.UNSUPPORTED_MEDIA_TYPE.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(GlobalErrorCode.UNSUPPORTED_MEDIA_TYPE.getStatus()));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(GlobalErrorCode.UNSUPPORTED_HTTP_METHOD.getCode())
                .message(GlobalErrorCode.UNSUPPORTED_HTTP_METHOD.getMessage())
                .status(GlobalErrorCode.UNSUPPORTED_HTTP_METHOD.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(GlobalErrorCode.UNSUPPORTED_HTTP_METHOD.getStatus()));
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(GlobalErrorCode.NOT_FOUND.getCode())
                .message(GlobalErrorCode.NOT_FOUND.getMessage())
                .status(GlobalErrorCode.NOT_FOUND.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(GlobalErrorCode.NOT_FOUND.getStatus()));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(GlobalErrorCode.MISSING_QUERY_PARAMETER.getCode())
                .message(e.getLocalizedMessage())
                .status(GlobalErrorCode.MISSING_QUERY_PARAMETER.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(GlobalErrorCode.MISSING_QUERY_PARAMETER.getStatus()));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(GlobalErrorCode.INVALID_QUERY_PARAMETER_TYPE.getCode())
                .message(GlobalErrorCode.INVALID_QUERY_PARAMETER_TYPE.getMessage())
                .status(GlobalErrorCode.INVALID_QUERY_PARAMETER_TYPE.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(GlobalErrorCode.INVALID_QUERY_PARAMETER_TYPE.getStatus()));
    }
}
