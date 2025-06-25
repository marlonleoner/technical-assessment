package marlon.leoner.technical.assessment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marlon.leoner.technical.assessment.domain.model.Topic;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicDTO {

    private String id;
    private String name;
    private String slug;

    public TopicDTO(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
        this.slug = topic.getSlug();
    }
}
