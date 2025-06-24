package marlon.leoner.technical.assessment.service;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.dto.request.CreateTopicRequest;
import marlon.leoner.technical.assessment.model.Topic;
import marlon.leoner.technical.assessment.model.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TopicService {

    private final TopicRepository repository;

    public List<Topic> getAllTopics() {
        return repository.findAll();
    }

    public Optional<Topic> getTopic(String topicId) {
        return repository.findById(topicId);
    }

    public Topic getTopicByIdOrException(String topicId) throws ObjectNotFoundException {
        Optional<Topic> topic = this.getTopic(topicId);
        return topic.orElseThrow(() -> new ObjectNotFoundException(Topic.class));
    }

    public Topic createTopic(CreateTopicRequest params) {
        Topic topic = params.toEntity();
        return repository.save(topic);
    }
}
