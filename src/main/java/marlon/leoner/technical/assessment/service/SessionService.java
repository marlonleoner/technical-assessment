package marlon.leoner.technical.assessment.service;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SessionService {

    private static final String SESSION_DOES_NOT_EXIST = "Não existe uma sessão aberta para esta pauta.";
    private static final String SESSION_IS_NOT_OPEN = "A sessão informada não encontra-se aberta para votação.";

    private final SessionRepository repository;

    public List<Session> getAllSessions() {
        return repository.findAll();
    }

    public Optional<Session> getSessionById(String sessionId) {
        return repository.findById(sessionId);
    }

    public Session getSessionByIdOrException(String sessionId) throws ObjectNotFoundException {
        Optional<Session> session = getSessionById(sessionId);
        return session.orElseThrow(() -> new ObjectNotFoundException(Session.class));
    }

    public Session save(Session session) {
        return repository.save(session);
    }

    public Optional<Session> getSessionByTopicId(String topicId) {
        return repository.findSessionByTopicId(topicId);
    }
}
