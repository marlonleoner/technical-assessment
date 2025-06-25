package marlon.leoner.technical.assessment.domain.param;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateVotingSessionParam {

    @NotNull(message = "O campo 'duration' é obrigatório")
    private Integer duration;
}
