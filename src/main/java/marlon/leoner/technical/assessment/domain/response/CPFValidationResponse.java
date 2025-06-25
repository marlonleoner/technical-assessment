package marlon.leoner.technical.assessment.domain.response;

import lombok.Data;

@Data
public class CPFValidationResponse {

    private Boolean valid;

    private String formatted;
}
