package marlon.leoner.technical.assessment.model.exception;

import marlon.leoner.technical.assessment.model.BaseEntity;

public class ObjectNotFoundException extends Exception {

    public ObjectNotFoundException(Class<? extends BaseEntity> type) {
        super(type.getSimpleName() + " not found");
    }
}
