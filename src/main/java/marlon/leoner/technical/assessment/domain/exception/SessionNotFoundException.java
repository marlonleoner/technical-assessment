package marlon.leoner.technical.assessment.domain.exception;

import marlon.leoner.technical.assessment.domain.model.Session;

public class SessionNotFoundException extends ObjectNotFoundException {

    public SessionNotFoundException() {
        super(Session.class);
    }
}
