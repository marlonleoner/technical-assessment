package marlon.leoner.technical.assessment.domain.exception;

import lombok.Getter;

@Getter
public class BaseException extends Exception {

    private final Integer code;

    public BaseException(String message) {
        super(message);
        this.code = 500;
    }

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
