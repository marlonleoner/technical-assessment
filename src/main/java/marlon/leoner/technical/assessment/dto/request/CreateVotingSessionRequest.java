package marlon.leoner.technical.assessment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateVotingSessionRequest {

    @NotNull(message = "O campo 'duration' é obrigatório")
    private Integer duration;
}
