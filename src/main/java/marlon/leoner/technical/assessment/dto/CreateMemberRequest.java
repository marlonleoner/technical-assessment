package marlon.leoner.technical.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.model.Member;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateMemberRequest {

    private String cpf;
    private String name;

    public Member toEntity() {
        return new Member(this.cpf, this.name);
    }
}
