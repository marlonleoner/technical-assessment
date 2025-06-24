package marlon.leoner.technical.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.model.Member;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {

    private String id;
    private String cpf;
    private String name;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.cpf = member.getCpf();
        this.name = member.getName();
    }
}
