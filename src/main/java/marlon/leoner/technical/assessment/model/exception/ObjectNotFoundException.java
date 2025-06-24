package marlon.leoner.technical.assessment.model.exception;

public class ObjectNotFoundException extends Exception {

    public ObjectNotFoundException(Class type) {
        super(type.getSimpleName() + " not found");
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
