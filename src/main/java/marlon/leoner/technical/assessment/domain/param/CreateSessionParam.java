package marlon.leoner.technical.assessment.domain.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Data
public class CreateSessionParam {

    @JsonProperty("topic_id")
    @NotNull(message = "Uma pauta deve ser informada.")
    private String topicId;

    private Integer duration;

    public Integer getDuration() {
        return Objects.isNull(this.duration) ? 1 : this.duration;
    }
}
