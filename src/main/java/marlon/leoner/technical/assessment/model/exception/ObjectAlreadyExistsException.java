package marlon.leoner.technical.assessment.model.exception;

public class ObjectAlreadyExistsException extends Exception {

    public ObjectAlreadyExistsException(Class type) {
        super(type.getSimpleName().concat(" already exists"));
    }
}
