package marlon.leoner.technical.assessment.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.domain.dto.TopicDTO;
import marlon.leoner.technical.assessment.domain.model.Topic;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TopicMapper {

    public static TopicDTO toDTO(Topic topic) {
        TopicDTO dto = new TopicDTO();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setSlug(topic.getSlug());

        return dto;
    }
}
