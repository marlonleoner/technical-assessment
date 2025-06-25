package marlon.leoner.technical.assessment.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class SessionDTO {

    private String id;

    @JsonProperty("topic_id")
    private String topicId;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime finishTime;

    private boolean closed;
}
