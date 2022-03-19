package com.quantox.awsmock.errors;

import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;

@Getter
public class EntityNotFoundException extends AbstractThrowableProblem {

    private final transient String entityId;
    private final transient String entityName;

    @Override
    public String getMessage() {
        return String.format("Entity '%s' with id: '%s' was not found", entityName, entityId);
    }

    public EntityNotFoundException(String entityId, String entityName) {
        this.entityId = entityId;
        this.entityName = entityName;
    }

    public EntityNotFoundException(Long entityId, String entityName) {
        this.entityId = entityId.toString();
        this.entityName = entityName;
    }

}
