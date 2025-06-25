package marlon.leoner.technical.assessment.domain.exception;

import org.springframework.http.HttpStatus;

public class SessionInProgressException extends BaseException {

    private static final String MESSAGE = "A sessão ainda não foi finalizada.";
    private static final Integer CODE = HttpStatus.BAD_REQUEST.value();

    public SessionInProgressException() {
        super(MESSAGE, CODE);
    }
}
