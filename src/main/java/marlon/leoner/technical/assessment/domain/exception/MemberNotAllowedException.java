package marlon.leoner.technical.assessment.domain.exception;

import org.springframework.http.HttpStatus;

public class MemberNotAllowedException extends BaseException {

    private static final String MESSAGE = "O associado não possui permissão para votar.";
    private static final Integer CODE = HttpStatus.FORBIDDEN.value();

    public MemberNotAllowedException() {
        super(MESSAGE, CODE);
    }
}
