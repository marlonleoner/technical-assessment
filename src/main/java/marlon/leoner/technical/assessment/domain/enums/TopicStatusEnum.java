package marlon.leoner.technical.assessment.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TopicStatusEnum {

    CREATED(1, "Criado"),
    IN_PROGRESS(2, "Em progresso"),
    FINISHED(3, "Encerrado");

    private final Integer id;
    private final String description;
}
