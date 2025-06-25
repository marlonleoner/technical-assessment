package marlon.leoner.technical.assessment.domain.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateVoteParam {

    @JsonProperty("member_id")
    private String memberId;

    @JsonProperty("session_id")
    private String sessionId;

    @NotNull(message = "O campo 'vote' é obrigatório.")
    @Pattern(regexp = "^[SN]", message = "Os valores para o campo 'vote' são: S ou N")
    private String vote;
}
