package marlon.leoner.technical.assessment.service;

import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.model.Topic;
import marlon.leoner.technical.assessment.domain.param.CreateTopicParam;
import marlon.leoner.technical.assessment.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {

    @Mock
    private TopicRepository repository;

    @InjectMocks
    private TopicService topicService;

    @Test
    void shouldReturnAllTopics() {
        Topic topic1 = new Topic("Topic 1", "Description Topic 1");
        Topic topic2 = new Topic("Topic 2", "Description Topic 2");

        when(repository.findAll()).thenReturn(List.of(topic1, topic2));

        List<Topic> topics = topicService.getAllTopics();

        assertEquals(2, topics.size());
        verify(repository).findAll();
    }

    @Test
    void shouldReturnTopicWhenFound() {
        String name = "Topic Test";
        Topic topic = new Topic(name, name.concat(" description"));

        when(repository.findById(anyString())).thenReturn(Optional.of(topic));

        Optional<Topic> result = topicService.getTopic(anyString());

        assertTrue(result.isPresent());
        assertEquals(name, result.get().getName());
    }

    @Test
    void shouldThrowExceptionWhenTopicNotFound() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            topicService.getTopicByIdOrException(anyString());
        });
    }

    @Test
    void shouldCreateTopic() {
        String name = "Topic";
        CreateTopicParam param = new CreateTopicParam(name, name.concat(" description"));
        Topic topic = param.toEntity();

        when(repository.save(any(Topic.class))).thenReturn(topic);

        Topic created = topicService.createTopic(param);

        assertNotNull(created);
        assertEquals(name, created.getName());
        verify(repository).save(any(Topic.class));
    }
}