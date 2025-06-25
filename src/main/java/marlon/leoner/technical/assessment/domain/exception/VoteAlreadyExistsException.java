package marlon.leoner.technical.assessment.domain.exception;

public class VoteAlreadyExistsException extends ObjectAlreadyExistsException {

    private static final String MESSAGE = "O associado já votou nesta sessão.";

    public VoteAlreadyExistsException() {
        super(MESSAGE);
    }
}
