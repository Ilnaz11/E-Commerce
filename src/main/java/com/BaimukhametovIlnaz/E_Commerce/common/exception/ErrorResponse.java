package com.BaimukhametovIlnaz.E_Commerce.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
    OffsetDateTime timestamp,
    int status,
    String error,
    String message,
    String path,
    Map<String, String> fieldErrors
) {}