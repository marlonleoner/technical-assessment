package marlon.leoner.technical.assessment.aggregation;

import marlon.leoner.technical.assessment.domain.dto.TopicDTO;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.model.Topic;
import marlon.leoner.technical.assessment.domain.param.CreateTopicParam;
import marlon.leoner.technical.assessment.service.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicAggregationTest {

    @Mock
    private TopicService service;

    @InjectMocks
    private TopicAggregation aggregation;

    private Topic topic;

    @BeforeEach
    void setup() {
        topic = new Topic();
        topic.setName("Título da pauta");
    }

    @Test
    void shouldCreateTopicAndReturnDTO() {
        CreateTopicParam param = new CreateTopicParam();
        param.setName("Título da pauta");

        when(service.createTopic(param)).thenReturn(topic);

        TopicDTO result = aggregation.createTopic(param);

        assertNotNull(result);
        assertEquals("Título da pauta", result.getName());

        verify(service).createTopic(param);
    }

    @Test
    void shouldReturnAllTopicsAsDTOs() {
        when(service.getAllTopics()).thenReturn(List.of(topic));

        List<TopicDTO> result = aggregation.getAllTopics();

        assertEquals(1, result.size());
        verify(service).getAllTopics();
    }

    @Test
    void shouldReturnTopicDTOById() throws ObjectNotFoundException {
        when(service.getTopicByIdOrException(anyString())).thenReturn(topic);

        TopicDTO result = aggregation.getTopic(anyString());

        assertNotNull(result);
        verify(service).getTopicByIdOrException(anyString());
    }

    @Test
    void shouldThrowWhenTopicNotFound() throws ObjectNotFoundException {
        when(service.getTopicByIdOrException(anyString())).thenThrow(new ObjectNotFoundException(Topic.class));

        assertThrows(ObjectNotFoundException.class, () -> {
            aggregation.getTopic(anyString());
        });

        verify(service).getTopicByIdOrException(anyString());
    }
}
