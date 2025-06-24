package marlon.leoner.technical.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.model.Topic;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateTopicRequest {

    private String name;
    private String description;

    public Topic toEntity() {
        return new Topic(this.name, this.description);
    }
}
