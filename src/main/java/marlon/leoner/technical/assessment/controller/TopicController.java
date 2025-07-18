package marlon.leoner.technical.assessment.controller;

import lombok.AllArgsConstructor;
import marlon.leoner.technical.assessment.aggregation.TopicAggregation;
import marlon.leoner.technical.assessment.domain.dto.TopicDTO;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.param.CreateTopicParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/topic")
public class TopicController {

    private final TopicAggregation aggregation;

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAllTopics() {
        return ResponseEntity.ok(aggregation.getAllTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopic(@PathVariable("id") String topicId) throws ObjectNotFoundException {
        return ResponseEntity.ok(aggregation.getTopic(topicId));
    }

    @PostMapping
    public ResponseEntity<Void> createTopic(@RequestBody CreateTopicParam params) {
        TopicDTO topic = aggregation.createTopic(params);
        return ResponseEntity.created(URI.create("/topic/" + topic.getId())).build();
    }
}
