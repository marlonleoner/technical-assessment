package marlon.leoner.technical.assessment.aggregation;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.domain.dto.ResultDTO;
import marlon.leoner.technical.assessment.domain.dto.TopicDTO;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.domain.model.Topic;
import marlon.leoner.technical.assessment.domain.param.CreateTopicParam;
import marlon.leoner.technical.assessment.mapper.TopicMapper;
import marlon.leoner.technical.assessment.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicAggregation {

    private final TopicService topicService;

    public TopicDTO createTopic(CreateTopicParam params) {
        Topic topic = topicService.createTopic(params);
        return TopicMapper.toDTO(topic);
    }

    public List<TopicDTO> getAllTopics() {
        return topicService.getAllTopics()
                .stream()
                .map(TopicMapper::toDTO)
                .toList();
    }

    private Topic getTopicByIdOrException(String topicId) throws ObjectNotFoundException {
        return topicService.getTopicByIdOrException(topicId);
    }

    public TopicDTO getTopic(String topicId) throws ObjectNotFoundException {
        Topic topic = getTopicByIdOrException(topicId);
        return TopicMapper.toDTO(topic);
    }
}
