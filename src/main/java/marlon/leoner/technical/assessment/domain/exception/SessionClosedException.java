package marlon.leoner.technical.assessment.domain.exception;

import org.springframework.http.HttpStatus;

public class SessionClosedException extends BaseException {

    private static final String MESSAGE = "A sessão já foi finalizada.";
    private static final Integer CODE = HttpStatus.BAD_REQUEST.value();

    public SessionClosedException() {
        super(MESSAGE, CODE);
    }
}
