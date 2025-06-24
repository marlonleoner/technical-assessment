package marlon.leoner.technical.assessment.aggregation;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.dto.SessionDTO;
import marlon.leoner.technical.assessment.dto.TopicDTO;
import marlon.leoner.technical.assessment.dto.request.CreateTopicRequest;
import marlon.leoner.technical.assessment.dto.request.CreateVotingSessionRequest;
import marlon.leoner.technical.assessment.model.Session;
import marlon.leoner.technical.assessment.model.Topic;
import marlon.leoner.technical.assessment.model.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.model.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.service.SessionService;
import marlon.leoner.technical.assessment.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicAggregation {

    private final TopicService topicService;
    private final SessionService sessionService;

    public List<TopicDTO> getAllTopics() {
        return topicService.getAllTopics()
                .stream()
                .map(Topic::toDTO)
                .toList();
    }

    public TopicDTO getTopic(String topicId) throws ObjectNotFoundException {
        Topic topic = getTopicByIdOrException(topicId);
        return topic.toDTO();
    }

    private Topic getTopicByIdOrException(String topicId) throws ObjectNotFoundException {
        return topicService.getTopicByIdOrException(topicId);
    }

    public TopicDTO createTopic(CreateTopicRequest params) {
        Topic topic = topicService.createTopic(params);
        return topic.toDTO();
    }

    public SessionDTO createVotingSession(String topicId, CreateVotingSessionRequest params) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        Topic topic = getTopicByIdOrException(topicId);
        sessionService.validateSessionAlreadyExists(topic.getSession());

        Session session = sessionService.createSession(topic, params.getDuration());
        return session.toDTO();
    }
}
