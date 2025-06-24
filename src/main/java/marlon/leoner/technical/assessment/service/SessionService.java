package marlon.leoner.technical.assessment.service;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.model.Session;
import marlon.leoner.technical.assessment.model.Topic;
import marlon.leoner.technical.assessment.model.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class SessionService {

    private static final String ERROR_SESSION_ALREADY_EXISTS = "Já existe uma votação vinculada à esta pauta.";

    private final SessionRepository repository;

    public Session createSession(Topic topic, Integer duration) {
        Session session = new Session(topic, duration);
        return repository.save(session);
    }

    public void validateSessionAlreadyExists(Session session) throws ObjectAlreadyExistsException {
        if (Objects.nonNull(session)) {
            throw new ObjectAlreadyExistsException(ERROR_SESSION_ALREADY_EXISTS);
        }
    }
}
