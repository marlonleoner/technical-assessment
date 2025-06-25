package marlon.leoner.technical.assessment.domain.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateVoteParam {

    @JsonProperty("member_id")
    private String memberId;

    private String vote;
}
