package marlon.leoner.technical.assessment.aggregation;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.dto.CreateTopicRequest;
import marlon.leoner.technical.assessment.dto.TopicDTO;
import marlon.leoner.technical.assessment.model.Topic;
import marlon.leoner.technical.assessment.model.exception.ObjectNotFound;
import marlon.leoner.technical.assessment.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicAggregation {

    private final TopicService service;

    public List<TopicDTO> getAllTopics() {
        return service.getAllTopics()
                .stream()
                .map(Topic::toDTO)
                .toList();
    }

    public TopicDTO getTopic(String topicId) throws ObjectNotFound {
        Topic topic = service.getTopicByIdOrException(topicId);
        return topic.toDTO();
    }

    public TopicDTO createTopic(CreateTopicRequest params) {
        Topic topic = service.createTopic(params);
        return topic.toDTO();
    }
}
