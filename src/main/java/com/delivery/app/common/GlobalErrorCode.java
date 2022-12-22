package com.delivery.app.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode {

    NOT_FOUND(404, "GLOBAL_000", "Not found"),
    INVALID_METHOD_ARGUMENT(400, "GLOBAL_001", "Invalid method argument"),
    INVALID_JSON_FORMAT(400, "GLOBAL_002", "Invalid json format"),
    UNSUPPORTED_MEDIA_TYPE(400, "GLOBAL_003", "Unsupported media type"),
    UNSUPPORTED_HTTP_METHOD(400, "GLOBAL_004", "Unsupported http method")
    ;

    private final int status;
    private final String code;
    private final String message;

}
