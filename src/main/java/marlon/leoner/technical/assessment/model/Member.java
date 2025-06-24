package marlon.leoner.technical.assessment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.dto.MemberDTO;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_member")
public class Member extends BaseEntity {

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "name")
    private String name;

    public Member(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    public MemberDTO toDTO() {
        return new MemberDTO(this);
    }
}
