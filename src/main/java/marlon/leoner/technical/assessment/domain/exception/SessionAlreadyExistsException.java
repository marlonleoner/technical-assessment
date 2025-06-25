package marlon.leoner.technical.assessment.domain.exception;

public class SessionAlreadyExistsException extends ObjectAlreadyExistsException {

    private static final String MESSAGE = "Já existe uma votação vinculada à esta pauta.";

    public SessionAlreadyExistsException() {
        super(MESSAGE);
    }
}
