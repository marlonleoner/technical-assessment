package marlon.leoner.technical.assessment.aggregation;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.domain.dto.SessionDTO;
import marlon.leoner.technical.assessment.domain.exception.BaseException;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.exception.SessionAlreadyExistsException;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.domain.model.Topic;
import marlon.leoner.technical.assessment.domain.param.CreateSessionParam;
import marlon.leoner.technical.assessment.mapper.SessionMapper;
import marlon.leoner.technical.assessment.service.SessionService;
import marlon.leoner.technical.assessment.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SessionAggregation {

    private final TopicService topicService;
    private final SessionService sessionService;

    private Topic getTopic(String topicId) throws ObjectNotFoundException {
        return topicService.getTopicByIdOrException(topicId);
    }

    public List<SessionDTO> getAllSessions() {
        return sessionService.getAllSessions()
                .stream()
                .map(SessionMapper::toDTO)
                .toList();
    }

    public SessionDTO createSession(CreateSessionParam params) throws BaseException {
        validateSessionExists(params.getTopicId());

        Session session = SessionMapper.toEntity(params);
        session.setTopic(getTopic(params.getTopicId()));
        sessionService.save(session);

        return SessionMapper.toDTO(session);
    }

    private void validateSessionExists(String topicId) throws SessionAlreadyExistsException {
        Optional<Session> session = sessionService.getSessionByTopicId(topicId);
        if (session.isPresent()) throw new SessionAlreadyExistsException();
    }
}
