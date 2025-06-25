package marlon.leoner.technical.assessment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {

    private String id;
    private String cpf;
    private String name;
}
