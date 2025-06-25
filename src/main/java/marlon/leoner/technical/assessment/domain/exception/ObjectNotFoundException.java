package marlon.leoner.technical.assessment.domain.exception;

import marlon.leoner.technical.assessment.domain.model.BaseEntity;
import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends BaseException {

    private static final Integer CODE = HttpStatus.NOT_FOUND.value();

    public ObjectNotFoundException(String message) {
        super(message, CODE);
    }

    public ObjectNotFoundException(Class<? extends BaseEntity> type) {
        super(type.getSimpleName() + " not found", CODE);
    }
}
