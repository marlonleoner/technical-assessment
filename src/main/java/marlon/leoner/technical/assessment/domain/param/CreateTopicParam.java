package marlon.leoner.technical.assessment.domain.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.domain.model.Topic;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateTopicParam {

    private String name;
    private String description;

    public Topic toEntity() {
        return new Topic(this.name, this.description);
    }
}
