package com.watch_inventory.exception;

import java.time.Instant;
import java.util.List;

public record ErrorApi(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        List<FieldError> fieldError
) {

    public record FieldError (String field, String message) {}
}
