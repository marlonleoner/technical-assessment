package marlon.leoner.technical.assessment.domain.exception;

import marlon.leoner.technical.assessment.domain.model.BaseEntity;

public class ObjectNotFoundException extends Exception {

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(Class<? extends BaseEntity> type) {
        super(type.getSimpleName() + " not found");
    }
}
