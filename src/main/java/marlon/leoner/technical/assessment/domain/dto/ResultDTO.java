package marlon.leoner.technical.assessment.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResultDTO {

    private TopicDTO topic;

    private Integer totalVotes;

    private Integer votesYes;

    private Integer votesNo;

    private String result;
}
