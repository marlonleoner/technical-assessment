package marlon.leoner.technical.assessment.domain.param;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateMemberParam {

    @NotNull(message = "O campo 'cpf' é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 dígitos")
    private String cpf;

    @NotNull(message = "O campo 'name' é obrigatório")
    private String name;
}
