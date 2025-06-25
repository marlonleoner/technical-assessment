package marlon.leoner.technical.assessment.domain.exception;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends BaseException {

    private static final Integer CODE = HttpStatus.BAD_GATEWAY.value();
    private static final String MESSAGE = "Falha ao consultar sistema externo.";

    public ExternalServiceException() {
        super(MESSAGE, CODE);
    }
}
