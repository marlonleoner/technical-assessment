package marlon.leoner.technical.assessment.domain.exception;

import marlon.leoner.technical.assessment.domain.model.BaseEntity;
import org.springframework.http.HttpStatus;

public class ObjectAlreadyExistsException extends BaseException {

    private static final Integer CODE = HttpStatus.CONFLICT.value();

    public ObjectAlreadyExistsException(String message) {
        super(message, CODE);
    }

    public ObjectAlreadyExistsException(Class<? extends BaseEntity> type) {
        super(type.getSimpleName().concat(" already exists"), CODE);
    }
}
