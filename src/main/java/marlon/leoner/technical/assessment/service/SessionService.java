package marlon.leoner.technical.assessment.service;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.domain.exception.SessionNotFoundException;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SessionService {

    private final SessionRepository repository;

    public List<Session> getAllSessions() {
        return repository.findAll();
    }

    public Optional<Session> getSessionById(String sessionId) {
        return repository.findById(sessionId);
    }

    public Session getSessionByIdOrException(String sessionId) throws SessionNotFoundException {
        Optional<Session> session = getSessionById(sessionId);
        return session.orElseThrow(SessionNotFoundException::new);
    }

    public Session save(Session session) {
        return repository.save(session);
    }

    public Optional<Session> getSessionByTopicId(String topicId) {
        return repository.findSessionByTopicId(topicId);
    }
}
