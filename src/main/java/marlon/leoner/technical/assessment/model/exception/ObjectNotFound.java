package marlon.leoner.technical.assessment.model.exception;

public class ObjectNotFound extends Exception {

    public ObjectNotFound(Class type) {
        super(type.getSimpleName() + " not found");
    }

    public ObjectNotFound(String message) {
        super(message);
    }
}
