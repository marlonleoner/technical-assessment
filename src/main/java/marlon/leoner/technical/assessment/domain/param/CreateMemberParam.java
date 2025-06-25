package marlon.leoner.technical.assessment.domain.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.domain.model.Member;
import jakarta.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateMemberParam {

    @NotNull(message = "O campo 'cpf' é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 dígitos")
    private String cpf;

    @NotBlank
    private String name;

    public Member toEntity() {
        return new Member(this.cpf, this.name);
    }
}
