package marlon.leoner.technical.assessment.model.exception;

import marlon.leoner.technical.assessment.model.BaseEntity;

public class ObjectAlreadyExistsException extends Exception {

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }

    public ObjectAlreadyExistsException(Class<? extends BaseEntity> type) {
        super(type.getSimpleName().concat(" already exists"));
    }
}
