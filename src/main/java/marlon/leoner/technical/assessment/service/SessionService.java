package marlon.leoner.technical.assessment.service;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.model.Session;
import marlon.leoner.technical.assessment.model.Topic;
import marlon.leoner.technical.assessment.model.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.model.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.model.exception.SessionException;
import marlon.leoner.technical.assessment.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class SessionService {

    private static final String ERROR_SESSION_ALREADY_EXISTS = "Já existe uma votação vinculada à esta pauta.";
    private static final String SESSION_DOES_NOT_EXIST = "Não existe uma sessão aberta para esta pauta.";
    private static final String SESSION_IS_NOT_OPEN = "A sessão informada não encontra-se aberta para votação.";

    private final SessionRepository repository;

    public Session createSession(Topic topic, Integer duration) {
        Session session = new Session(topic, duration);
        return repository.save(session);
    }

    public void validateSessionExists(Session session) throws ObjectNotFoundException {
        if (Objects.isNull(session)) {
            throw new ObjectNotFoundException(SESSION_DOES_NOT_EXIST);
        }
    }

    public void validateSessionAlreadyExists(Session session) throws ObjectAlreadyExistsException {
        if (Objects.nonNull(session)) {
            throw new ObjectAlreadyExistsException(ERROR_SESSION_ALREADY_EXISTS);
        }
    }

    public void validateSessionOpened(Session session) throws SessionException {
        if (session.isClosed()) {
            throw new SessionException(SESSION_IS_NOT_OPEN);
        }
    }

}
