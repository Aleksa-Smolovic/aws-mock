package com.quantox.awsmock.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ExceptionErrors {

    MACHINE_LOCKED("E01", "Machine is unavailable at the moment!"),
    INVALID_ACTION("E02", "Invalid action!"),
    INTERNAL_SERVER_ERROR("E03", "Internal server error!"),
    ENTITY_NOT_FOUND("E04", "Entity missing!");

    private final String errorCode;
    private final String errorDescription;

}
