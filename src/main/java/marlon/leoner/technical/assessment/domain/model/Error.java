package marlon.leoner.technical.assessment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class Error {

    private Integer status;

    private String message;
}
