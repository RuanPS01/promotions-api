package br.inatel.promotions.api.core;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception{

    private final String errorCode;
    private final String message;
    private final HttpStatus status;

    public ApiException(AppErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
